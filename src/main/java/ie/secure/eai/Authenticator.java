package ie.secure.eai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.secure.Identity;

public interface Authenticator {
	/**
	 * 
	 * @param req
	 * @param resp
	 * Used when the request has sent data that allows the user to be authentciated
	 */
	public void authenticate(HttpServletRequest req, HttpServletResponse resp);
	
	/**
	 * 
	 * @param identity
	 * @param resp
	 * Used where you have a the identity extracted as want to use an application defined values in an Identity object to perform the authentication 
	 * 
	 */
	public void authenticate(Identity identity, HttpServletResponse resp);
	
	
	/**
	 * 
	 * @param identity
	 * @param resp
	 * Used where the identity is held in a thread local 
	 */
	public void authenticate(HttpServletResponse resp);

}
