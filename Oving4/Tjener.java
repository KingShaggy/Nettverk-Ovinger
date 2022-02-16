import java.io.IOException;
import java.net.*;
import java.util.StringTokenizer;

public class Tjener {

    final static int PORTNR = 6969;

    public static void main(String[] args) throws IOException {

        DatagramSocket tjenerSocket = new DatagramSocket(PORTNR);

        byte[] buffer = null;

        DatagramPacket request = null;
        DatagramPacket response = null;

        while(true){
            buffer = new byte[65535];

            request = new DatagramPacket(buffer, buffer.length);

            tjenerSocket.receive(request);
            System.out.println("Klient Oppkoblet");
            
            InetAddress address = request.getAddress();

            String equation = new String(buffer, 0, buffer.length);

            System.out.println("Equation Received: " + equation);

            equation = equation.trim();

            StringTokenizer st = new StringTokenizer(equation);

            int number1 = Integer.parseInt(st.nextToken());
            String operation = st.nextToken();
            int number2 = Integer.parseInt(st.nextToken());

            int answer = switch (operation) {
                case "+" -> number1 + number2;
                default -> number1 - number2;
            };

            String answerString = Integer.toString(answer);
            buffer = answerString.getBytes();
            
            response = new DatagramPacket(buffer, buffer.length, address,request.getPort());
            tjenerSocket.send(response);
        }
    }
}