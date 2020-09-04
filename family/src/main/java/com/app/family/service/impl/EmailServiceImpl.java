package com.app.family.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;

import com.app.family.dao.RepositoryService;
import com.app.family.models.LoginInfo;
import com.app.family.enums.IdAppender;
import com.app.family.enums.SignInMode;
import com.app.family.enums.VerificationStatus;
import com.app.family.models.VerificationInfo;
import com.app.family.service.EmailService;
import com.app.family.service.JasyptEncryptorService;
import com.app.family.utils.DateUtil;
import com.app.family.utils.EmailConfiguration;
import com.app.family.utils.EncoderDecoderUtil;
import com.app.family.utils.LoginIdGenerator;
import com.app.family.utils.PasswordUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailServiceImpl implements EmailService{	

	@Autowired
	private EmailConfiguration email;

	@Autowired
	private JasyptEncryptorService jasyptEncryptorService;	
	
	@Autowired
	private RepositoryService repositoryService;

	private final Configuration templates;

	@Autowired
	EmailServiceImpl(Configuration templates){
		this.templates = templates;
	}

	@Override
	public Boolean generateMailAndAddUser(LoginInfo user){
		
		user.setSignInMode(SignInMode.EMAIL.getText());
		user.setPassword(PasswordUtil.getSafePassword(user.getPassword()));
		user.setLoginId(LoginIdGenerator.generateUserId(user.getEmail(), IdAppender.EMAIL.getText()));
		
		Properties props = new Properties();
		props.put("mail.smtp.host", email.getHost()); 
		props.put("mail.smtp.port", email.getPort()); 
		props.put("mail.smtp.auth", email.getAuth()); 
		props.put("mail.smtp.starttls.enable", email.getEnable()); 

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFromEmail(), jasyptEncryptorService.getApplicationEmailSendingPassword());
			}
		};
		return sendEmailAndAddUser(Session.getInstance(props, auth),email,user);	
	}

	private Boolean sendEmailAndAddUser(Session session,EmailConfiguration email, LoginInfo user){    	
		final String UTF = "UTF-8";    
		Boolean mailFlag = Boolean.FALSE;
		try 
		{
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit"); 
			msg.setFrom(new InternetAddress(email.getFromEmail(), "Family app .....")); 
			msg.setReplyTo(InternetAddress.parse(email.getReplyTo(), false)); 
			msg.setSubject(email.getSubject(), UTF);            
			msg.setSentDate(new Date());
			msg.setContent(getBodyForEmailVerificationLink(user), "text/html");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail(), false));
			Transport.send(msg);
			getVerificationTokenAndUpdateStatus(user);
			mailFlag = Boolean.TRUE;
		}
		catch (Exception e) {
			mailFlag = Boolean.FALSE;
		}
		return mailFlag;
	}

	private String getBodyForEmailVerificationLink(LoginInfo user) {
		String body=null;
		try {
			Template t = templates.getTemplate("email-verification.ftl");
			Map<String, String> map = getTokenMapAndAddUser(user);
			body = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
		} catch (Exception ex) {
			System.out.println("failed in preparing verification link "+ ex.getMessage());
		}
		return body;    	
	}

	private Map<String, String> getTokenMapAndAddUser(LoginInfo user) {
		Map<String, String> map = new HashMap<>();
		VerificationInfo verificationInfo = repositoryService.getVerificationInfoByUser(user);
		if(verificationInfo == null) {
			verificationInfo = new VerificationInfo();
			String uniqueKey = UUID.randomUUID().toString();
			verificationInfo.setToken(uniqueKey);
			String originalTokenString = user.getName()+VerificationInfo.TOKEN_SEPARATOR+uniqueKey;
			String encodedString = EncoderDecoderUtil.encodeString(originalTokenString);
			String encodedVerificationUrl = email.getEmailVerificationLinkURL()+encodedString;
			verificationInfo.setVerificationUrl(encodedVerificationUrl);
			map.put("VERIFICATION_URL", encodedVerificationUrl);
			verificationInfo.setUser(user);
			System.out.println(user.toString());
			repositoryService.saveLoginInfo(user);
			System.out.println(verificationInfo.toString());
			repositoryService.saveVerificationInfo(verificationInfo);    		
		} else {
			map.put("VERIFICATION_URL", verificationInfo.getVerificationUrl());    		
		}
		return map;
	}


	@Override
	public boolean verifyLinkAndUpdateStatus(String token) {
		boolean isValidToken = false;		
		VerificationInfo vt = repositoryService.getVerificationInfoByToken(token);		
		if(vt != null) {
			isValidToken = isEmailVerificationLinkValid(vt);
			if(isValidToken) {
				updateVerificationLinkStatus(vt, VerificationStatus.VERIFIED.getVerificationStatus());
			}
		}else {
			System.out.println("Expired :: token is not Valid");
		}
		return isValidToken;		
	}

	private boolean isEmailVerificationLinkValid(VerificationInfo vt) {
		boolean isValid = false;		
		if(vt != null) {
			String create = vt.getCreateDate();
			Date verifyDate = new Date();
			Date d1 = DateUtil.StringToDate(create);
			Date expiredDate = DateUtil.calculateExpiryDate(d1, Integer.parseInt(email.getExpiration()));	
			if(expiredDate != null) {
				isValid = verifyDate.before(expiredDate) || verifyDate.equals(expiredDate);
			}else {
				System.out.println("expiredDate calculated as empty !!");
			}			
		}
		return isValid;
	}


	private void updateVerificationLinkStatus(VerificationInfo verificationToken, String status) {
		repositoryService.updateUserEmailVerificationStatus(verificationToken, status);
	}

	private void getVerificationTokenAndUpdateStatus(LoginInfo user) {
		VerificationInfo verificationToken = repositoryService.getVerificationInfoByUser(user);
		updateVerificationLinkStatus(verificationToken, VerificationStatus.UNVERIFIED.getVerificationStatus());		
	}

	@Override
	public String getTokenFromEncodedURLString(String encodedString){
		String token = "";
		if(! StringUtils.isEmpty(encodedString)) {
			String originalString = EncoderDecoderUtil.decodeString(encodedString);
			String originalValues[] = originalString.split(VerificationInfo.TOKEN_SEPARATOR);
			if(originalValues!=null && originalValues.length == 2) {
				token = originalValues[1];
			}			
		}	
		return token;
	}	

	private LoginInfo getUserByEmail(String email) {
		System.out.println("service email :: "+email);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = repositoryService.getUserByEmail(email);	
		}
		return usr;
	}

	@Override
	public LoginInfo checkIfUserExistByEmail(String email) {
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = getUserByEmail(email);
		}		
		return ((usr != null) && isVerifiedUser(usr)) ? usr : null;		
	}
	
	private boolean isVerifiedUser(LoginInfo user) {
		if(user != null) {
			VerificationInfo vt = repositoryService.getVerificationInfoByUser(user);
			if(vt != null && vt.getVerificationStatus().equals(VerificationStatus.VERIFIED.getVerificationStatus())) {
				return true;
			}
		}		
		return false;
	}
	
	@Override
	public LoginInfo verifyUserByEmailAndPassword(LoginInfo user) {
		LoginInfo usr = null;
		if(user != null && !StringUtils.isEmpty(user.getEmail())) {
			LoginInfo u = getUserByEmail(user.getEmail());
			if((PasswordUtil.isPasswordVerified(user.getPassword(), u.getPassword())) && isVerifiedUser(u)) {
				usr = u;
			}
		}
		return usr;
	}

	@Override
	public LoginInfo getCreatedUserByEmail(String email) {
		LoginInfo usr = getUserByEmail(email);
		return usr!=null ? usr :null;
	}

}
