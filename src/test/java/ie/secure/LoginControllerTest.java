package ie.secure;

import static org.junit.Assert.*;

import java.util.UUID;




import org.junit.Before;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;



public class LoginControllerTest {
	

	private MockHttpServletRequest req;
	
	private MockHttpServletResponse resp;
	
	private Model model;	
	private LoginController controller;
	
	private CookieMonster monster ;

	@Before
	public void setUp() throws Exception {
		bootstrapExecutionEnvironment();		
		bootstrapController();
	}
	
	private void bootstrapExecutionEnvironment(){
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
		model = new ExtendedModelMap();		
	}
	
	private void bootstrapController(){	
		controller = new LoginController();
		controller.setProducer(new AppTokenProducer());
		monster = new CookieMonster();
		monster.setTokenGenerator(new AppTokenProducer());
		controller.setCookieMonster(monster);
		controller.setUserService(new UserService());
	}


	@Test
	public void login_page_get_request_return_view() {		
		assertEquals(controller.loginPage(req, resp, model), AbstractLoginViews.INDEX_VIEW);
	}
	
   
	@Test
	public void home_page_landing_get_request_return_view(){
		assertEquals(controller.displayPage(req, resp, model), AbstractLoginViews.JWT_VIEW);
	}
	
	@Test
	public void content_page_get_request_return_view(){
		assertEquals(controller.contentTest(req, resp, model), AbstractLoginViews.INDEX_VIEW);
	}
	
	@Test
	public void jwt_cookie_in_request_model_and_jwt_view_returned(){
		req.setCookies(monster.jwtCookie(Identity.builder().withUserName("USER").build()));
		assertEquals(controller.displayPage(req, resp, model), AbstractLoginViews.JWT_VIEW);
	}
	
	@Test
	public void checkIndexOf(){
		String invalid = "ABC3006DDD";
		assertNotEquals(invalid.indexOf("3006"), -1);
	}

}
