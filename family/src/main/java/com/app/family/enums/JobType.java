package com.app.family.enums;

public enum JobType {
	
	PREVIOUSJOB("Previous"),
	CURRENTJOB("Current");
	
	private final String text;

	private JobType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
