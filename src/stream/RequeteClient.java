package stream;

import java.io.Serializable;

public class RequeteClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String message;
	public String username;
	public String otherUsername;
	public int type;
	public boolean valide = true;
	
	RequeteClient (String requete) {
		
		String req [] = requete.split(" ");
		switch (req[0])
		{
			case ("CONNECT") :
				type = 0;
				if (req[1].length() < 4)
				{
					System.err.println ("Identifiant inexistant ou trop court");
					valide = false;
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
					message = req[3];
				}
				else
				{
					System.err.println("Requete d'envoi non valide");
					valide=false;
				}
				break;
			case ("QUIT") :
				type=2;
				break;
			default :
				valide=false;
				break;
		} // Fin de switch
	} // Fin du constructeur
	
	RequeteClient(String requete, String aUser)
	{
		this(requete);
		username = aUser;
	}
}
