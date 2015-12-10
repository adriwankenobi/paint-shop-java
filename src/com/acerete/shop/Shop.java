package com.acerete.shop;

import java.util.concurrent.ExecutionException;

import com.acerete.exception.ReadingFileException;
import com.acerete.exception.WrongFormatException;
import com.acerete.exception.WrongSolutionException;

public interface Shop {

	public static final int NUM_WORKERS = 10;
	
	/**
	 * Writes in standard output the result of serving the customers from file and returns it
	 */
	public String satisfy(String fileName) throws ReadingFileException,
												WrongFormatException,
												WrongSolutionException,
												InterruptedException,
												ExecutionException;
}
