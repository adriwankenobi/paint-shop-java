package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.acerete.input.Input;
import com.acerete.output.Output;
import com.acerete.service.Service;
import com.acerete.service.SimplexService;

public class TestService extends SetupTest {
	
	@Test
	public void testSomeCustomers() throws Exception {
		
		List<Output> outputList = new ArrayList<Output>();
		for (Input input : VALID_INPUT) {
			Service service = new SimplexService(input);
			Output output = service.serve();
			outputList.add(output);
		}
		
		assertEquals(outputList.size(), VALID_INPUT.size());
		for (Output output : outputList) {
			assertTrue(VALID_OUTPUT.contains(output));
		}
	}

}
