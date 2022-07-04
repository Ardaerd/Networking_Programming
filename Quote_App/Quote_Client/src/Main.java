import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {

        String hostname = "localhost";
        int port = 17;

        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();

            while (true) {
                DatagramPacket request = new DatagramPacket(new byte[1],1,address,port);
                socket.send(request);

                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String quote = new String(buffer,0,response.getLength());

                System.out.println(quote);
                System.out.println();

                Thread.sleep(10000);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}