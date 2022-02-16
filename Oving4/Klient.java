import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Klient {

    final static int PORTNR = 6969;
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Skriv inn host navn: ");
        String hostName = sc.nextLine();
        InetAddress address = InetAddress.getByName(hostName);

        DatagramSocket klientSocket = new DatagramSocket();
        byte[] buffer = null;

        while(true){
            System.out.println("Skriv inn regnestykket på formen \"2 + 2\": ");
            String equation = sc.nextLine();

            buffer = new byte[65535];
            buffer = equation.getBytes();

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, PORTNR);

            klientSocket.send(request);

            buffer = new byte[65535];

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            klientSocket.receive(response);

            System.out.println("Svar: " + new String(response.getData()));
        }
    }

}
