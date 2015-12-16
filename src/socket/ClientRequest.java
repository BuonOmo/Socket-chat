package socket;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientRequest implements Serializable {
	
	public String requete;
	public String message;
	public String user;
	public String receiver;
	public String errorMessage;
	public int type;
	
	ClientRequest (String requete) {
		this.requete = requete;
		String req [] = requete.split(" ");
		switch (req[0])
		{
			case ("CONNECT") :
				type = 0;
				if (req[1].length() < 4)
				{
					errorMessage = "Identifiant"+req[1]+"inexistant ou trop court";
				}
				else
				{
					user = req[1];
				}
				break;
				
			case ("SENDTO") :
				type=1;
				if (req.length == 4 && req[2].equals("CONTENT")&&(req[1].equals("all") || req[1].length() > 3))
				{
					receiver = req[1];
					message = "";
					for (int i = 3 ; i < req.length ; i++)
						message+= req[i];
				}
				else
				{
					errorMessage = "Requete d'envoi de message non valide.";
				}
				break;
			case ("QUIT") :
				type=2;
				break;
			default :
				type = 3;
				errorMessage = "Requete mal formée.";
				break;
		} // Fin de switch
	} // Fin du constructeur
	
	ClientRequest(String requete, String aUser)
	{
		this(requete);
		user = aUser;
	}
	
	public String toString ()
	{
		return requete;
		//return "( type: "+type+" ; message: "+message+" ; user: "+user+" ; receiver: "+receiver+" ; errorMessage: "+errorMessage+" )";
	}
	
	public int toInt ()
	{
		return type;
	}
}