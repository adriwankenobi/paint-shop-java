package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.acerete.input.Input;
import com.acerete.input.InputReader;
import com.acerete.input.FileInputReader;
import com.acerete.output.Output;
import com.acerete.output.OutputWriter;
import com.acerete.output.StdOutputWriter;
import com.acerete.service.Color;
import com.acerete.service.ColorType;
import com.acerete.service.Customer;
import com.acerete.shop.Shop;
import com.acerete.shop.PaintShop;


public class SetupTest {

	public static final String NO_FILE = "./test/files/no.txt";
	public static final String NO_INT = "./test/files/no_int.txt";
	public static final String NEGATIVE_INT = "./test/files/negative_int.txt";
	public static final String NO_N_TEST_CASES_FILE = "./test/files/no_n_test_cases.txt";
	public static final String NO_N_COLORS_FILE = "./test/files/no_n_colors.txt";
	public static final String NO_N_CUSTOMERS_FILE = "./test/files/no_n_customers.txt";
	public static final String LIMITS_EXCEEDED_1_FILE = "./test/files/limits_exceeded_1.txt";
	public static final String LIMITS_EXCEEDED_2_FILE = "./test/files/limits_exceeded_2.txt";
	public static final String LIMITS_EXCEEDED_3_FILE = "./test/files/limits_exceeded_3.txt";
	public static final String NO_N_LIKES_FILE = "./test/files/no_n_likes.txt";
	public static final String WRONG_CUSTOMER_FORMAT_FILE = "./test/files/wrong_customer_format.txt";
	public static final String WRONG_COLOR_TYPE_ID_FILE = "./test/files/wrong_color_type_id.txt";
	public static final String WRONG_COLOR_FILE = "./test/files/wrong_color.txt";
	public static final String ALREADY_LIKED_COLOR_FILE = "./test/files/already_liked_color.txt";
	public static final String ALREADY_LIKED_MATTE_FILE = "./test/files/already_liked_matte.txt";
	public static final String VALID_FILE = "./test/files/valid.txt";
	public static final String OTHER_VALID_FILE = "./test/files/other_valid.txt";
	
	public static final String VALID_OUTPUT_RESULT = "Case #1: 1 0 0 0 0 \r\nCase #2: IMPOSSIBLE\r\n";
	public static final String OTHER_VALID_OUTPUT_RESULT = "Case #1: 0 0 0 \r\n";
	
	public static final InputReader INPUT_READER = FileInputReader.INSTANCE;
	public static final OutputWriter OUTPUT_WRITER = StdOutputWriter.INSTANCE;
	
	public static final Shop SHOP = PaintShop.INSTANCE;
	
	public static final Set<Input> VALID_INPUT;
	public static final List<Output> VALID_OUTPUT;
	
	static {
		
		// Matches values on 'valid.txt'
		VALID_INPUT = new HashSet<Input>();
		
		Input input1 = new Input(0, 5);
		Customer customerA1 = new Customer();
		customerA1.addLike(new Color(1, ColorType.MATTE));
		Customer customerA2 = new Customer();
		customerA2.addLike(new Color(1, ColorType.GLOSSY));
		customerA2.addLike(new Color(2, ColorType.GLOSSY));
		Customer customerA3 = new Customer();
		customerA3.addLike(new Color(5, ColorType.GLOSSY));
		input1.addCustomer(customerA1);
		input1.addCustomer(customerA2);
		input1.addCustomer(customerA3);
		
		Input input2 = new Input(1, 1);
		Customer customerB1 = new Customer();
		customerB1.addLike(new Color(1, ColorType.GLOSSY));
		Customer customerB2 = new Customer();
		customerB2.addLike(new Color(1, ColorType.MATTE));
		input2.addCustomer(customerB1);
		input2.addCustomer(customerB2);
		
		VALID_INPUT.add(input1);
		VALID_INPUT.add(input2);
		
		// Matches values on 'VALID_OUTPUT_RESULT'
		VALID_OUTPUT = new ArrayList<Output>();
		
		Output output1 = new Output(0);
		output1.addColor(ColorType.MATTE);
		output1.addColor(ColorType.GLOSSY);
		output1.addColor(ColorType.GLOSSY);
		output1.addColor(ColorType.GLOSSY);
		output1.addColor(ColorType.GLOSSY);
		
		Output output2 = new Output(1);
		
		VALID_OUTPUT.add(output1);
		VALID_OUTPUT.add(output2);

	}
	
}
