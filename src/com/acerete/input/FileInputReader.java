package com.acerete.input;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.acerete.exception.ColorTypeNotFoundException;
import com.acerete.exception.ReadingFileException;
import com.acerete.exception.WrongFormatException;
import com.acerete.service.Color;
import com.acerete.service.ColorType;
import com.acerete.service.Customer;

public class FileInputReader implements InputReader {
	
	// SINGLETON
	public final static InputReader INSTANCE = new FileInputReader();
	private FileInputReader() {}
	
	@Override
	public Set<Input> readInputFromFile(String fileName) throws ReadingFileException, WrongFormatException {
		
		Set<Input> inputSet = null;
		
		try {
			
			URL resource = getClass().getClassLoader().getResource(fileName);
			
			if (resource == null) {
				throw new ReadingFileException(FILE_NOT_FOUND);
			}
			
			File file = new File(resource.toURI());
			
			Scanner scanner = new Scanner(file);
			
			inputSet = readFromFile(scanner);

			scanner.close();

		} catch (IOException | URISyntaxException e) {
			throw new ReadingFileException(e.getMessage());
		}
		
		return inputSet;
	}
	
	/**
	 * PRIVATE HELPER METHODS
	 */
	private Set<Input> readFromFile(Scanner scanner) throws WrongFormatException {
		
		Set<Input> inputSet = new HashSet<Input>();
		
		if (!scanner.hasNextLine()) {
			throw new WrongFormatException(N_TEST_CASES_NOT_FOUND);
		}
		
		int nCases = parsePositiveInt(scanner.nextLine(), Input.N_CASES_MIN, Input.N_CASES_MAX);
		
		for (int i = 0; i < nCases; i++) {
			Input input = readCase(i, scanner, nCases);
			inputSet.add(input);
		}
		
		return inputSet;
	}
	
	private Input readCase(int caseId, Scanner scanner, int nCases) throws WrongFormatException {
		
		if (!scanner.hasNextLine()) {
			throw new WrongFormatException(N_COLORS_NOT_FOUND);
		}
		
		int nColors = parsePositiveInt(scanner.nextLine(), Color.N_COLORS_MIN, Color.N_COLORS_MAX);
			
		if (!scanner.hasNextLine()) {
			throw new WrongFormatException(N_CUSTOMERS_NOT_FOUND);
		}
				
		int nCustomers = parsePositiveInt(scanner.nextLine(), Customer.N_CUSTOMERS_MIN, Customer.N_CUSTOMERS_MAX);
		
		if (nCases > Input.N_CASES_LIMIT && 
				(nColors > Color.N_COLORS_LIMIT || nCustomers > Customer.N_CUSTOMERS_LIMIT)) {
			throw new WrongFormatException(LIMITS_EXCEEDED);
		}
		
		Input input = new Input(caseId, nColors);
				
		int totalLikes = 0;
		
		while (nCustomers > 0) {
			Customer customer = readCustomer(scanner, nColors);
			totalLikes += customer.getLikesSize();
			
			if (totalLikes > MAX_LIKES_PER_CASE) {
				throw new WrongFormatException(TOO_MANY_LIKES);
			}
			
			input.addCustomer(customer);
			nCustomers--;
		}
				
		return input;
	}
	
	private Customer readCustomer(Scanner scanner, int nColors) throws WrongFormatException {
		
		if (!scanner.hasNextLine()) {
			throw new WrongFormatException(N_LIKES_NOT_FOUND);
		}
		
		String[] line = scanner.nextLine().split(DELIM);
		int nLikes = parsePositiveInt(line[0], 1);
		
		if (line.length != nLikes * 2 + 1) {
			throw new WrongFormatException(WRONG_CUSTOMER_FORMAT);
		}
		
		Customer customer = new Customer();
				
		while (nLikes > 0) {
			Color color = readColor(line, nLikes);
			
			if (color.getId() <= 0 || color.getId() > nColors) {
				throw new WrongFormatException(WRONG_COLOR);
			}
			
			if (customer.likes(color)) {
				throw new WrongFormatException(ALREADY_LIKED_COLOR);
			}
			
			if (color.getType() == ColorType.MATTE && customer.likesType(ColorType.MATTE)) {
				throw new WrongFormatException(ALREADY_LIKED_MATTE);
			}
			
			customer.addLike(color);
			nLikes--;
		}
				
		return customer;
	}
	
	private Color readColor(String[] line, int idx) throws WrongFormatException {
		
		int colorId = parsePositiveInt(line[line.length - 2 * idx], 1);
		int colorTypeId = parsePositiveInt(line[line.length - 2 * idx + 1], 0);
		
		try {
			return new Color(colorId, ColorType.getById(colorTypeId));
		}
		catch (ColorTypeNotFoundException e) {
			throw new WrongFormatException(e.getMessage());
		}
		
	}
	
	private int parsePositiveInt(String string, int minValue) {
		return parsePositiveInt(string, minValue, Integer.MAX_VALUE);
	}
	
	private int parsePositiveInt(String string, int minValue, int maxValue) {
		
		int number = Integer.parseInt(string);
		
		if (number < 0) {
			throw new NumberFormatException();
		}
		
		if (number < minValue) {
			throw new NumberFormatException();
		}
		
		if (number > maxValue) {
			throw new NumberFormatException();
		}
		
		return number;
	}

}
