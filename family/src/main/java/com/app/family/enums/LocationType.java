package com.app.family.enums;

public enum LocationType {
	
	NATIVE("native"),
	CURRENTSTAY("currentStay");
	
	private final String text;

	private LocationType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	
}
