// A Java program for a Server
import java.net.*;
import java.io.*;
 
public class server {
    // initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    // constructor with port
    public server(int port) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            int sum = 0;
            
            // reads message from client until "0" is sent
            while (!line.equals("0")) {
                try {
                    line = in.readUTF();
                    System.out.println("Client says: " + line);
                    sum += Integer.parseInt(line);
                } catch (Exception e) {;}
            }

            System.out.println("Sum: " + sum);
            System.out.println("Closing connection");
            
            // close connection
            socket.close();
            in.close();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exit Server");
            System.exit(0);
        }
    }

    public static void main(String[] argc) {
        new server(5000);
    }
}