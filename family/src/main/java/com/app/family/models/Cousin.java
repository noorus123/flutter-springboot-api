package com.app.family.models;

public class Cousin {

	private String cousinName;
	private String personalId;

	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public Cousin() {
		super();
	}

	public String getCousinName() {
		return cousinName;
	}

	public void setCousinName(String cousinName) {
		this.cousinName = cousinName;
	}

}
