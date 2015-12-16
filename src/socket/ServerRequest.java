package socket;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ServerRequest implements Serializable {
	
	public int type;
	String requete;
	public String message;
	public String user;
	public String receiver;
	
	ServerRequest(String requete)
	{
		this.requete = requete;
		String req[] = requete.split(" ");
		switch (req[0])
		{
			case ("SIGNIN") :
				type = 0;
				user = req[1];
				message = "server > all : "+req[1]+" signed in";
				break;
			case ("MESSAGE") :
				type = 1;
				user = req[2];
				receiver = req[4];
				message = user+" > "+receiver+" : ";
				for (int i = 6 ; i < req.length ; i++)
					message+= req[i];
				break;
			case ("SIGNOUT") :
				type = 2;
				user = req[1];
				message = "server > all : "+req[1]+" signed out";
				break;
			default : // ERROR
				type = 3;
				message = requete.substring("ERROR ".length());
				break;
		}
	}

	public String toString ()
	{
		return requete;
		//return "( type: "+type+" ; message: "+message+" ; user: "+user+" ; receiver: "+receiver+" )";
	}
	
	public int toInt ()
	{
		return type;
	}
}
