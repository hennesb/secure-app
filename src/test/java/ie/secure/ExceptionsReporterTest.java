package ie.secure;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExceptionsReporterTest {
	
	private ExceptionsReporter exceptionsReporter;
	private String tokenValueOne = "2017-08-09:00:00 error-code=99 clientid=67877";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		exceptionsReporter = new ExceptionsReporter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tokenizeOne() {
		fail("Not yet implemented"); // TODO
	}

}
