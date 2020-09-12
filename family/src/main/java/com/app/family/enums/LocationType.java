package com.app.family.enums;

public enum LocationType {
	
	NATIVE("native"),
	CURRENT("current");
	
	private final String text;

	private LocationType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	
}
