package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import model.AllotmentCutoff;
import model.College;
import model.Course;
import model.RecommendationResult;
import model.StudentProfile;
import repository.CollegeDatabase;
import repository.DistrictGraph;
import service.CollegeNavigatorService;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class ApiServer {
    private final int port;
    private final CollegeNavigatorService service;
    private HttpServer server;

    public ApiServer(int port) {
        this.port = port;
        this.service = new CollegeNavigatorService();
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);

        // API Endpoints
        server.createContext("/api/districts", new DistrictsHandler());
        server.createContext("/api/courses", new CoursesHandler());
        server.createContext("/api/colleges", new CollegesHandler(service));
        server.createContext("/api/cutoffs", new CutoffsHandler(service));
        server.createContext("/api/predict", new PredictHandler(service));

        // Static files handler (serves web/)
        server.createContext("/", new StaticFileHandler());

        server.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(8));
        server.start();
        System.out.println("==================================================");
        System.out.println("KEAM College Navigator Server running at: http://localhost:" + port);
        System.out.println("Decision Support System for KEAM Admission Planning");
        System.out.println("==================================================");
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }

    private static Map<String, String> parseQueryParams(String query) {
        Map<String, String> params = new HashMap<>();
        if (query == null || query.isEmpty()) return params;
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                if (idx > 0) {
                    String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                    String val = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
                    params.put(key, val);
                } else if (!pair.isEmpty()) {
                    params.put(URLDecoder.decode(pair, StandardCharsets.UTF_8), "");
                }
            } catch (Exception ignored) {}
        }
        return params;
    }

    private static void sendJsonResponse(HttpExchange exchange, int statusCode, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    // --- Handlers ---

    private static class DistrictsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 204, "");
                return;
            }
            List<String> districts = DistrictGraph.getAllDistricts();
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < districts.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append("\"").append(districts.get(i)).append("\"");
            }
            sb.append("]");
            sendJsonResponse(exchange, 200, sb.toString());
        }
    }

    private static class CoursesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 204, "");
                return;
            }
            Collection<Course> courses = CollegeDatabase.getAllCourses();
            StringBuilder sb = new StringBuilder("[");
            int i = 0;
            for (Course c : courses) {
                if (i > 0) sb.append(",");
                sb.append(c.toJson());
                i++;
            }
            sb.append("]");
            sendJsonResponse(exchange, 200, sb.toString());
        }
    }

    private static class CollegesHandler implements HttpHandler {
        private final CollegeNavigatorService service;
        public CollegesHandler(CollegeNavigatorService service) { this.service = service; }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 204, "");
                return;
            }
            Map<String, String> query = parseQueryParams(exchange.getRequestURI().getQuery());
            String q = query.get("q");
            String district = query.get("district");
            String type = query.get("type");

            List<College> colleges = service.searchColleges(q, district, type);
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < colleges.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(colleges.get(i).toJson());
            }
            sb.append("]");
            sendJsonResponse(exchange, 200, sb.toString());
        }
    }

    private static class CutoffsHandler implements HttpHandler {
        private final CollegeNavigatorService service;
        public CutoffsHandler(CollegeNavigatorService service) { this.service = service; }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 204, "");
                return;
            }
            Map<String, String> query = parseQueryParams(exchange.getRequestURI().getQuery());
            String college = query.get("college");
            String course = query.get("course");

            List<AllotmentCutoff> cutoffs = service.searchCutoffs(college, course);
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < cutoffs.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(cutoffs.get(i).toJson());
            }
            sb.append("]");
            sendJsonResponse(exchange, 200, sb.toString());
        }
    }

    private static class PredictHandler implements HttpHandler {
        private final CollegeNavigatorService service;
        public PredictHandler(CollegeNavigatorService service) { this.service = service; }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 204, "");
                return;
            }

            StudentProfile profile = new StudentProfile();
            Map<String, String> params = parseQueryParams(exchange.getRequestURI().getQuery());

            if (params.containsKey("rank")) {
                try { profile.setRank(Integer.parseInt(params.get("rank"))); } catch (Exception ignored) {}
            }
            if (params.containsKey("category")) {
                profile.setCategory(params.get("category"));
            }
            if (params.containsKey("district")) {
                profile.setHomeDistrict(params.get("district"));
            }
            if (params.containsKey("courses")) {
                String c = params.get("courses");
                if (!c.isEmpty() && !c.equalsIgnoreCase("ALL")) {
                    profile.setPreferredCourses(Arrays.asList(c.split(",")));
                }
            }
            if (params.containsKey("types")) {
                String t = params.get("types");
                if (!t.isEmpty() && !t.equalsIgnoreCase("ALL")) {
                    profile.setPreferredCollegeTypes(Arrays.asList(t.split(",")));
                }
            }
            if (params.containsKey("nearby")) {
                profile.setNearbyOnly(Boolean.parseBoolean(params.get("nearby")));
            }
            if (params.containsKey("sort")) {
                profile.setSortBy(params.get("sort"));
            }

            List<RecommendationResult> recs = service.getRecommendations(profile);

            int safeCount = 0;
            int targetCount = 0;
            int reachCount = 0;

            for (RecommendationResult r : recs) {
                if ("SAFE".equals(r.getStatus())) safeCount++;
                else if ("TARGET".equals(r.getStatus())) targetCount++;
                else if ("REACH".equals(r.getStatus())) reachCount++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("\"studentRank\":").append(profile.getRank()).append(",");
            sb.append("\"category\":\"").append(profile.getCategory()).append("\",");
            sb.append("\"homeDistrict\":\"").append(profile.getHomeDistrict()).append("\",");
            sb.append("\"totalEligible\":").append(recs.size()).append(",");
            sb.append("\"safeCount\":").append(safeCount).append(",");
            sb.append("\"targetCount\":").append(targetCount).append(",");
            sb.append("\"reachCount\":").append(reachCount).append(",");
            sb.append("\"recommendations\":[");
            for (int i = 0; i < recs.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(recs.get(i).toJson());
            }
            sb.append("]");
            sb.append("}");

            sendJsonResponse(exchange, 200, sb.toString());
        }
    }

    private static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path == null || path.equals("/") || path.isEmpty()) {
                path = "/index.html";
            }

            File file = new File("web" + path);
            if (!file.exists() || file.isDirectory()) {
                file = new File("web/index.html");
            }

            String contentType = getContentType(file.getName());
            byte[] bytes = Files.readAllBytes(file.toPath());

            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }

        private String getContentType(String filename) {
            if (filename.endsWith(".html")) return "text/html; charset=UTF-8";
            if (filename.endsWith(".css")) return "text/css; charset=UTF-8";
            if (filename.endsWith(".js")) return "application/javascript; charset=UTF-8";
            if (filename.endsWith(".json")) return "application/json; charset=UTF-8";
            if (filename.endsWith(".png")) return "image/png";
            if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) return "image/jpeg";
            if (filename.endsWith(".svg")) return "image/svg+xml";
            if (filename.endsWith(".ico")) return "image/x-icon";
            return "text/plain; charset=UTF-8";
        }
    }
}
