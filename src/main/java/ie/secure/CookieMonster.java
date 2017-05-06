package ie.secure;

import java.util.UUID;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CookieMonster {
	
	private String domain;
	
    private String getDomain() {
    	if (domain == null){
    		return "localhost";
    	}else{
		   return domain;
    	}
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Autowired
    private AppTokenProducer tokenGenerator; 
    
	
	public void setTokenGenerator(AppTokenProducer tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}

	public Cookie jwtCookie(Identity identity, long timetolive){
		Cookie cookie = new Cookie(CookieConfig.JWT_COOKIE_NAME, tokenGenerator.createJWTToken(identity, timetolive));		
		configureSecureCookie(cookie);
		cookie.setHttpOnly(false);
		return cookie;
	}
	
	public Cookie jwtCookie(Identity identity){
		Cookie cookie = new Cookie(CookieConfig.JWT_COOKIE_NAME, tokenGenerator.createJWTToken(identity));		
		configureSecureCookie(cookie);
		cookie.setHttpOnly(false);
		return cookie;
	}
	
	public Cookie csrfCookie(){
		Cookie cookie = new Cookie(CookieConfig.XSRF_COOKIE_NAME, UUID.randomUUID().toString());
		configureSecureCookie(cookie);
		cookie.setHttpOnly(true);
		return cookie;
	}
	
	private Cookie configureSecureCookie(Cookie cookie){
		cookie.setMaxAge(-1);
		cookie.setSecure(true);
		cookie.setDomain(getDomain());
		return cookie;
	}

}
