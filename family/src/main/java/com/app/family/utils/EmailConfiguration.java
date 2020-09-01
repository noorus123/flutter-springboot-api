package com.app.family.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email")
public class EmailConfiguration {

    private String name;
	private String fromEmail;
	private String toEmail;
	private String subject;
	private String body;
	private String replyTo;
	private String password;
	private String host;
	private String port;
	private String auth;
	private String enable;
	private String emailVerificationLinkURL;
	private String expiration;
	
	public EmailConfiguration() {
		super();
	}

	public EmailConfiguration(String fromEmail, String toEmail, String subject, String body, String password) {
		super();
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public void setEmailVerificationLinkURL(String emailVerificationLinkURL) {
		this.emailVerificationLinkURL = emailVerificationLinkURL;
	}

	public String getEmailVerificationLinkURL() {
		return emailVerificationLinkURL;
	}
	
	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
}