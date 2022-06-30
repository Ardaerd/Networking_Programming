import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    // Static serverSocket variable
    private static ServerSocket serverSocket;
    // Socket server port on which it will listen
    private static int port = 5000;

    public static void main(String[] args) {
        try {
            // Create the serverSocket server object
            serverSocket = new ServerSocket(port);

            // keep listens indefinitely until receives 'exit' call or program terminates
            while (true) {
                System.out.println("Waiting for the client request");
                // creating socket and waiting for client connection
                Socket socket = serverSocket.accept();
                System.out.println("Connected...");

                // Read from socket
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader input = new BufferedReader(inputStreamReader);
                // Write to client
                PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

                String message = input.readLine();
                System.out.println("Message Receive: " + message);

                output.println("Hi Client " + message);

                // Close resources
                inputStreamReader.close();
                input.close();
                output.close();
                socket.close();
                if (message.equalsIgnoreCase("exit"))
                    break;
            }

            System.out.println("Shutting down Socket server!!");
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}