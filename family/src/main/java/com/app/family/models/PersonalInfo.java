package com.app.family.models;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.family.enums.Gender;
import com.app.family.enums.MartialStatus;

@Document
public class PersonalInfo {
	
	@Id
	private String personalId;
	private String name;
	private String fatherName;
	private String motherName;
	private Gender gender;
	private String birthday;
	private MartialStatus maritialStatus;
	private List<Degree> education;
	private List<Location> locations;
	private List<Contact> contacts;
	private List<Profession> professions; 
	private List<Family> subscribedFamilies;
	private List<Family> familiesAdmin;
	
	public PersonalInfo() {
		super();
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public MartialStatus getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(MartialStatus maritialStatus) {
		this.maritialStatus = maritialStatus;
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

	public List<Family> getSubscribedFamilies() {
		return subscribedFamilies;
	}

	public void setSubscribedFamilies(List<Family> subscribedFamilies) {
		this.subscribedFamilies = subscribedFamilies;
	}

	public List<Family> getFamilyAdmins() {
		return familiesAdmin;
	}

	public void setFamilyAdmins(List<Family> familyAdmins) {
		this.familiesAdmin = familyAdmins;
	}	
}
