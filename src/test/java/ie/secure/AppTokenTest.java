package ie.secure;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.jsonwebtoken.Claims;

public class AppTokenTest {
	
	private static final String ANY_STRING ="1010AB";
	private AppTokenProducer producer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	

	@Before
	public void setUp() throws Exception {
		producer = new AppTokenProducer();	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void get_A_NonNull_jwt_token() {	
		assertNotNull(producer.createJWTToken(Identity.builder().withName(ANY_STRING).build(), 1000));
	}

	
	@Test
	public void get_A_NonNull_jwt_token_no_ttl_set() {	
		assertNotNull(producer.createJWTToken(Identity.builder().withName(ANY_STRING).build()));
	} 
	
	@Test
	public void get_A_NonNull_csrf_token() {	
		assertNotNull(producer.createCSRFToken());
	}	
	
	@Test
	public void generate_token_read_it_back_check_details_not_null(){
		String jwtToken = producer.createJWTToken(Identity.builder().withName(ANY_STRING).build());
		Claims claim = producer.extractJWTTokenDetails(jwtToken);
		assertNotNull(claim);
	}

}
