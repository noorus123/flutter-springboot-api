package com.email.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import org.springframework.data.annotation.Transient;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.email.util.DateUtil;

@Entity
@Table(name = "verificationToken")
public class VerificationToken {

	public final int EXPIRATION = 1440 ; //60 * 24
	public static final String 	TOKEN_SEPARATOR = ":separator:";
    	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
	 /*
	  @Transient public static final String SEQUENCE_NAME = "tokens_sequence";
	 */   
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;     
    private String verificationURL;
    private String createDate;
    private String token;    
   
    public VerificationToken() {
		super();
		this.createDate = DateUtil.dateToString(new Date());	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	/*
	 public static String getSequenceName() { return SEQUENCE_NAME; }
	 */
	
	public static String getTokenSeparator() {
		return TOKEN_SEPARATOR;
	}
	public String getVerificationURL() {
		return verificationURL;
	}

	public void setVerificationURL(String verificationURL) {
		this.verificationURL = verificationURL;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "VerificationToken [id=" + id + ", token=" + token + ", user=" + user + ", verificationURL="
				+ verificationURL + ", createDate=" + createDate + "]";
	}   
	
	
	
	
}
