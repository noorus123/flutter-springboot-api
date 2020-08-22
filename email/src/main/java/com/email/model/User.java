package com.email.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "user")  // table will be created automatically
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;  // In DB this field must be user_id, if not created manually, at start of server this will get created automatically.
	private String userName; // In DB this field must be user_name, if not created manually, at start of server this will get created automatically.
	private String email;
	private String verificationStatus = VerificationStatus.PENDING.getVerificationStatus();
	
	
	@OneToOne(mappedBy="user", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private VerificationToken verificationToken;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public VerificationToken getVerificationToken() {
		return verificationToken;
	}
	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	
	
	
	
}
