package com.app.family.models;

import com.app.family.enums.FamilyType;

public class Pibbling {

	private PersonalInfo pibble;
	private FamilyType familyType;
	
	public Pibbling() {
		super();
	}

	public PersonalInfo getPibble() {
		return pibble;
	}

	public void setPibble(PersonalInfo pibble) {
		this.pibble = pibble;
	}

	public FamilyType getFamilyType() {
		return familyType;
	}

	public void setFamilyType(FamilyType familyType) {
		this.familyType = familyType;
	}
}
