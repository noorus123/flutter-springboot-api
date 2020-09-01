package com.app.family.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LoginInfo {
	
	@Id
	private String loginId;
	private String signInMode;
	private String email;
	private String password;
	private String name;
	private String phone;
	
	public LoginInfo() {
		super();
	}	

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSignInMode() {
		return signInMode;
	}

	public void setSignInMode(String signInMode) {
		this.signInMode = signInMode;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "LoginInfo [loginId=" + loginId + ", signInMode=" + signInMode + ", email=" + email + ", password="
				+ password + ", name=" + name + ", phone=" + phone + "]";
	}	
}
