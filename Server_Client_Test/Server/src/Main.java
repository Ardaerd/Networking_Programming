import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();
            System.out.println("Connected...");

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (true) {
                String msgFromClient = bufferedReader.readLine();
                System.out.println("Client: " + msgFromClient);

                bufferedWriter.write(" Message Received");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                if (msgFromClient.equalsIgnoreCase("bye"))
                    break;
            }

            socket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
