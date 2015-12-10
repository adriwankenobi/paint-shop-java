package com.acerete.shop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.acerete.exception.ReadingFileException;
import com.acerete.exception.WrongFormatException;
import com.acerete.exception.WrongSolutionException;
import com.acerete.input.Input;
import com.acerete.input.InputReader;
import com.acerete.input.FileInputReader;
import com.acerete.output.Output;
import com.acerete.output.OutputWriter;
import com.acerete.output.StdOutputWriter;
import com.acerete.service.Service;
import com.acerete.service.SimplexService;


public class PaintShop implements Shop {

	// SINGLETON
	public final static Shop INSTANCE = new PaintShop();
	private PaintShop() {}
	
	private InputReader inputReader = FileInputReader.INSTANCE;
	private OutputWriter outputWriter = StdOutputWriter.INSTANCE;
	
	public String satisfy(String fileName) throws ReadingFileException,
												WrongFormatException,
												WrongSolutionException,
												InterruptedException,
												ExecutionException {
		
		// Read from file
		Set<Input> inputSet = inputReader.readInputFromFile(fileName);
		
		// Prepare output
		List<Output> outputList = new ArrayList<Output>();
		
		// Parallelize work among workers
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_WORKERS);
		
		Set<Callable<Output>> callables = new HashSet<Callable<Output>>();
		for (Input input : inputSet) {
			callables.add(new Worker(input));
		}

		List<Future<Output>> futures = executorService.invokeAll(callables);

		for(Future<Output> future : futures){
			outputList.add(future.get());
		}
	    
	    // Print output
		return outputWriter.writeOutputIntoStdout(outputList);
	}
	
	private class Worker implements Callable<Output> {

		private Service service;
		
		public Worker(Input input) {
			this.service = new SimplexService(input);
		}

		@Override
		public Output call() throws Exception {
			return service.serve();
		}
	}
}
