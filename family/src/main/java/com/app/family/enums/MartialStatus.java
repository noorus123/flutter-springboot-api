package com.app.family.enums;

public enum MartialStatus {
	MARRIED("married"),
	UNMARRIED("unmarried"),
	DIVORCED("divorced");
	
	private final String text;

	private MartialStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}	
}
