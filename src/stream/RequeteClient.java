package stream;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RequeteClient implements Serializable {
	
	public String message;
	public String username;
	public String otherUsername;
	public String errorMessage;
	public int type;
	
	RequeteClient (String requete) {
		
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
					username = req[1];
				}
				break;
				
			case ("SENDTO") :
				type=1;
				if (req.length == 4 && req[2].equals("CONTENT")&&(req[1].equals("all") || req[1].length() > 3))
				{
					otherUsername = req[1];
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
	
	RequeteClient(String requete, String aUser)
	{
		this(requete);
		username = aUser;
	}
}
