package ie.secure;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.xml.bind.DatatypeConverter;
import java.util.UUID;    
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@Controller
public class LoginController {
	
	private static final String UNAUTHENTICATED = "UNAUTHENTICATED";
	
	@Autowired
	private AppTokenProducer producer;
	
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setProducer(AppTokenProducer producer) {
		this.producer = producer;
	}

	public void setCookieMonster(CookieMonster cookieMonster) {
		this.cookieMonster = cookieMonster;
	}

	@Autowired
	private CookieMonster cookieMonster;
		
	
	@RequestMapping(value = AppControllerUrls.ADMIN_URL, method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, Model model) {
		setToken(resp);
		setModel(model);
        return AbstractLoginViews.INDEX_VIEW;
	}
	

	
	@RequestMapping(value = {"/display", "/" }, method = RequestMethod.GET)
	public String displayPage(HttpServletRequest req, HttpServletResponse resp, Model model) {
		String jwtToken = getJWTCookie(req);
		if ( jwtToken != null){
		   Claims claim = parseJWT(jwtToken);
		   setJWTModelAttributes(model, claim);
		}
		return AbstractLoginViews.JWT_VIEW;
	}
	
	@RequestMapping(value = "/contentTest", method = RequestMethod.GET)
	public String contentTest(HttpServletRequest req, HttpServletResponse resp, Model model) {
		setCsrfToken(resp);
		return "index";
	}
	
	private String getJWTCookie(HttpServletRequest req){
        String value = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals(CookieConfig.JWT_COOKIE_NAME)) {
			   value = cookie.getValue();
		    }
		  }
		}
		return value;
	}
	
	private void setCsrfToken(HttpServletResponse resp){
		resp.addCookie(csrfCookie());
	}
	
	private void setToken(HttpServletResponse resp) {
		String email = userService.email(whoami());
		Identity identity = Identity.builder().withUserName(whoami()).withEmail(email).build();
		resp.addCookie(cookieMonster.jwtCookie(identity));
	}
	
	private Cookie csrfCookie(){
		return cookieMonster.csrfCookie();
	}
	
	private String whoami() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return auth.getName();
		}else{
			return UNAUTHENTICATED;
		}
		
	}
	
	private Claims parseJWT(String jwt) {
		return producer.extractJWTTokenDetails(jwt);	
	}
	
	private void setJWTModelAttributes(Model model, Claims claims){
		model.addAttribute("id", claims.getId());
		model.addAttribute("subject", claims.getSubject());
		model.addAttribute("issuer", claims.getIssuer());
		model.addAttribute("expiration", claims.getExpiration());		
	}
	
	private void setModel(Model model){
		model.addAttribute("title", "Test for json we token");
		model.addAttribute("message", "In dev tools check the cookie ");
		model.addAttribute("userName", whoami());		
	}
}
