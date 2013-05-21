package constants;

/**
 * Constants used for for API need to be added here
 * 
 * @author tejawork
 *
 */
public final class APIConstants {
	
	public static final String LOTAME_TOP_AUDIENCE_URL = "https://api.lotame.com/audstats/reports/topAudiences";
	public static final String LOTAME_AUTH_URL = "https://api.lotame.com";
	
	public static final String LOTAME_API_CONFIG_USERNAME_PROP = "api.lotame.username";
	public static final String LOTAME_API_CONFIG_PASSWORD_PROP = "api.lotame.password";
	public static final String LOTAME_JSON_TOP_AUDIENCE_STAT_VAR = "stat";
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String CONTENT_TYPE_FORM_URL_ENCODED = "application/x-www-form-urlencoded";
	public static final String ACCEPT_HEADER = "Accept";
	public static final String JSON_HEADER_TYPE = "application/json";
	
	public static final String JSON_ERROR_KEY = "error";
	public static final String JSON_500_MESSAGE = "an error occured on the server.";
}
