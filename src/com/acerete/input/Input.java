package com.acerete.input;

import java.util.HashSet;
import java.util.Set;

import com.acerete.service.Customer;

public class Input {

	private int caseId;
	private int nColors;
	private Set<Customer> setCustomers;
	
	public static final int N_CASES_MIN = 1;
	public static final int N_CASES_LIMIT = 5;
	public static final int N_CASES_MAX = 100;
	
	public Input(int caseId, int nColors) {
		this.caseId = caseId;
		this.nColors = nColors;
		this.setCustomers = new HashSet<Customer>();
	}
	
	public int getCaseId() {
		return caseId;
	}
	
	public int getnColors() {
		return nColors;
	}

	public void addCustomer(Customer customer) {
		setCustomers.add(customer);
	}
	
	public Set<Customer> getCustomerSet() {
		return setCustomers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caseId;
		result = prime * result + nColors;
		result = prime * result
				+ ((setCustomers == null) ? 0 : setCustomers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Input other = (Input) obj;
		if (caseId != other.caseId)
			return false;
		if (nColors != other.nColors)
			return false;
		if (setCustomers == null) {
			if (other.setCustomers != null)
				return false;
		} else if (!setCustomers.equals(other.setCustomers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Input [caseId=" + caseId + ", nColors=" + nColors
				+ ", setCustomers=" + setCustomers + "]";
	}
}
