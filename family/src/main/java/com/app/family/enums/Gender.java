package com.app.family.enums;

public enum Gender {
	MALE("male"),
	FEMALE("female"),
	OTHER("other");
	
	private final String text;

	private Gender(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}	
}
