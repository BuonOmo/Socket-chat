/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

package socket;

import java.io.*;
import java.net.*;
import java.util.Map;

public class Server  {
	public static Map<String, Socket> allSockets;
 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	static void doService(ClientThread t) {
    	  try {
    		ObjectInputStream ois = new ObjectInputStream (t.clientSocket.getInputStream());
    		ObjectOutputStream oos = new ObjectOutputStream (t.clientSocket.getOutputStream());
    		ClientRequest rc;
    		ServerRequest rs;
    		while (true) {
    		  rc = (ClientRequest) ois.readObject();
			  System.out.println("Requete du client : "+rc);
			  String requete;
			  switch (rc.type)
			  {
			  	case 0 :
			  		if (!allSockets.containsKey(rc.user))
			  		{
			  			allSockets.put(rc.user, t.clientSocket);
			  			requete = "SIGNIN "+rc.user;
			  			t.connect();
			  		}
			  		else
			  		{
			  			requete = "ERROR Utilisateur déjà existant, veuillez choisir un autre pseudo";
			  			
			  		}
			  		break;
			  	case 1 :
			  		requete = "MESSAGE FROM "+rc.user+" TO "+rc.receiver+" CONTENT "+rc.message;
			  		break;
			  	case 2 :
			  		requete = "SIGNOUT "+rc.user;
			  		t.disconnect();
			  		allSockets.remove(rc.user);
			  		break;
			  	default :
			  		requete = "ERROR "+rc.errorMessage;
			  		break;
			  }
			  rs = new ServerRequest(requete);
			  System.out.println("Réponse du serveur : "+rs);
			  switch (rc.receiver)
			  {
			  	case "all" :
			  		for (Socket s : allSockets.values()) {
			  			oos = new ObjectOutputStream(s.getOutputStream());
			  			oos.writeObject(rs);
			  		}
			  	case "" :
		  			oos = new ObjectOutputStream(t.clientSocket.getOutputStream());
		  			oos.writeObject(rs);
			  	default :
		  			oos = new ObjectOutputStream(allSockets.get(rs.receiver).getOutputStream());
		  			oos.writeObject(rs);	
			  }
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
		try
		{
			listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
			while (true)
			{
				Socket clientSocket = listenSocket.accept();
				System.out.println("connexion from:" + clientSocket.getInetAddress());
				ClientThread ct = new ClientThread(clientSocket);
				ct.start();
			}
		}
		catch (Exception e)
		{
		    System.err.println("Error in Server:" + e);
		}
	}
}
