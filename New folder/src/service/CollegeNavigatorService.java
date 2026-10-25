package service;

import model.AllotmentCutoff;
import model.College;
import model.RecommendationResult;
import model.StudentProfile;
import repository.CollegeDatabase;
import repository.DistrictGraph;

import java.util.*;
import java.util.stream.Collectors;

public class CollegeNavigatorService {

    public List<RecommendationResult> getRecommendations(StudentProfile profile) {
        List<AllotmentCutoff> allCutoffs = CollegeDatabase.getAllCutoffs();
        List<RecommendationResult> results = new ArrayList<>();

        for (AllotmentCutoff cutoff : allCutoffs) {
            College college = CollegeDatabase.getCollege(cutoff.getCollegeCode());
            if (college == null) continue;

            // Filter by preferred college types if specified
            if (profile.getPreferredCollegeTypes() != null && !profile.getPreferredCollegeTypes().isEmpty()) {
                if (!profile.getPreferredCollegeTypes().contains(college.getType())) {
                    continue;
                }
            }

            // Filter by preferred courses if specified
            if (profile.getPreferredCourses() != null && !profile.getPreferredCourses().isEmpty()) {
                boolean matchCourse = false;
                for (String pref : profile.getPreferredCourses()) {
                    if (cutoff.getCourseName().equalsIgnoreCase(pref) ||
                        cutoff.getCourseName().toLowerCase().contains(pref.toLowerCase())) {
                        matchCourse = true;
                        break;
                    }
                }
                if (!matchCourse) continue;
            }

            // Filter by nearby if requested (Home or Adjacent district)
            int proximity = DistrictGraph.getProximityLevel(profile.getHomeDistrict(), college.getDistrict());
            if (profile.isNearbyOnly() && proximity > 1) {
                continue;
            }

            // Evaluate through prediction engine
            RecommendationResult rec = PredictionEngine.evaluate(profile, cutoff);

            // Filter out purely unreachable options unless student rank is extraordinarily high
            if (!"UNLIKELY".equals(rec.getStatus())) {
                results.add(rec);
            }
        }

        // Sort results
        String sortBy = profile.getSortBy() != null ? profile.getSortBy().toUpperCase() : "PROBABILITY";
        switch (sortBy) {
            case "DISTANCE":
                results.sort(Comparator.comparingInt(RecommendationResult::getProximityLevel)
                        .thenComparing(Comparator.comparingInt(RecommendationResult::getProbabilityScore).reversed()));
                break;
            case "CUTOFF_ASC":
                results.sort(Comparator.comparingInt(RecommendationResult::getCutoffRank));
                break;
            case "CUTOFF_DESC":
                results.sort(Comparator.comparingInt(RecommendationResult::getCutoffRank).reversed());
                break;
            case "RATING":
                results.sort(Comparator.comparingDouble((RecommendationResult r) -> r.getCollege().getRating()).reversed()
                        .thenComparing(Comparator.comparingInt(RecommendationResult::getProbabilityScore).reversed()));
                break;
            case "PROBABILITY":
            default:
                results.sort(Comparator.comparingInt(RecommendationResult::getProbabilityScore).reversed()
                        .thenComparingInt(RecommendationResult::getProximityLevel)
                        .thenComparingDouble((RecommendationResult r) -> r.getCollege().getRating()).reversed());
                break;
        }

        return results;
    }

    public List<College> searchColleges(String query, String district, String type) {
        return CollegeDatabase.getAllColleges().stream()
                .filter(c -> {
                    if (query != null && !query.isEmpty()) {
                        String q = query.toLowerCase();
                        if (!c.getName().toLowerCase().contains(q) && !c.getCode().toLowerCase().contains(q)) {
                            return false;
                        }
                    }
                    if (district != null && !district.isEmpty() && !district.equalsIgnoreCase("ALL")) {
                        if (!c.getDistrict().equalsIgnoreCase(district)) return false;
                    }
                    if (type != null && !type.isEmpty() && !type.equalsIgnoreCase("ALL")) {
                        if (!c.getType().equalsIgnoreCase(type)) return false;
                    }
                    return true;
                })
                .sorted(Comparator.comparingDouble(College::getRating).reversed())
                .collect(Collectors.toList());
    }

    public List<AllotmentCutoff> searchCutoffs(String collegeCode, String courseName) {
        return CollegeDatabase.getAllCutoffs().stream()
                .filter(c -> {
                    if (collegeCode != null && !collegeCode.isEmpty() && !collegeCode.equalsIgnoreCase("ALL")) {
                        if (!c.getCollegeCode().equalsIgnoreCase(collegeCode)) return false;
                    }
                    if (courseName != null && !courseName.isEmpty() && !courseName.equalsIgnoreCase("ALL")) {
                        if (!c.getCourseName().toLowerCase().contains(courseName.toLowerCase())) return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
