package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestOutput extends SetupTest {
	
	@Test
	public void testSomeOutput() throws Exception {
		String result = OUTPUT_WRITER.writeOutputIntoStdout(VALID_OUTPUT);
		assertEquals(VALID_OUTPUT_RESULT, result);
	}

}
