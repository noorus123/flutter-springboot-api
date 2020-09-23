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
	private String spouse;
	private List<Sibling> siblings;
	private List<Cousin> cousins;
	private List<Pibbling> pibblings;       //aunt & Uncle
	private List<Nibbling> nibblings;      //niece and nephew
	private List<GrandParent> grandParent;
	private List<GrandChildren> grandChildren;
	
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

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	
	public String getSpouse() {
		return spouse;
	}	

	public List<Sibling> getSiblings() {
		return siblings;
	}

	public void setSiblings(List<Sibling> siblings) {
		this.siblings = siblings;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public List<Cousin> getCousins() {
		return cousins;
	}

	public void setCousins(List<Cousin> cousins) {
		this.cousins = cousins;
	}

	public List<Pibbling> getPibblings() {
		return pibblings;
	}

	public void setPibblings(List<Pibbling> pibblings) {
		this.pibblings = pibblings;
	}

	public List<Nibbling> getNibblings() {
		return nibblings;
	}

	public void setNibblings(List<Nibbling> nibblings) {
		this.nibblings = nibblings;
	}

	public List<GrandParent> getGrandParent() {
		return grandParent;
	}

	public void setGrandParent(List<GrandParent> grandParent) {
		this.grandParent = grandParent;
	}

	public List<GrandChildren> getGrandChildren() {
		return grandChildren;
	}

	public void setGrandChildren(List<GrandChildren> grandChildren) {
		this.grandChildren = grandChildren;
	}
}
