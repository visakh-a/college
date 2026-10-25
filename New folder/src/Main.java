import controller.ApiServer;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        try {
            ApiServer server = new ApiServer(port);
            server.start();

            // Keep main thread alive
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down KEAM College Navigator Server...");
                server.stop();
            }));

        } catch (Exception e) {
            System.err.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
