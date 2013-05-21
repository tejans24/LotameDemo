package util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import models.Token;

import play.Logger;
import play.Play;
import play.cache.Cache;
import play.libs.WS;
import play.libs.WS.Response;
import play.libs.WS.WSRequestHolder;
import constants.APIConstants;

/**
 * Util class that can be used 
 * 
 * @author tejawork
 *
 */
public class AuthorizationUtil {
	
	private static final int TOKEN_EXP_MINS = 30;
	private static final int TIME_OUT_IN_MILLI_SECS = 30000;
	
	private static final String CACHE_API_VAR = "lotame_token";

	
	/**
	 * Utility method that connects to Lotame API to get a proper 
	 * Authentication token. Uses the configured username and password 
	 * from the config files to post the form encoded data. 
	 * 
	 * @return a {@link Token}  object with a newly acquired token code from 
	 * the API and created date/time.
	 */
	public static Token getNewToken(){
		
		String username = Play.application().configuration().
    			getString(APIConstants.LOTAME_API_CONFIG_USERNAME_PROP);
    	String password = Play.application().configuration().
    			getString(APIConstants.LOTAME_API_CONFIG_PASSWORD_PROP);
    	
    	Logger.debug("Connecting to the Lotame API using username : " + username);
    	Logger.debug("Post to " + APIConstants.LOTAME_AUTH_URL);

    	WSRequestHolder req = WS.url(APIConstants.LOTAME_AUTH_URL);
    	req.setTimeout(TIME_OUT_IN_MILLI_SECS);
    	req.setHeader(APIConstants.CONTENT_TYPE_HEADER, APIConstants.CONTENT_TYPE_FORM_URL_ENCODED);
    	Response response = req.post("email=" + username + "&password=" + password).get();
    	
    	Logger.debug("Token : " + response.getBody());

		return new Token(response.getBody(), new Date());
	}
	
	/**
	 * Method can be used to check if a token has expired.
	 * 
	 * @param token a {@link Token} that need to be validated
	 * @return true if expired, false if its still valid
	 */
	public static Boolean hasTokenExpired(Token token){
		
		Boolean expired = false;
		Long diffMinutes = AuthorizationUtil.differenceInMins(token.createdTime, new Date());
		
		Logger.debug("It has been " + diffMinutes + " minutes since token has been created");
		
		if(diffMinutes >= TOKEN_EXP_MINS){
			expired = true;
		}
		
		return expired;
	}
	
	/**
	 * This method calculates time differences between two date
	 * objects and returns it in minutes. 
	 * <p>
	 * Assumption: endTime always comes after startTime.
	 * 
	 * @param startTime a start time
	 * @param endTime an end time
	 * @return difference between start time and end time in minutes
	 */
	public static Long differenceInMins(Date startTime, Date endTime){
        
		long diffMillSecs = endTime.getTime() - startTime.getTime();
        long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diffMillSecs);
        
        return diffMinutes;
	}
	
	/**
	 * Returns a token if it exists cache. If it doesn't 
	 * exist in cache, a new token is created, stored in 
	 * cache and returned. 
	 * 
	 * @return a existing {@link Token} in cache if it exists or a new 
	 * token if it doesn't exist.
	 */
	public static Token getToken(){
    	
    	Token token = (Token) Cache.get(CACHE_API_VAR);
    	
    	//token expiration check. If it expired, a new token
    	//is created.
    	//if cache is cleared for some reason, no worries, just get a new token
    	if(token == null || AuthorizationUtil.hasTokenExpired(token)){
    		Cache.remove(CACHE_API_VAR);
    		token = AuthorizationUtil.getNewToken();
    		Cache.set(CACHE_API_VAR, token);
    	}
    	
    	return token;
	}
}
