package ie.secure;

import java.util.UUID;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CookieMonster {
	
	
	
    @Autowired
    private AppTokenProducer tokenGenerator; 
    
	
	public void setTokenGenerator(AppTokenProducer tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}

	public Cookie jwtCookie(Identity identity, long timetolive){
		Cookie cookie = new Cookie(CookieConfig.JWT_COOKIE_NAME, tokenGenerator.createJWTToken(identity, timetolive));		
		configureSecureCookie(cookie);
		cookie.setHttpOnly(true);
		return cookie;
	}
	
	public Cookie jwtCookie(Identity identity){
		Cookie cookie = new Cookie(CookieConfig.JWT_COOKIE_NAME, tokenGenerator.createJWTToken(identity));		
		configureSecureCookie(cookie);
		cookie.setHttpOnly(true);
		return cookie;
	}
	
	public Cookie csrfCookie(){
		Cookie cookie = new Cookie(CookieConfig.XSRF_COOKIE_NAME, UUID.randomUUID().toString());
		configureSecureCookie(cookie);
		return cookie;
	}
	
	private Cookie configureSecureCookie(Cookie cookie){
		cookie.setMaxAge(-1);
		//cookie.setSecure(true);
		//cookie.setDomain(DOMAIN);
		return cookie;
	}

}
