import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException,
            ClassNotFoundException, InterruptedException {
        // get the localhost IP address, if server is running on
        // some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader input = null;
        PrintWriter output = null;

        for (int i = 0; i < 5; i++) {
            // establish socket connection to server
            socket = new Socket(host.getHostName(),5000);

            // write to socket using PrintWriter
            output = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("Sending request to Socket Server...");

            if (i == 4)
                output.println("exit");
            else
                output.println(" " + i);

            // Read the Server response message
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            input = new BufferedReader(inputStreamReader);

            String message = input.readLine();
            System.out.println("Message: " + message);

            // close resources
            output.close();
            inputStreamReader.close();
            input.close();
            Thread.sleep(1000);
        }
    }
}