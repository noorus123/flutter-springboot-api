package com.app.family.enums;

public enum JobType {
	
	PREVIOUSJOB("previous_job"),
	CURRENTJOB("current_job");
	
	private final String text;

	private JobType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
