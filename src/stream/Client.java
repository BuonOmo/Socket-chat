/***
 * Client
 * TCP client 
 * Date: 10/01/04
 * Authors: ulysse
 */
package stream;

import java.io.*;
import java.net.*;



public class Client {

 
  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/
    public static void main(String[] args) throws IOException {

        Socket server = null;
        ObjectOutputStream oos = null;
        PrintStream socOut = null;
        BufferedReader stdIn = null;
        BufferedReader socIn = null;

        if (args.length != 2) {
          System.out.println("Usage: java EchoClient <EchoServer host> <EchoServer port>");
          System.exit(1);
        }

        try {
      	    // creation socket ==> connexion
      	    server = new Socket(args[0],new Integer(args[1]).intValue());
      	    oos = new ObjectOutputStream(server.getOutputStream());
		    socIn = new BufferedReader(
		    		          new InputStreamReader(server.getInputStream()));
		    
		    socOut= new PrintStream(oos);
		    stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to:"+ args[0]);
            System.exit(1);
        }
                             
        String line;
        while (true) {
        	line=stdIn.readLine();
        	RequeteClient rc = new RequeteClient(line);
        	if (line.equals(".")) break;
        	oos.writeObject(rc);
        	System.out.println(socIn.readLine());
        }
      socOut.close();
      socIn.close();
      stdIn.close();
      server.close();
    }
}


