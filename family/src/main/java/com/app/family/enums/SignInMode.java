package com.app.family.enums;

public enum SignInMode {
	
	EMAIL("email"),
	GOOGLE("google"),
	PHONE("ph"),
	FACEBOOK("fb");
	
	private final String text;	

	private SignInMode(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}	
}
