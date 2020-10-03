package com.app.family.models;

public class Nibbling {

	private String nibblingName;
	private String personalId;

	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public Nibbling() {
		super();
	}

	public String getNibblingName() {
		return nibblingName;
	}

	public void setNibblingName(String nibblingName) {
		this.nibblingName = nibblingName;
	}
}
