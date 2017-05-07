package ie.secure.eai;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EAIHeadersTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void get_eai_header_name_used_to_send_webseal_user_in_response() {
		assertEquals(EAIHeaderConstants.EAI_USER_ID_HEADER_NAME, EAIHeaders.lookUp(EAIHeader.EAI_USER_ID_HEADER));
	}

}
