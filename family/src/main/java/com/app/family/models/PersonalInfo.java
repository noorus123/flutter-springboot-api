package com.app.family.models;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@Document
//@JsonInclude(JsonInclude.Include.NON_NULL) 
public class PersonalInfo {
	
	@Id
	private String _id;
	private String personalId;  //loginId
	private String name;
	private String fatherName;
	private String motherName;
	private String gender;
	private String birthday;
	private Marital marital;
	private boolean isChildren;
	private int childCount;
	private List<Child> children;
	private List<Contact> contacts;
	private List<Degree> education;
	private List<Location> locations;	
	private List<Profession> professions; 
	private List<SubscribedFamily> subscribedFamilies;
	private List<String> familiesAdmin;
	
	public PersonalInfo() {
		super();
	}	

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}	

	public Marital getMarital() {
		return marital;
	}

	public void setMarital(Marital marital) {
		this.marital = marital;
	}
	
	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public List<Degree> getEducation() {
		return education;
	}

	public void setEducation(List<Degree> education) {
		this.education = education;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}	

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
	}
	
	public List<SubscribedFamily> getSubscribedFamilies() {
		return subscribedFamilies;
	}

	public void setSubscribedFamilies(List<SubscribedFamily> subscribedFamilies) {
		this.subscribedFamilies = subscribedFamilies;
	}

	public List<String> getFamiliesAdmin() {
		return familiesAdmin;
	}

	public void setFamiliesAdmin(List<String> familiesAdmin) {
		this.familiesAdmin = familiesAdmin;
	}

	public boolean getIsChildren() {
		return isChildren;
	}

	public void setIsChildren(boolean isChildren) {
		this.isChildren = isChildren;
	}	

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	

	@Override
	public String toString() {
		return "PersonalInfo [_id=" + _id + ", personalId=" + personalId + ", name=" + name + ", fatherName="
				+ fatherName + ", motherName=" + motherName + ", gender=" + gender + ", birthday=" + birthday
				+ ", marital=" + marital + ", isChildren=" + isChildren + ", childCount=" + childCount + ", children="
				+ children + ", contacts=" + contacts + ", education=" + education + ", locations=" + locations
				+ ", professions=" + professions + ", subscribedFamilies=" + subscribedFamilies + ", familiesAdmin="
				+ familiesAdmin + "]";
	}
}
