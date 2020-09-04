package com.app.family.enums;

public enum IdAppender {
	EMAIL("mail_"),
	FACEBOOK("fb_"),
	GOOGLE("google_"),
	PHONE("ph_");
	
	private final String text;
	  
	IdAppender(String idText) {
	      this.text = idText;
	  }
	
	public String getText() {
	      return this.text;
	  }
}
