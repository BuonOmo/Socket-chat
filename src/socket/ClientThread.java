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
	
	public Socket clientSocket;
	private boolean connected = false;
	public ObjectOutputStream oos;
	
	ClientThread(Socket s) {
		this.clientSocket = s;
	}
	
	public boolean disconnect()
	{
		return connected = false;
	}
	
	public boolean connect()
	{
		return connected = true;
	}
	
 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() {
	  while (true)
	  {
		  Server.doService(this);
	  }
	}
  }