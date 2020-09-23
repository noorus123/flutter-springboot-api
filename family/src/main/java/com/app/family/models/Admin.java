package com.app.family.models;


import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin {

	@Id
	private String _id;
	private String name;
	private String adminId; //Login Id
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
}
