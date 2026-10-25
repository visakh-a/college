package model;

import java.util.ArrayList;
import java.util.List;

public class StudentProfile {
    private int rank;
    private String category; // "SM", "EZ", "MU", "LA", "DV", "VK", "BH", "BX", "KN", "KU", "SC", "ST", "EW", "FW"
    private String homeDistrict;
    private List<String> preferredCourses;
    private List<String> preferredCollegeTypes; // "G", "N", "S"
    private boolean nearbyOnly;
    private String sortBy; // "PROBABILITY", "CUTOFF_ASC", "CUTOFF_DESC", "DISTANCE", "RATING"

    public StudentProfile() {
        this.rank = 10000;
        this.category = "SM";
        this.homeDistrict = "Ernakulam";
        this.preferredCourses = new ArrayList<>();
        this.preferredCollegeTypes = new ArrayList<>();
        this.nearbyOnly = false;
        this.sortBy = "PROBABILITY";
    }

    public StudentProfile(int rank, String category, String homeDistrict) {
        this();
        this.rank = rank;
        this.category = (category != null && !category.isEmpty()) ? category : "SM";
        this.homeDistrict = (homeDistrict != null && !homeDistrict.isEmpty()) ? homeDistrict : "Ernakulam";
    }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getHomeDistrict() { return homeDistrict; }
    public void setHomeDistrict(String homeDistrict) { this.homeDistrict = homeDistrict; }

    public List<String> getPreferredCourses() { return preferredCourses; }
    public void setPreferredCourses(List<String> preferredCourses) { this.preferredCourses = preferredCourses; }

    public List<String> getPreferredCollegeTypes() { return preferredCollegeTypes; }
    public void setPreferredCollegeTypes(List<String> preferredCollegeTypes) { this.preferredCollegeTypes = preferredCollegeTypes; }

    public boolean isNearbyOnly() { return nearbyOnly; }
    public void setNearbyOnly(boolean nearbyOnly) { this.nearbyOnly = nearbyOnly; }

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
}
