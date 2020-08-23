package com.email.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "user")  // table will be created automatically
public class User {

	@Id
	private String userId;  // In DB this field must be user_id, if not created manually, at start of server this will get created automatically.
	private String name; // In DB this field must be user_name, if not created manually, at start of server this will get created automatically.
	private String email;
	private String password;
	private String verificationStatus = VerificationStatus.PENDING.getVerificationStatus();

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + name + ", email=" + email + ", password=" + password
				+ ", verificationStatus=" + verificationStatus + "]";
	}
	
}
