package com.app.family.enums;

public enum VerificationStatus {
	PENDING("pending"),
	UNVERIFIED("unverified"),
	VERIFIED("verified");
	
	private final String text;
	  
	VerificationStatus(String statusText) {
	      this.text = statusText;
	  }
	
	public String getVerificationStatus() {
	      return this.text;
	  }
}
