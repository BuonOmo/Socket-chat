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
        ObjectInputStream ois = null;
        BufferedReader stdIn = null;

        if (args.length != 2) {
          System.out.println("Usage: java EchoClient <EchoServer host> <EchoServer port>");
          System.exit(1);
        }

        try {
      	    // creation socket ==> connexion
      	    server = new Socket(args[0],new Integer(args[1]).intValue());
      	    oos = new ObjectOutputStream(server.getOutputStream());
      	    ois = new ObjectInputStream(server.getInputStream());
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
        RequeteClient rc;
        RequeteServeur rs;
        while (true) {
        	line=stdIn.readLine();
        	rc = new RequeteClient(line);
        	if (line.equals(".")) break;
        	oos.writeObject(rc);
        	try 
        	{
				rs = (RequeteServeur) ois.readObject();
				switch (rs.type)
				{
				  	case 0 : // SIGNIN
				  		System.out.println(rs.message);
				  		break;
				  	case 1 : // MESSAGE
				  		System.out.println(rs.message);
				  		break;
				  	case 2 : // SIGNOUT
				  		System.out.println(rs.message);
				  		break;
				  	default :
				  		System.err.println(rs.message);
				  		break;
				}
			}
        	catch (ClassNotFoundException e)
        	{
				e.printStackTrace();
			}
        }
        

      stdIn.close();
      server.close();
    }
}


