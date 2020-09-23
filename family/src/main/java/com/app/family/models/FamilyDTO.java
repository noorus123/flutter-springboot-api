package com.app.family.models;

public class FamilyDTO{

	private String familyName;
	private String familySide;
	private String familyType;
	private LoginInfo loginInfo;

	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFamilySide() {
		return familySide;
	}
	public void setFamilySide(String familySide) {
		this.familySide = familySide;
	}
	public String getFamilyType() {
		return familyType;
	}
	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}	
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	@Override
	public String toString() {
		return "FamilyDTO [familyName=" + familyName + ", familySide=" + familySide + ", familyType=" + familyType
				+ ", loginInfo=" + loginInfo + "]";
	}
	
	
}