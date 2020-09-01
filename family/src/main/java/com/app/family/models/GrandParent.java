package com.app.family.models;

import com.app.family.enums.FamilyType;

public class GrandParent {

	private PersonalInfo grandParent;
	private FamilyType familyType;
	
	public GrandParent() {
		super();
	}

	public PersonalInfo getGrandParent() {
		return grandParent;
	}

	public void setGrandParent(PersonalInfo grandParent) {
		this.grandParent = grandParent;
	}

	public FamilyType getFamilyType() {
		return familyType;
	}

	public void setFamilyType(FamilyType familyType) {
		this.familyType = familyType;
	}	
}
