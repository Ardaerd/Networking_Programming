import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<>();
    private Random random;

    public Main(int port) throws SocketException {
        socket = new DatagramSocket(port);
        random = new Random();
    }

    public static void main(String[] args) {
        String quoteFile = "Quotes.txt";
        int port = 17;

        try {
            Main server = new Main(port);
            server.loadQuotesFromFile(quoteFile);
            server.service();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void service() throws IOException {
        while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1],1);
            socket.receive(request);

            String quote = getRandomQuote();
            byte[] buffer = quote.getBytes();

            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();

            DatagramPacket response = new DatagramPacket(buffer, buffer.length,clientAddress,clientPort);
            socket.send(response);
        }
    }

    private void loadQuotesFromFile(String quoteFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
        String aQuote;

        while ((aQuote = reader.readLine()) != null)
            listQuotes.add(aQuote);

        reader.close();
    }

    private String getRandomQuote() {
        int randomIndex = random.nextInt(listQuotes.size());
        String randomQuote = listQuotes.get(randomIndex);
        return randomQuote;
    }
}