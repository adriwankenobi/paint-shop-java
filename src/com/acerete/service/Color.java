package com.acerete.service;

public class Color {

	private int id;
	private ColorType type;
	
	public static final int N_COLORS_MIN = 1;
	public static final int N_COLORS_LIMIT = 10;
	public static final int N_COLORS_MAX = 2000;
	public static final int BATCHES_OFFERED_PER_COLOR = 1;
	
	public Color(int id, ColorType type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public ColorType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Color other = (Color) obj;
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Color [id=" + id + ", type=" + type + "]";
	}
	
}
