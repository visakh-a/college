package repository;

import java.util.*;

public class DistrictGraph {
    private static final Map<String, List<String>> ADJACENCY = new HashMap<>();
    private static final List<String> ALL_DISTRICTS = Arrays.asList(
            "Thiruvananthapuram", "Kollam", "Pathanamthitta", "Alappuzha", "Kottayam",
            "Idukki", "Ernakulam", "Thrissur", "Palakkad", "Malappuram",
            "Kozhikode", "Wayanad", "Kannur", "Kasaragod"
    );

    static {
        ADJACENCY.put("Thiruvananthapuram", Arrays.asList("Kollam"));
        ADJACENCY.put("Kollam", Arrays.asList("Thiruvananthapuram", "Pathanamthitta", "Alappuzha"));
        ADJACENCY.put("Pathanamthitta", Arrays.asList("Kollam", "Alappuzha", "Kottayam", "Idukki"));
        ADJACENCY.put("Alappuzha", Arrays.asList("Kollam", "Pathanamthitta", "Kottayam", "Ernakulam"));
        ADJACENCY.put("Kottayam", Arrays.asList("Alappuzha", "Pathanamthitta", "Idukki", "Ernakulam"));
        ADJACENCY.put("Idukki", Arrays.asList("Pathanamthitta", "Kottayam", "Ernakulam", "Thrissur"));
        ADJACENCY.put("Ernakulam", Arrays.asList("Alappuzha", "Kottayam", "Idukki", "Thrissur"));
        ADJACENCY.put("Thrissur", Arrays.asList("Ernakulam", "Idukki", "Palakkad", "Malappuram"));
        ADJACENCY.put("Palakkad", Arrays.asList("Thrissur", "Malappuram"));
        ADJACENCY.put("Malappuram", Arrays.asList("Thrissur", "Palakkad", "Kozhikode", "Wayanad"));
        ADJACENCY.put("Kozhikode", Arrays.asList("Malappuram", "Wayanad", "Kannur"));
        ADJACENCY.put("Wayanad", Arrays.asList("Malappuram", "Kozhikode", "Kannur"));
        ADJACENCY.put("Kannur", Arrays.asList("Kozhikode", "Wayanad", "Kasaragod"));
        ADJACENCY.put("Kasaragod", Arrays.asList("Kannur"));
    }

    public static List<String> getAllDistricts() {
        return ALL_DISTRICTS;
    }

    public static int getProximityLevel(String fromDistrict, String toDistrict) {
        if (fromDistrict == null || toDistrict == null) return 3;
        String from = normalize(fromDistrict);
        String to = normalize(toDistrict);
        if (from.equalsIgnoreCase(to)) return 0; // Same District

        List<String> neighbors = ADJACENCY.get(from);
        if (neighbors != null) {
            for (String n : neighbors) {
                if (n.equalsIgnoreCase(to)) return 1; // Adjacent
            }
        }

        // BFS for shortest path distance
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> dist = new HashMap<>();
        queue.add(from);
        dist.put(from, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int d = dist.get(curr);
            List<String> currNeighbors = ADJACENCY.get(curr);
            if (currNeighbors != null) {
                for (String neighbor : currNeighbors) {
                    if (!dist.containsKey(neighbor)) {
                        dist.put(neighbor, d + 1);
                        if (neighbor.equalsIgnoreCase(to)) {
                            return d + 1;
                        }
                        queue.add(neighbor);
                    }
                }
            }
        }
        return 3;
    }

    public static String getProximityLabel(int level) {
        switch (level) {
            case 0: return "Home District";
            case 1: return "Adjacent District";
            case 2: return "Nearby Region";
            default: return "Kerala State";
        }
    }

    public static int getApproxKm(int level) {
        switch (level) {
            case 0: return 20;
            case 1: return 65;
            case 2: return 140;
            default: return 260;
        }
    }

    private static String normalize(String name) {
        if (name == null) return "";
        String s = name.trim();
        for (String d : ALL_DISTRICTS) {
            if (d.equalsIgnoreCase(s)) return d;
        }
        // Handle common variations
        if (s.equalsIgnoreCase("Trivandrum") || s.equalsIgnoreCase("TVM")) return "Thiruvananthapuram";
        if (s.equalsIgnoreCase("Cochin") || s.equalsIgnoreCase("Kochi")) return "Ernakulam";
        if (s.equalsIgnoreCase("Calicut")) return "Kozhikode";
        if (s.equalsIgnoreCase("Trichur")) return "Thrissur";
        if (s.equalsIgnoreCase("Alleppey")) return "Alappuzha";
        return s;
    }
}
