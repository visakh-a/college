package model;

public class Course {
    private String name;
    private String category; // e.g. "Computer Science & IT", "Electronics & Electrical", "Mechanical & Allied", "Civil", "Specialized"

    public Course(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }

    public String toJson() {
        return "{\"name\":\"" + escape(name) + "\",\"category\":\"" + escape(category) + "\"}";
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}
