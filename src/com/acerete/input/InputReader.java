package com.acerete.input;

import java.util.Set;

import com.acerete.exception.ReadingFileException;
import com.acerete.exception.WrongFormatException;

public interface InputReader {

	/**
	 * Input limits
	 */
	public static final int MAX_LIKES_PER_CASE = 3000;
	
	/**
	 * Error messages
	 */
	public static final String FILE_NOT_FOUND = "File not found";
	public static final String N_TEST_CASES_NOT_FOUND = "Number of test cases not found";
	public static final String N_COLORS_NOT_FOUND = "Number of colors not found";
	public static final String N_CUSTOMERS_NOT_FOUND = "Number of customers not found";
	public static final String LIMITS_EXCEEDED = "Limits exceeded";
	public static final String N_LIKES_NOT_FOUND = "Number of likes not found";
	public static final String WRONG_CUSTOMER_FORMAT = "Wrong customer format";
	public static final String WRONG_COLOR = "Wrong color";
	public static final String ALREADY_LIKED_COLOR = "Already liked color";
	public static final String ALREADY_LIKED_MATTE = "Already liked matte color";
	public static final String TOO_MANY_LIKES = "Too many likes";
	
	/**
	 * Delimiter character
	 */
	public static final String DELIM = " ";
	
	/**
	 * Reads input content from file
	 */
	public Set<Input> readInputFromFile(String fileName) throws ReadingFileException, WrongFormatException;
	
}
