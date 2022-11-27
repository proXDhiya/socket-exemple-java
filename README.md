# socket-exemple-java

## What is a Socket?
- it's a way to talk to other computers using **standard Unix file descriptors**. In Unix, every I/O action is done by writing or reading a file descriptor. A file descriptor is just an ***integer associated with an open file and it can be a network connection, a text file, a terminal, or something else.***
- To a programmer, a socket looks and behaves much like a **low-level file descriptor**. This is because commands such as `read()` and `write()` work with sockets in the same way they do with files and pipes.

---

## Where is Socket Used?
- A Unix Socket is used in a **client-server application framework**. A server is a process that performs some functions on request from a client. Most of the application-level protocols like *FTP*, *SMTP*, and *POP3* make use of sockets to establish connection between client and server and then for exchanging data.

---

## Socket types:
There are four types of sockets available to the users. The first two are most commonly used and the last two are rarely used.

1. ***Stream Sockets*** − Delivery in a networked environment is guaranteed. If you send through the stream socket three items "A, B, C", they will arrive in the same order − "A, B, C". These sockets use TCP (Transmission Control Protocol) for data transmission. If delivery is impossible, the sender receives an error indicator. Data records do not have any boundaries.
2. ***Datagram Sockets*** − Delivery in a networked environment is not guaranteed. They're connectionless because you don't need to have an open connection as in Stream Sockets − you build a packet with the destination information and send it out. They use UDP (User Datagram Protocol).
3. ***Raw Sockets***.
4. ***Sequenced Packet Sockets***.

---

## Socket in java:
- To connect to another machine we need a socket connection. A socket connection means *the two machines have information about each other’s* network location (IP Address) and TCP port. `The java.net.Socket` class represents a Socket. To open a socket: 

```java
Socket socket = new Socket(“127.0.0.1”, 5000);
```

- The first argument – IP address of Server. ( **127.0.0.1**  is the IP address of localhost, where code will run on the single stand-alone machine).
- The second argument – TCP Port. (Just a number representing which application to run on a server. For example, HTTP runs on port 80. Port number can be **from 0 to 65535**)

---

## Server Code:

```java
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
```

---

## Client code:

```java
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
```
---

### Compile and Run:

```bash
javac server.java
javac client.java

java server
java client
```

---

### Output of Server:

```bash
Server started
Waiting for a client ...
Client accepted
Client says: 1
Client says: 2
Client says: 0
sum: 3
Closing connection
```
---

### Output of Client:

```bash
Client Connected
Msg: 1
Msg: 2
Msg: 0
Exit Client
```

---

## References:
- [tutorialspoint.com/unix_sockets](https://www.tutorialspoint.com/unix_sockets/what_is_socket.htm#)
- [geeksforgeeks.org/socket-programming-in-java](https://www.geeksforgeeks.org/socket-programming-in-java/)

---

## Thank You!
made by [**@proXDhiya**](www.github.com/proXDhiya)
