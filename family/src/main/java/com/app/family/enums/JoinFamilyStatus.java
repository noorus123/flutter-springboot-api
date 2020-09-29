package com.app.family.enums;

public enum JoinFamilyStatus {
	PENDING("pending"),
	APPROVED("approved"),
	REJECTED("rejected");
	
	private final String text;	

	private JoinFamilyStatus(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
