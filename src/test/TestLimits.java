package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.acerete.exception.WrongSolutionException;
import com.acerete.input.Input;
import com.acerete.output.Output;
import com.acerete.service.Color;
import com.acerete.service.ColorType;
import com.acerete.service.Customer;
import com.acerete.service.Service;
import com.acerete.service.SimplexService;

public class TestLimits extends SetupTest {
	
	public static final ColorType TEST_COLOR_TYPE = ColorType.MATTE;
	
	public static final int SMALL_DATASET_N_CASES = Input.N_CASES_MAX;
	public static final int SMALL_DATASET_N_COLORS = Color.N_COLORS_LIMIT;
	public static final int SMALL_DATASET_N_CUSTOMERS = Customer.N_CUSTOMERS_LIMIT;
	
	public static final int LARGE_DATASET_N_CASES = Input.N_CASES_LIMIT;
	public static final int LARGE_DATASET_N_COLORS = Color.N_COLORS_MAX;
	public static final int LARGE_DATASET_N_CUSTOMERS = Customer.N_CUSTOMERS_MAX;
	
	@Test
	public void testSmallDataset() throws Exception {
		List<Input> validInputList = generateInput(SMALL_DATASET_N_CASES, SMALL_DATASET_N_COLORS, SMALL_DATASET_N_CUSTOMERS);
		List<Output> expectedOutputList = generateOutput(SMALL_DATASET_N_CASES, SMALL_DATASET_N_COLORS, SMALL_DATASET_N_CUSTOMERS);
		doTest(validInputList, expectedOutputList);
	}
	
	@Test
	public void testLargeDataset() throws Exception {
		List<Input> validInputList = generateInput(LARGE_DATASET_N_CASES, LARGE_DATASET_N_COLORS, LARGE_DATASET_N_CUSTOMERS);
		List<Output> expectedOutputList = generateOutput(LARGE_DATASET_N_CASES, LARGE_DATASET_N_COLORS, LARGE_DATASET_N_CUSTOMERS);
		doTest(validInputList, expectedOutputList);
	}
	
	private List<Input> generateInput(int nCases, int nColors, int nCustomers) {
		List<Input> inputList = new ArrayList<Input>();
		for (int i = 0; i < nCases; i++) {
			Input input = new Input(i, nColors);
			for (int j = 0; j < nCustomers; j++) {
				Customer customer = new Customer();
				customer.addLike(new Color((j % nColors) + 1, TEST_COLOR_TYPE));
				input.addCustomer(customer);
			}
			inputList.add(input);
		}
		return inputList;
	}
	
	private List<Output> generateOutput(int nCases, int nColors, int nCustomers) {
		List<Output> outputList = new ArrayList<Output>();
		for (int i = 0; i < nCases; i++) {
			Output output = new Output(i);
			for (int j = 0; j < nColors; j++) {
				if (output.getColors().size() < nCustomers) {
					output.addColor(TEST_COLOR_TYPE);
				}
				else {
					output.addColor(ColorType.cheapest());
				}
			}
			outputList.add(output);
		}
		return outputList;
	}
	
	private void doTest(List<Input> inputList, List<Output> expectedOutputList) throws WrongSolutionException {
		List<Output> outputList = new ArrayList<Output>();
		for (Input input : inputList) {
			Service service = new SimplexService(input);
			Output output = service.serve();
			outputList.add(output);
		}
		
		assertEquals(outputList.size(), inputList.size());
		for (Output output : outputList) {
			assertTrue(expectedOutputList.contains(output));
		}
	}
}
