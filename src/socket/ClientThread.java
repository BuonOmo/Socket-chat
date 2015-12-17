/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package socket;

import java.io.*;
import java.net.*;
import java.util.List;

public class ClientThread
	extends Thread {
	
	private Socket clientSocket;
	public ObjectOutputStream oos;
	public static List<ClientThread> allThreads;
	
	ClientThread(Socket s) {
		this.clientSocket = s;
		allThreads.add(this);
	}

 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() {
    	  try {
      		ObjectInputStream ois = new ObjectInputStream (clientSocket.getInputStream());
      		oos = new ObjectOutputStream (clientSocket.getOutputStream());
      		ClientRequest rc;
      		ServerRequest rs;
    		while (true) {
      		  rc = (ClientRequest) ois.readObject();
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
  			  rs = new ServerRequest(requete);
  			  System.out.println("Réponse du serveur : "+rs);

  			  for (ClientThread sub : allThreads)
  			  {
  				  sub.oos.writeObject(rs);
  			  }
    		}
    	} catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
        }
       }
  
  }