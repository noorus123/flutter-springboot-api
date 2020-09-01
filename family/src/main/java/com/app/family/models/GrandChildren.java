package com.app.family.models;

import com.app.family.enums.FamilyType;

public class GrandChildren {
	
	private PersonalInfo grandChildren;
	private FamilyType familyType;
	
	public GrandChildren() {
		super();
	}

	public PersonalInfo getGrandChildren() {
		return grandChildren;
	}

	public void setGrandChildren(PersonalInfo grandChildren) {
		this.grandChildren = grandChildren;
	}

	public FamilyType getFamilyType() {
		return familyType;
	}

	public void setFamilyType(FamilyType familyType) {
		this.familyType = familyType;
	}
	
	
}
