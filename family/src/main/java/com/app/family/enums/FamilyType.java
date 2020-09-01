package com.app.family.enums;

public enum FamilyType {

	NATIVE_MATERNAL("native_maternal"),
	NATIVE_PATERNAL("native_paternal"),
	INLAW_MATERNAL("inLaw_maternal"),
	INLAW_PATERNAL("inLaw_paternal");
	
	private final String text;

	private FamilyType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
