package com.acerete.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Customer {

	private Map<ColorType, Set<Color>> likes;
	private int size; // Duplicated for performance
	
	public static final int N_CUSTOMERS_MIN = 1;
	public static final int N_CUSTOMERS_LIMIT = 100;
	public static final int N_CUSTOMERS_MAX = 2000;
	public static final int MIN_MATCHES_TO_SATISFY = 1;
	
	public Customer() {
		this.likes = new HashMap<ColorType, Set<Color>>();
		for (ColorType type : ColorType.values()) {
			this.likes.put(type, new HashSet<Color>());
		}
		this.size = 0;
	}

	public void addLike(Color like) {
		Set<Color> colors = this.likes.get(like.getType());
		colors.add(like);
		this.likes.put(like.getType(), colors);
		this.size++;
	}
	
	public int getLikesSize() {
		return size;
	}
	
	public boolean likes(Color color) {
		return this.likes.get(color.getType()).contains(color);
	}
	
	public boolean likesType(ColorType type) {
		return !this.likes.get(type).isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime * result + size;
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
		Customer other = (Customer) obj;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [likes=" + likes + ", size=" + size + "]";
	}

}
