import java.io.*;
import java.net.*;

public class KlientTraad extends Thread{
    
    Socket forbindelse;
    ServerSocket tjener;

    public KlientTraad(Socket forbindelse, ServerSocket tjener){
        this.forbindelse = forbindelse;
        this.tjener = tjener;
    }

    public void run(){
            try (/* �pner str�mmer for kommunikasjon med klientprogrammet */
                InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream())) {
                BufferedReader leseren = new BufferedReader(leseforbindelse);
                PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

                /* Sender innledning til klienten */
                skriveren.println("Hei, du har kontakt med tjenersiden!");
                skriveren.println("Skriv hva du vil, s� skal jeg gjenta det, avslutt med linjeskift.");

                /* Mottar data fra klienten */
                String enLinje = leseren.readLine();  // mottar en linje med tekst
                while (enLinje != null) {  // forbindelsen p� klientsiden er lukket
                    System.out.println("En klient skrev: " + enLinje);
                    String[] symboler = enLinje.split(" ");
                    int svar = symboler[1].equals("+") ? Integer.parseInt(symboler[0]) + Integer.parseInt(symboler[2]) : Integer.parseInt(symboler[0]) - Integer.parseInt(symboler[2]);
                    skriveren.println("Svar: " + svar);  // sender svar til klienten
                    enLinje = leseren.readLine();
                }

                /* Lukker forbindelsen */
                leseren.close();
                skriveren.close();
                forbindelse.close();
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
