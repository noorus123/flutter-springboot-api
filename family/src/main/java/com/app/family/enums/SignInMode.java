package com.app.family.enums;

public enum SignInMode {
	
	EMAIL("email"),
	GOOGLE("google"),
	PHONE("phone"),
	FACEBOOK("facebook");
	
	private final String text;	

	private SignInMode(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}	
}
