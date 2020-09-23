package com.app.family.models;

public class Marital {

	private String maritalStatus;
	private String maritalSpouse;
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMaritalSpouse() {
		return maritalSpouse;
	}
	public void setMaritalSpouse(String maritalSpouse) {
		this.maritalSpouse = maritalSpouse;
	}
	
	@Override
	public String toString() {
		return "Marital [maritalStatus=" + maritalStatus + ", maritalSpouse=" + maritalSpouse + "]";
	}	
	
	
}
