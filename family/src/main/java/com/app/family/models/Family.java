package com.app.family.models;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Family {
	
	@Id
	private String familyId;
	private String familyName;
	private String familyCode;
	private PersonalInfo adminId; //PersonalInfoId
	private List<PersonalInfo> members; //List of PersonalInfoId
	
	public Family() {
		super();
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyCode() {
		return familyCode;
	}

	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}

	public PersonalInfo getAdminId() {
		return adminId;
	}

	public void setAdminId(PersonalInfo adminId) {
		this.adminId = adminId;
	}

	public List<PersonalInfo> getMembers() {
		return members;
	}

	public void setMembers(List<PersonalInfo> members) {
		this.members = members;
	}

	
	
}
