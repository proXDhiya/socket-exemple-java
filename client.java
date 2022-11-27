// Code for client side
import java.io.*;
import java.net.*;

public class client {
    // init socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    // constructor to put ip address and port
    public client(String addr, int port) {
        // establish a connection
        try {
            socket = new Socket(addr, port);
            System.out.println("Client Connected");
 
            // takes input from terminal
            input = new DataInputStream(System.in);
 
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exit Client");
            System.exit(0);
        }

        // string to read message from input
        String line = "";

        // keep reading until 0 is input
        while (!line.equals("0")) {
            try {
                System.out.print("Msg: ");
                line = input.readLine();
                out.writeUTF(line);
            } catch (Exception e) {;}
        }

        System.out.println("Exit Client");

        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        } catch (Exception e) {;}
    }

    public static void main(String[] argc) {
        new client("127.0.0.1", 5000);
    }
}
