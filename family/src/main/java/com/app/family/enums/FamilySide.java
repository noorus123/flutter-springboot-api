package com.app.family.enums;

public enum FamilySide {

	MATERNAL("maternal"),
	PATERNAL("paternal");

	private final String text;

	private FamilySide(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
