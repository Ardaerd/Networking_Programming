import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Waiting for requests...");
            while (true) {
                new Echoer(serverSocket.accept()).start();
                System.out.println("New client connected...");
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}