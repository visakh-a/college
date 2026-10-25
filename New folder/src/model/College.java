package model;

public class College {
    private String code;
    private String name;
    private String district;
    private String type; // "G" (Government/Aided), "N" (Govt Controlled/CAPE/IHRD/Univ), "S" (Private Self-Financing)
    private double rating;
    private String established;
    private String website;

    public College(String code, String name, String district, String type) {
        this.code = code;
        this.name = name;
        this.district = district;
        this.type = type;
        this.rating = getDefaultRating(type);
    }

    public College(String code, String name, String district, String type, double rating) {
        this(code, name, district, type, rating, "");
    }

    public College(String code, String name, String district, String type, double rating, String website) {
        this.code = code;
        this.name = name;
        this.district = district;
        this.type = type;
        this.rating = rating;
        this.website = website;
    }

    private double getDefaultRating(String type) {
        switch (type) {
            case "G": return 4.7;
            case "N": return 4.2;
            case "S": return 3.9;
            default: return 4.0;
        }
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getDistrict() { return district; }
    public String getType() { return type; }
    public double getRating() { return rating; }
    public String getWebsite() { return website; }

    public String getTypeName() {
        switch (type) {
            case "G": return "Government / Aided";
            case "N": return "Govt-Controlled / University";
            case "S": return "Private Self-Financing";
            default: return "Other";
        }
    }

    public String toJson() {
        return "{" +
                "\"code\":\"" + escape(code) + "\"," +
                "\"name\":\"" + escape(name) + "\"," +
                "\"district\":\"" + escape(district) + "\"," +
                "\"type\":\"" + escape(type) + "\"," +
                "\"typeName\":\"" + escape(getTypeName()) + "\"," +
                "\"rating\":" + rating + "," +
                "\"website\":\"" + escape(website) + "\"" +
                "}";
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"").replace("\n", " ").replace("\r", "");
    }
}
