package com.app.family.enums;

public enum FamilyType {

	PARENTAL("parental"),
	INLAW("in-law");

	private final String text;

	private FamilyType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
