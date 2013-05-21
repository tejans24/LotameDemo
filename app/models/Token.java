package models;

import java.util.Date;

/**
 * Token object is used to store Authntication token for API calls 
 * and the creation date. 
 * 
 * @author tejawork
 *
 */
public class Token {
	
	public String tokenCode;
	
	public Date createdTime;

	/**
	 * Constructor for Token class.
	 * 
	 * @param tokenCode token code
	 * @param createdTime creation time of the token
	 */
	public Token(String tokenCode, final Date createdTime){
		this.tokenCode = tokenCode;
		this.createdTime = createdTime;
	}
}
