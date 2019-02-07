package ca.utoronto.utm.mcs;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void handle(ServerSocket server) throws IOException {
        Socket client = server.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        /* TODO: TASK FOR 2nd MEMBER
           Display the current path of the web page.
           For example if the user is at localhost:8080/somepath here then the
           page should display "/somepath" in the <h1> tag.

           To get this data you will need to string split the first line read
           from the BufferedReader in. It will be formatted: GET /path HTTP/1.1
           simply replace the content of the <h1> with the middle element
           
           This change should be made in a branch made off of release/1.0 called 
           feature/DisplayPathHeader.
        */



        
        String text = lst.get(0).split(" ")[1];
           
        String body = "<h1>"+text+"</h1>";

        out.write("HTTP/1.1 200 OK\r\n");
        out.write(String.format("Content-Length: %d\r\n", body.length()));
        out.write("Content-Type: text/html\r\n");
        out.write("\r\n");
        out.write(body);

        out.close();
        in.close();
        client.close();
    }

    public static void main(String[] args)
    {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8080);
            System.out.println("Starting server ...");
        } catch (IOException e) {
            System.out.println("An exception occured while starting the server. Exiting.");
            System.exit(1);
        }

        try {
            while (true) {
                handle(server);
            }
        } catch (IOException e) {
            System.err.println("An IOExeception occured: " + e.toString());
        }
    }
}
