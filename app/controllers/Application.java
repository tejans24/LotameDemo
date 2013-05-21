package controllers;

import play.mvc.*;
import views.html.*;

/**
 * Application controller for home page
 * 
 * @author tejawork
 *
 */
public class Application extends Controller {
	
	/**
	 * route: /
	 * <p>
	 * 
	 * Returns the index home page
	 * 
	 * @return
	 */
    public static Result index() {	
        return ok(index.render());
    }

}
