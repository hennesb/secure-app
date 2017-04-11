package ie.secure;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.UUID;    
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@Controller
public class LoginController {
	
	private static final String APP = "secure-apps";
	private static final String COOKIE_NAME = "JWTCOOKIE";
	private static final int TTL = 300000;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest req, HttpServletResponse resp) {
		setToken(resp);
        return onUI();
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String displayPage(HttpServletRequest req, HttpServletResponse resp, Model model) {
		parseJWT(getCookie(req) , model);
		return "jwt-display";
	}
	
	
	public String getCookie(HttpServletRequest req){
		Cookie[] cookies = req.getCookies();
        String value = "";
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals("JWTCOOKIE")) {
			   value = cookie.getValue();
		    }
		  }
		}
		return value;
	}
	
	private void setToken(HttpServletResponse resp){
		resp.addCookie(jwtCookie());
	}
	
	private Cookie jwtCookie(){
		Cookie cookie = new Cookie(COOKIE_NAME, createJWT(TTL));
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(-1);
		return cookie;
	}
	
	private ModelAndView onUI(){
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Test for json we token");
		model.addObject("message", "In dev tools check the cookie ");
		model.addObject("userName", whoami());
		model.setViewName("login-display");
		return model;		
	}

	private String whoami(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	private void setTTL(JwtBuilder builder, long ttlMillis, long now){
	    if (ttlMillis >= 0) {
	    long expMillis = now + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }		
	}
	
	private String createJWT(long ttlMillis) {
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("some-secret-key");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
	                                .setIssuedAt(now)
	                                .setSubject(whoami())
	                                .setIssuer(APP)
	                                .signWith(signatureAlgorithm, signingKey);

	    setTTL(builder, ttlMillis, nowMillis);
	    return builder.compact();
	}
	
	private void parseJWT(String jwt, Model model) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary("some-secret-key"))
	       .parseClaimsJws(jwt).getBody();
	    model.addAttribute("id", claims.getId());
	    model.addAttribute("subject", claims.getSubject());
	    model.addAttribute("issuer", claims.getIssuer());
	    model.addAttribute("expiration", claims.getExpiration());
	
	}
}
