package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AllotmentCutoff {
    private String collegeCode;
    private String courseName;
    private String collegeType;
    private Map<String, Integer> categoryCutoffs;
    private Map<String, Integer> otherCategories; // e.g. FW, MG, CC, PD, PT, SD, XS, CB

    public AllotmentCutoff(String collegeCode, String courseName, String collegeType) {
        this.collegeCode = collegeCode;
        this.courseName = courseName;
        this.collegeType = collegeType;
        this.categoryCutoffs = new HashMap<>();
        this.otherCategories = new HashMap<>();
    }

    public void addCutoff(String category, int rank) {
        if (rank > 0) {
            categoryCutoffs.put(category.toUpperCase().trim(), rank);
        }
    }

    public void addOther(String key, int rank) {
        if (rank > 0) {
            otherCategories.put(key.toUpperCase().trim(), rank);
        }
    }

    public String getCollegeCode() { return collegeCode; }
    public String getCourseName() { return courseName; }
    public String getCollegeType() { return collegeType; }
    public Map<String, Integer> getCategoryCutoffs() { return Collections.unmodifiableMap(categoryCutoffs); }
    public Map<String, Integer> getOtherCategories() { return Collections.unmodifiableMap(otherCategories); }

    public Integer getCutoff(String category) {
        if (category == null) return categoryCutoffs.get("SM");
        String cat = category.toUpperCase().trim();
        if (categoryCutoffs.containsKey(cat)) {
            return categoryCutoffs.get(cat);
        }
        if (otherCategories.containsKey(cat)) {
            return otherCategories.get(cat);
        }
        // Fallback to State Merit (SM) if specific category cutoff is not applicable / not available
        return categoryCutoffs.get("SM");
    }

    public Integer getExactCutoff(String category) {
        if (category == null) return null;
        String cat = category.toUpperCase().trim();
        if (categoryCutoffs.containsKey(cat)) return categoryCutoffs.get(cat);
        if (otherCategories.containsKey(cat)) return otherCategories.get(cat);
        return null;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"collegeCode\":\"").append(escape(collegeCode)).append("\",");
        sb.append("\"courseName\":\"").append(escape(courseName)).append("\",");
        sb.append("\"collegeType\":\"").append(escape(collegeType)).append("\",");
        sb.append("\"cutoffs\":{");
        int i = 0;
        for (Map.Entry<String, Integer> entry : categoryCutoffs.entrySet()) {
            if (i > 0) sb.append(",");
            sb.append("\"").append(entry.getKey()).append("\":").append(entry.getValue());
            i++;
        }
        sb.append("},");
        sb.append("\"others\":{");
        int j = 0;
        for (Map.Entry<String, Integer> entry : otherCategories.entrySet()) {
            if (j > 0) sb.append(",");
            sb.append("\"").append(entry.getKey()).append("\":").append(entry.getValue());
            j++;
        }
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}
