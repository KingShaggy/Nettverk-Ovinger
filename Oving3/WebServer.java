import java.io.*;
import java.net.*;

class WebServer {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 55555;

    ServerSocket tjener = new ServerSocket(PORTNR);
    Socket socket = tjener.accept();

    InputStreamReader leseforbindelse = new InputStreamReader(socket.getInputStream());
    BufferedReader br = new BufferedReader(leseforbindelse);

    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
    printWriter.print("HTTP/1.1 200 OK\r\n");
    printWriter.print("Content-Type: text/html;charset=utf-8\r\n");
    printWriter.print("\r\n");
    printWriter.println("<html>");
    printWriter.println("<body>");
    printWriter.println("<h1>hei</h1>");
    printWriter.println("<ul>");

    String linje = br.readLine();
    while(!linje.equals("")){
      System.out.println(linje);
      printWriter.println("<li>" + linje + "</li>");
      linje = br.readLine();
    }
    printWriter.println("</ul>");
    printWriter.println("</body>");
    printWriter.println("</html>");

    printWriter.close();
    leseforbindelse.close();
    br.close();
    socket.close();
    tjener.close();
  }
}