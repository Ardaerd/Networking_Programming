import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);

            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Text received is: " + new String(buffer,0, packet.getLength()));

                String returnString = "echo " + new String(buffer,0, packet.getLength());
                byte[] buffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length,address,port);
                socket.send(packet);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}