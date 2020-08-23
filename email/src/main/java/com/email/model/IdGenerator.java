package com.email.model;

public enum IdGenerator {
	MAIL("mail_"),
	FACEBOOK("fb_"),
	GOOGLE("google_"),
	PHONE("phone_");
	
	private final String text;
	  
	IdGenerator(String idText) {
	      this.text = idText;
	  }
	
	public String getIdGenerator() {
	      return this.text;
	  }
}
