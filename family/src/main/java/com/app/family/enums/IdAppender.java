package com.app.family.enums;

public enum IdAppender {
	MAIL("mail_"),
	FACEBOOK("fb_"),
	GOOGLE("google_"),
	PHONE("phone_");
	
	private final String text;
	  
	IdAppender(String idText) {
	      this.text = idText;
	  }
	
	public String getIdGenerator() {
	      return this.text;
	  }
}
