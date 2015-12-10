package test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.acerete.exception.ReadingFileException;
import com.acerete.exception.WrongFormatException;
import com.acerete.input.Input;
import com.acerete.input.InputReader;
import com.acerete.service.ColorType;

public class TestInput extends SetupTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testNoFile() throws Exception {
		expectedEx.expect(ReadingFileException.class);
		expectedEx.expectMessage(InputReader.FILE_NOT_FOUND);
		INPUT_READER.readInputFromFile(NO_FILE);
	}
	
	@Test
	public void testNoInteger() throws Exception {
		expectedEx.expect(NumberFormatException.class);
		INPUT_READER.readInputFromFile(NO_INT);
	}
	
	@Test
	public void testNegativeInteger() throws Exception {
		expectedEx.expect(NumberFormatException.class);
		INPUT_READER.readInputFromFile(NEGATIVE_INT);
	}
	
	@Test
	public void testNoNTestCases() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.N_TEST_CASES_NOT_FOUND);
		INPUT_READER.readInputFromFile(NO_N_TEST_CASES_FILE);
	}
	
	@Test
	public void testNoNColors() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.N_COLORS_NOT_FOUND);
		INPUT_READER.readInputFromFile(NO_N_COLORS_FILE);
	}
	
	@Test
	public void testNoNCustomers() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.N_CUSTOMERS_NOT_FOUND);
		INPUT_READER.readInputFromFile(NO_N_CUSTOMERS_FILE);
	}
	
	@Test
	public void testLimits1() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.LIMITS_EXCEEDED);
		INPUT_READER.readInputFromFile(LIMITS_EXCEEDED_1_FILE);
	}
	
	@Test
	public void testLimits2() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.LIMITS_EXCEEDED);
		INPUT_READER.readInputFromFile(LIMITS_EXCEEDED_2_FILE);
	}
	
	@Test
	public void testLimits3() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.LIMITS_EXCEEDED);
		INPUT_READER.readInputFromFile(LIMITS_EXCEEDED_3_FILE);
	}
	
	@Test
	public void testNoNLikes() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.N_LIKES_NOT_FOUND);
		INPUT_READER.readInputFromFile(NO_N_LIKES_FILE);
	}
	
	@Test
	public void testWrongCustomerFormat() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.WRONG_CUSTOMER_FORMAT);
		INPUT_READER.readInputFromFile(WRONG_CUSTOMER_FORMAT_FILE);
	}
	
	@Test
	public void testWrongColorTypeId() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(ColorType.UNKNOWN_COLOR_TYPE_ID);
		INPUT_READER.readInputFromFile(WRONG_COLOR_TYPE_ID_FILE);
	}
	
	@Test
	public void testWrongColor() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.WRONG_COLOR);
		INPUT_READER.readInputFromFile(WRONG_COLOR_FILE);
	}
	
	@Test
	public void testAlreadyLikedColor() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.ALREADY_LIKED_COLOR);
		INPUT_READER.readInputFromFile(ALREADY_LIKED_COLOR_FILE);
	}
	
	@Test
	public void testAlreadyLikedMatte() throws Exception {
		expectedEx.expect(WrongFormatException.class);
		expectedEx.expectMessage(InputReader.ALREADY_LIKED_MATTE);
		INPUT_READER.readInputFromFile(ALREADY_LIKED_MATTE_FILE);
	}
	
	@Test
	public void testValidFile() throws Exception {
		Set<Input> inputSet = INPUT_READER.readInputFromFile(VALID_FILE);
		assertEquals(inputSet, VALID_INPUT);
	}

}
