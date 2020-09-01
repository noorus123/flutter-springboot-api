package com.app.family.models;

import com.app.family.enums.FamilyType;

public class Nibbling {

	private PersonalInfo nibble;
	private FamilyType familyType;
	
	public Nibbling() {
		super();
	}

	public PersonalInfo getNibble() {
		return nibble;
	}

	public void setNibble(PersonalInfo nibble) {
		this.nibble = nibble;
	}

	public FamilyType getFamilyType() {
		return familyType;
	}

	public void setFamilyType(FamilyType familyType) {
		this.familyType = familyType;
	}	
}
