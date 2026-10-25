package service;

import model.AllotmentCutoff;
import model.College;
import model.RecommendationResult;
import model.StudentProfile;
import repository.CollegeDatabase;
import repository.DistrictGraph;

public class PredictionEngine {

    public static RecommendationResult evaluate(StudentProfile profile, AllotmentCutoff cutoff) {
        College college = CollegeDatabase.getCollege(cutoff.getCollegeCode());
        if (college == null) {
            college = new College(cutoff.getCollegeCode(), cutoff.getCollegeCode(), "Unknown", cutoff.getCollegeType());
        }

        int userRank = profile.getRank();
        String requestedCategory = profile.getCategory();

        // Check if there is an exact quota cutoff for requested category
        Integer exactCutoff = cutoff.getExactCutoff(requestedCategory);
        boolean isQuota = (exactCutoff != null && !requestedCategory.equalsIgnoreCase("SM"));
        int closingRank = cutoff.getCutoff(requestedCategory);
        String categoryUsed = isQuota ? requestedCategory : "SM";

        // Proximity calculation
        int proximityLevel = DistrictGraph.getProximityLevel(profile.getHomeDistrict(), college.getDistrict());
        String proximityStatus = DistrictGraph.getProximityLabel(proximityLevel);
        int distanceKm = DistrictGraph.getApproxKm(proximityLevel);

        // Probability calculation
        double ratio = (double) userRank / closingRank;
        int probabilityScore;
        String status;
        String explanation;

        if (ratio <= 0.85) {
            status = "SAFE";
            // Between 85% and 99%
            double factor = Math.max(0.0, 1.0 - (ratio / 0.85));
            probabilityScore = 85 + (int) Math.round(factor * 14.0);
            if (probabilityScore > 99) probabilityScore = 99;

            int margin = closingRank - userRank;
            explanation = String.format("High Likelihood: Your rank #%d is comfortably inside the %s cutoff (#%d) with a positive buffer of +%d ranks.",
                    userRank, categoryUsed, closingRank, margin);
        } else if (ratio <= 1.05) {
            status = "TARGET";
            // Between 50% and 84%
            double factor = (1.05 - ratio) / 0.20;
            probabilityScore = 50 + (int) Math.round(factor * 34.0);
            if (probabilityScore < 50) probabilityScore = 50;
            if (probabilityScore > 84) probabilityScore = 84;

            int diff = closingRank - userRank;
            String diffStr = diff >= 0 ? "+" + diff : String.valueOf(diff);
            explanation = String.format("Realistic Target: Your rank #%d is very close to the %s cutoff (#%d, diff: %s). Strongly recommend keeping in top 5 choices.",
                    userRank, categoryUsed, closingRank, diffStr);
        } else if (ratio <= 1.30) {
            status = "REACH";
            // Between 15% and 49%
            double factor = (1.30 - ratio) / 0.25;
            probabilityScore = 15 + (int) Math.round(factor * 34.0);
            if (probabilityScore < 15) probabilityScore = 15;
            if (probabilityScore > 49) probabilityScore = 49;

            explanation = String.format("Aspirational Reach: Your rank #%d is above the Round 2 cutoff (#%d). Worth including for potential mop-up/spot allotments.",
                    userRank, closingRank);
        } else {
            status = "UNLIKELY";
            probabilityScore = Math.max(5, (int) Math.round(15.0 / (ratio - 0.30)));
            explanation = String.format("Difficult: Your rank #%d is significantly higher than the %s cutoff (#%d).",
                    userRank, categoryUsed, closingRank);
        }

        if (proximityLevel == 0) {
            explanation += " Located conveniently in your home district (" + college.getDistrict() + ").";
        } else if (proximityLevel == 1) {
            explanation += " Located in neighboring district (" + college.getDistrict() + ").";
        }

        return new RecommendationResult(
                college,
                cutoff.getCourseName(),
                closingRank,
                categoryUsed,
                isQuota,
                userRank,
                probabilityScore,
                status,
                proximityStatus,
                proximityLevel,
                distanceKm,
                explanation
        );
    }
}
