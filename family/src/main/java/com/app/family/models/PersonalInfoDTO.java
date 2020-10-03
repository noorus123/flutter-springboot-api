package com.app.family.models;

public class PersonalInfoDTO {	
	
	private String personalId;  //loginId
	private String name;	
	private String gender;
	
	public PersonalInfoDTO() {
		super();
	}	

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}	
}
