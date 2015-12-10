package com.acerete.output;

import java.util.List;

public interface OutputWriter {

	/**
	 * Writes output content into standard output and returns it
	 */
	public String writeOutputIntoStdout(List<Output> outputList);
}
