package com.acerete.output;

import java.util.ArrayList;
import java.util.List;

import com.acerete.service.ColorType;

public class Output {
	
	private int caseId;
	private List<ColorType> colors;
	
	public Output(int caseId) {
		this.caseId = caseId;
		this.colors = new ArrayList<ColorType>();
	}

	public int getCaseId() {
		return this.caseId;
	}
	
	public void addColor(ColorType color) {
		this.colors.add(color);
	}
	
	public void addColorList(List<ColorType> colorList) {
		this.colors.addAll(colorList);
	}
	
	public List<ColorType> getColors() {
		return colors;
	}
	
	public boolean hasColors() {
		return !colors.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caseId;
		result = prime * result + ((colors == null) ? 0 : colors.hashCode());
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
		Output other = (Output) obj;
		if (caseId != other.caseId)
			return false;
		if (colors == null) {
			if (other.colors != null)
				return false;
		} else if (!colors.equals(other.colors))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Output [caseId=" + caseId + ", colors=" + colors + "]";
	}

}
