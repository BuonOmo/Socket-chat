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
	
	public RequeteClient (String message) {
		this.message = message;
	}
}
