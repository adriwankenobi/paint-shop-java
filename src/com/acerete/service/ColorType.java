package com.acerete.service;

import com.acerete.exception.ColorTypeNotFoundException;

public enum ColorType {

	GLOSSY(0),
	MATTE(1);
	
	int id;
	
	public static final String UNKNOWN_COLOR_TYPE_ID = "Unknown colorType with id: ";
	
	private ColorType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static ColorType cheapest() {
		return GLOSSY;
	}
	
	public static ColorType getById(int id) throws ColorTypeNotFoundException {
		for (ColorType colorType : ColorType.values()) {
			if (colorType.getId() == id) {
				return colorType;
			}
		}
		throw new ColorTypeNotFoundException(UNKNOWN_COLOR_TYPE_ID + id);
	}
}
