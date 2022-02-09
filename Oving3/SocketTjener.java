import java.io.*;
import java.net.*;

class SocketTjener {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;

    ServerSocket tjener = new ServerSocket(PORTNR);
    while(true){
      System.out.println("Logg for tjenersiden. N� venter vi...");
      Socket forbindelse = tjener.accept();
      KlientTraad klientTråd = new KlientTraad(forbindelse, tjener);
      klientTråd.start();
    }
  }
}
