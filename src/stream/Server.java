/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;

public class Server  {

 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	static void doService(Socket clientSocket) {
    	  try {
    		ObjectInputStream ois = new ObjectInputStream (clientSocket.getInputStream());
    		ObjectOutputStream oos = new ObjectOutputStream (clientSocket.getOutputStream());
    		RequeteClient rc;
    		RequeteServeur rs;
    		while (true) {
    		  rc = (RequeteClient) ois.readObject();
			  System.out.println("Requete du client : "+rc);
			  String requete;
			  switch (rc.type)
			  {
			  	case 0 :
			  		requete = "SIGNIN "+rc.user;
			  		break;
			  	case 1 :
			  		requete = "MESSAGE FROM "+rc.user+" TO "+rc.receiver+" CONTENT "+rc.message;
			  		break;
			  	case 2 :
			  		requete = "SIGNOUT "+rc.user;
			  		break;
			  	default :
			  		requete = "ERROR "+rc.errorMessage;
			  		break;
			  }
			  rs = new RequeteServeur(requete);
			  System.out.println("Réponse du serveur : "+rs);
			  oos.writeObject(rs);
		}
    	} catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
        }
       }
	
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/
       public static void main(String args[]){ 
        ServerSocket listenSocket;
        
  	if (args.length != 1) {
          System.out.println("Usage: java EchoServer <EchoServer port>");
          System.exit(1);
  	}
	try {
		listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
		while (true) {
			Socket clientSocket = listenSocket.accept();
			System.out.println("connexion from:" + clientSocket.getInetAddress());
			doService(clientSocket);
		}
        } catch (Exception e) {
            System.err.println("Error in Server:" + e);
        }
      }
  }
