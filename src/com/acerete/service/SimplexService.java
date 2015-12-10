package com.acerete.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NoFeasibleSolutionException;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import com.acerete.exception.ColorTypeNotFoundException;
import com.acerete.exception.WrongSolutionException;
import com.acerete.input.Input;
import com.acerete.output.Output;

public class SimplexService implements Service {
	
	private static final String NO_INT_ERROR = "Solution should be int number";
	private static final String NO_ZERO_OR_ONE_ERROR = "Solution should be 0 or 1";
	private static final String TOO_MANY_BATCHES = "There are more offered batches than permitted";
	
	private int caseId;
	private int nColors;
	private Set<Customer> customerSet;
	
	private ColorType[] colorTypes;
	private int nTypes;
	private int nCombinations;
	
	public SimplexService(Input input) {
		this.caseId = input.getCaseId();
		this.nColors = input.getnColors();
		this.customerSet = input.getCustomerSet();
		
		this.colorTypes = ColorType.values();
		this.nTypes = colorTypes.length;
		this.nCombinations = nTypes * nColors;
	}
	   
	@Override
	public Output serve() throws WrongSolutionException {
		
		// Create the target function (maximize cheapest color type)
		double[] coefficients = generateCoefficients(nColors);
		LinearObjectiveFunction targetFunction = new LinearObjectiveFunction(coefficients, 0);
		
		// Define list of constraints
		List<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
		
		// Add color constraints (only one type per color)
		addColorConstrainsts(constraints);
        
        // Add customers specific constraints
		for (Customer customer : customerSet) {
			addCustomerConstraint(constraints, customer);
		}
        
		// Create simplex solver
        SimplexSolver solver = new SimplexSolver();
        
        // Execute the simplex algorithm
        try {
	        PointValuePair optSolution = solver.optimize(
	        		targetFunction, 
	        		new LinearConstraintSet(constraints),
	                GoalType.MAXIMIZE,
	                new NonNegativeConstraint(true));
	        
	        double[] solution = optSolution.getPoint();
	        return generateOutput(solution);
        }
        catch (NoFeasibleSolutionException e) {
        	
        	// No solution, return empty output
        	return new Output(caseId);
        }
	}

	/**
	 * PRIVATE HELPER METHODS
	 */

	/**
	 * Generate coefficients
	 * Setting 1 to cheapest color types, 0 to others
	 * One element per color type, per color
	 */
	private double[] generateCoefficients(int nColors) {
		
		double[] coefficients = new double[nCombinations];
		for (int i = 0; i < nCombinations; i+=nTypes) {
			for (int j = 0; j < nTypes; j++) {
				if (colorTypes[j] == ColorType.cheapest()) {
					coefficients[i + j] = 1;
				}
			}
		}
		return coefficients;
	}
	
	/**
	 * Add color constraints
	 * Setting 1 to not repeatable color types, 0 to others
	 * One element per color type, per color
	 */
	private void addColorConstrainsts(List<LinearConstraint> constraints) {
		
		for(int i = 0; i < nColors; i++) {
            double[] rule = new double[nCombinations];
            for(int j = 0; j < nCombinations; j++) {
            	if (nTypes * i == j || nTypes * i + 1 == j) {
            		rule[j] = 1;
            	}
            }
            constraints.add(new LinearConstraint(rule, Relationship.EQ, Color.BATCHES_OFFERED_PER_COLOR));
        }
	}
	
	/**
	 * Add customer constraint
	 * Setting 1 to liked colors types, 0 to others
	 * One element per color type, per color
	 */
	private void addCustomerConstraint(List<LinearConstraint> constraints, Customer customer) {
		
		double[] rule = new double[nCombinations];
		
		for (int i = 0; i < rule.length; i++) {
			int colorId = (i / nTypes) + 1;
			ColorType type = colorTypes[i % nTypes];
			Color color = new Color(colorId, type);
			if (customer.likes(color)) {
				rule[i] = 1;
			}
		}
		
		constraints.add(new LinearConstraint(rule, Relationship.GEQ, Customer.MIN_MATCHES_TO_SATISFY));
	}
	
	/**
	 * Generates output from solution from simplex algorithm
	 */
	private Output generateOutput(double[] solution) throws WrongSolutionException {
		
		Output output = new Output(caseId);
		
		for (int i = 0; i < nCombinations; i+=nTypes) {
			int totalMatches = 0;
			List<ColorType> matches = new ArrayList<ColorType>();
			for (int j = 0; j < nTypes; j++) {
				if (solution[i + j] % 1 != 0) {
					throw new WrongSolutionException(NO_INT_ERROR);
				}
				if (solution[i + j] != 0 && solution[i + j] != 1) {
					throw new WrongSolutionException(NO_ZERO_OR_ONE_ERROR);
				}
				if (solution[i + j] == 1) {
					totalMatches++;
					try {
						matches.add(ColorType.getById((i + j) % nTypes));
					}
					catch (ColorTypeNotFoundException e) {
						throw new WrongSolutionException(e.getMessage());
					}
				}
			}
			if (totalMatches != Color.BATCHES_OFFERED_PER_COLOR) {
				throw new WrongSolutionException(TOO_MANY_BATCHES);
			}
			output.addColorList(matches);
		}
		
		return output;
	}
}
