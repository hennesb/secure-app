package ie.secure.eai;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import mockit.internal.expectations.TestOnlyPhase;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import ie.secure.Identity;

@RunWith(JMockit.class)
public class AuthenticatorTest {
	
	private Authenticator authenticator;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;
	private static final String userEmail = "test@test.com";
	
	@Mocked
	private HttpServletRequest mockedReq;
	
	@Mocked
	private HttpServletResponse mockedResp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
		authenticator = new EAIAuthenticator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void behaviour_eai_user_header_added_to_response_when_valid_identity_passed() {		
		Identity.builder().withEmail(userEmail).build();
		new Expectations() {
			{
			   mockedResp.addHeader(EAIHeaders.lookUp(EAIHeader.EAI_USER_ID_HEADER), userEmail);		
			   times = 1;
			}
		};
		authenticator.authenticate(Identity.builder().withEmail(userEmail).build(), mockedResp);

	}
	
	@Test
	public void confirm_eai_user_header_added_to_response_is_not_null_when_valid_identity_passed() {		
		Identity.builder().withEmail(userEmail).build();
		authenticator.authenticate(Identity.builder().withEmail(userEmail).build(), resp);
		assertNotNull(resp.getHeader(EAIHeaders.lookUp(EAIHeader.EAI_USER_ID_HEADER)));
	}	

}
