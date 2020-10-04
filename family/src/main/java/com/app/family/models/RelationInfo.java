package com.app.family.models;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RelationInfo {

	@Id
	private String _id;
	private String relationId;
	private String familyId;
	private String personalId;
	private String fatherInLaw;
	private String motherInLaw;
	private List<PersonalInfoDTO> siblings;
	private List<PersonalInfoDTO> cousins;
	private List<PersonalInfoDTO> pibblings; //aunt & Uncle
	private List<PersonalInfoDTO> nibblings; //niece and nephew
	private List<PersonalInfoDTO> grandParent;
	private List<PersonalInfoDTO> grandChildren;
	
	public RelationInfo() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getFamilyId() {
		return familyId;
	}

	public String getPersonalId() {
		return personalId;
	}
	
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getFatherInLaw() {
		return fatherInLaw;
	}

	public void setFatherInLaw(String fatherInLaw) {
		this.fatherInLaw = fatherInLaw;
	}

	public String getMotherInLaw() {
		return motherInLaw;
	}

	public void setMotherInLaw(String motherInLaw) {
		this.motherInLaw = motherInLaw;
	}

	public List<PersonalInfoDTO> getSiblings() {
		return siblings;
	}

	public void setSiblings(List<PersonalInfoDTO> siblings) {
		this.siblings = siblings;
	}

	public List<PersonalInfoDTO> getCousins() {
		return cousins;
	}

	public void setCousins(List<PersonalInfoDTO> cousins) {
		this.cousins = cousins;
	}

	public List<PersonalInfoDTO> getPibblings() {
		return pibblings;
	}

	public void setPibblings(List<PersonalInfoDTO> pibblings) {
		this.pibblings = pibblings;
	}

	public List<PersonalInfoDTO> getNibblings() {
		return nibblings;
	}

	public void setNibblings(List<PersonalInfoDTO> nibblings) {
		this.nibblings = nibblings;
	}

	public List<PersonalInfoDTO> getGrandParent() {
		return grandParent;
	}

	public void setGrandParent(List<PersonalInfoDTO> grandParent) {
		this.grandParent = grandParent;
	}

	public List<PersonalInfoDTO> getGrandChildren() {
		return grandChildren;
	}

	public void setGrandChildren(List<PersonalInfoDTO> grandChildren) {
		this.grandChildren = grandChildren;
	}
}
