package com.app.family.models;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.family.enums.VerificationStatus;
import com.app.family.utils.DateUtil;

@Document
public class VerificationInfo {	
		
	public static final String 	TOKEN_SEPARATOR = ":separator:"; 	
 
	@Id
	private String _id;
    private LoginInfo user;    
    private String verificationUrl;
    private String createDate;
    private String token; 
    private String verificationStatus = VerificationStatus.PENDING.getVerificationStatus();
   
    public VerificationInfo() {
		super();
		this.createDate = DateUtil.dateToString(new Date());	
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
	public LoginInfo getUser() {
		return user;
	}

	public void setUser(LoginInfo user) {
		this.user = user;
	}

	public static String getTokenSeparator() {
		return TOKEN_SEPARATOR;
	}
	public String getVerificationUrl() {
		return verificationUrl;
	}

	public void setVerificationUrl(String verificationURL) {
		this.verificationUrl = verificationURL;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	@Override
	public String toString() {
		return "VerificationToken [token=" + token + ", user=" + user + ", verificationURL="
				+ verificationUrl + ", createDate=" + createDate + "]";
	}   
}
