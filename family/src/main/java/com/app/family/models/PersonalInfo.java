package com.app.family.models;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PersonalInfo {
	
	@Id
	private String _id;
	private String personalId;
	private String name;
	private String fatherName;
	private String motherName;
	private String gender;
	private String birthday;
	private String martialStatus;
	private List<Contact> contacts;
	private List<Degree> education;
	private List<Location> locations;	
	private List<Profession> professions; 
	private List<String> subscribedFamilies;
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

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
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

	public List<String> getSubscribedFamilies() {
		return subscribedFamilies;
	}

	public void setSubscribedFamilies(List<String> subscribedFamilies) {
		this.subscribedFamilies = subscribedFamilies;
	}

	public List<String> getFamiliesAdmin() {
		return familiesAdmin;
	}

	public void setFamiliesAdmin(List<String> familiesAdmin) {
		this.familiesAdmin = familiesAdmin;
	}

	@Override
	public String toString() {
		return "PersonalInfo [_id=" + _id + ", personalId=" + personalId + ", name=" + name + ", fatherName="
				+ fatherName + ", motherName=" + motherName + ", gender=" + gender + ", birthday=" + birthday
				+ ", martialStatus=" + martialStatus + ", contacts=" + contacts + ", education=" + education
				+ ", locations=" + locations + ", professions=" + professions + ", subscribedFamilies="
				+ subscribedFamilies + ", familiesAdmin=" + familiesAdmin + "]";
	}
	
	
}
