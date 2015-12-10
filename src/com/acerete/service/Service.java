package com.acerete.service;

import com.acerete.exception.WrongSolutionException;
import com.acerete.output.Output;

public interface Service {

	/**
	 * Generates an output
	 */
	public Output serve() throws WrongSolutionException;
}
