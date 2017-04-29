package ie.secure;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CookieProducerTest {
	
	
	private CookieMonster cookieMonster;
	private Identity identity;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	    cookieMonster = new CookieMonster();
		cookieMonster.setTokenGenerator(new AppTokenProducer());
		identity = Identity.builder().withUserName("USER").build();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void create_jwt_token_cookie_check_not_null() {
	    Cookie jwtCookie = cookieMonster.jwtCookie(identity);
	    assertNotNull(jwtCookie);
	}

	@Test
	public void create_jwt_token_cookie_with_ttl_check_not_null() {
	    Cookie jwtCookie = cookieMonster.jwtCookie(identity, 1);
	    assertNotNull(jwtCookie);
	}
	
	@Test
	public void create_jwt_token_cookie_with_negative_ttl_check_not_null() {
	    Cookie jwtCookie = cookieMonster.jwtCookie(identity , -1);
	    assertNotNull(jwtCookie);
	}
	
	@Test
	public void create_jwt_token_cookie_with_zero_ttl_check_not_null() {
	    Cookie jwtCookie = cookieMonster.jwtCookie(identity , 0);
	    assertNotNull(jwtCookie);
	}
	
	@Test
	public void create_csrf_token_cookie_check_not_null() {
	    Cookie csrfCookie = cookieMonster.csrfCookie();
	    assertNotNull(csrfCookie);
	}
}
