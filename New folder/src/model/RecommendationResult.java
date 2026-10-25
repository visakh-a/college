package model;

public class RecommendationResult {
    private College college;
    private String courseName;
    private int cutoffRank;
    private String categoryUsed;
    private boolean isQuotaSeat;
    private int userRank;
    private int rankDifference; // positive means user rank is better than cutoff
    private int probabilityScore; // 0 - 100%
    private String status; // "SAFE", "TARGET", "REACH"
    private String proximityStatus; // "Home District", "Adjacent District", "Kerala State"
    private int proximityLevel; // 0, 1, 2
    private int distanceKmApprox;
    private String explanation;

    public RecommendationResult(College college, String courseName, int cutoffRank,
                                String categoryUsed, boolean isQuotaSeat, int userRank,
                                int probabilityScore, String status, String proximityStatus,
                                int proximityLevel, int distanceKmApprox, String explanation) {
        this.college = college;
        this.courseName = courseName;
        this.cutoffRank = cutoffRank;
        this.categoryUsed = categoryUsed;
        this.isQuotaSeat = isQuotaSeat;
        this.userRank = userRank;
        this.rankDifference = cutoffRank - userRank;
        this.probabilityScore = probabilityScore;
        this.status = status;
        this.proximityStatus = proximityStatus;
        this.proximityLevel = proximityLevel;
        this.distanceKmApprox = distanceKmApprox;
        this.explanation = explanation;
    }

    public College getCollege() { return college; }
    public String getCourseName() { return courseName; }
    public int getCutoffRank() { return cutoffRank; }
    public String getCategoryUsed() { return categoryUsed; }
    public boolean isQuotaSeat() { return isQuotaSeat; }
    public int getUserRank() { return userRank; }
    public int getRankDifference() { return rankDifference; }
    public int getProbabilityScore() { return probabilityScore; }
    public String getStatus() { return status; }
    public String getProximityStatus() { return proximityStatus; }
    public int getProximityLevel() { return proximityLevel; }
    public int getDistanceKmApprox() { return distanceKmApprox; }
    public String getExplanation() { return explanation; }

    public String toJson() {
        return "{" +
                "\"college\":" + college.toJson() + "," +
                "\"courseName\":\"" + escape(courseName) + "\"," +
                "\"cutoffRank\":" + cutoffRank + "," +
                "\"categoryUsed\":\"" + escape(categoryUsed) + "\"," +
                "\"isQuotaSeat\":" + isQuotaSeat + "," +
                "\"userRank\":" + userRank + "," +
                "\"rankDifference\":" + rankDifference + "," +
                "\"probabilityScore\":" + probabilityScore + "," +
                "\"status\":\"" + escape(status) + "\"," +
                "\"proximityStatus\":\"" + escape(proximityStatus) + "\"," +
                "\"proximityLevel\":" + proximityLevel + "," +
                "\"distanceKmApprox\":" + distanceKmApprox + "," +
                "\"explanation\":\"" + escape(explanation) + "\"" +
                "}";
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"").replace("\n", " ").replace("\r", "");
    }
}
