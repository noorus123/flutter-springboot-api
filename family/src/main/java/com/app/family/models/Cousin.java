package com.app.family.models;

import com.app.family.enums.FamilyType;

public class Cousin {
	
	private PersonalInfo cousin;
	private FamilyType familyType;
	
	public Cousin() {
		super();
	}
	public PersonalInfo getCousin() {
		return cousin;
	}
	public void setCousin(PersonalInfo cousin) {
		this.cousin = cousin;
	}
	public FamilyType getFamilyType() {
		return familyType;
	}
	public void setFamilyType(FamilyType familyType) {
		this.familyType = familyType;
	}	
}
