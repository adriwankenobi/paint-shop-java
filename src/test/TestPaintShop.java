package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPaintShop extends SetupTest {
	
	@Test
	public void testSomeCustomers() throws Exception {
		String result = SHOP.satisfy(VALID_FILE);
		assertEquals(VALID_OUTPUT_RESULT, result);
	}
	
	@Test
	public void testOtherCustomers() throws Exception {
		String result = SHOP.satisfy(OTHER_VALID_FILE);
		assertEquals(OTHER_VALID_OUTPUT_RESULT, result);
	}

}
