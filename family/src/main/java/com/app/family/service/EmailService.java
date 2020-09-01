package com.app.family.service;

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

import com.app.family.dao.EmailDaoService;
import com.app.family.models.LoginInfo;
import com.app.family.enums.IdAppender;
import com.app.family.enums.VerificationStatus;
import com.app.family.models.VerificationInfo;
import com.app.family.utils.DateUtil;
import com.app.family.utils.EmailConfiguration;
import com.app.family.utils.EncoderDecoderUtil;
import com.app.family.utils.PasswordUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailService {	

	@Autowired
	private EmailConfiguration email;

	@Autowired
	private JasyptEncryptorService jasyptEncryptorService;

	@Autowired
	EmailDaoService emailDaoService;

	private final Configuration templates;

	@Autowired
	EmailService(Configuration templates){
		this.templates = templates;
	}

	public Boolean generateMail(LoginInfo user){
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
		return sendEmail(Session.getInstance(props, auth),email,user);	
	}

	private Boolean sendEmail(Session session,EmailConfiguration email, LoginInfo user){    	
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
			Map<String, String> map = getVerificationTokenMap(user);
			body = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
		} catch (Exception ex) {
			System.out.println("failed in preparing verification link "+ ex.getMessage());
		}
		return body;    	
	}

	private Map<String, String> getVerificationTokenMap(LoginInfo user) {
		Map<String, String> map = new HashMap<>();
		VerificationInfo verificationInfo = emailDaoService.getVerificationInfoByUser(user);
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
			emailDaoService.saveUser(user);
			System.out.println(verificationInfo.toString());
			emailDaoService.saveVerificationInfo(verificationInfo);    		
		} else {
			map.put("VERIFICATION_URL", verificationInfo.getVerificationUrl());    		
		}
		return map;
	}


	public boolean verifyLinkAndUpdateStatus(String token) {
		boolean isValidToken = false;		
		VerificationInfo vt = emailDaoService.getVerificationInfoByToken(token);		
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

	public boolean isEmailVerificationLinkValid(VerificationInfo vt) {
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
		emailDaoService.updateUserEmailVerificationStatus(verificationToken, status);
	}

	private void getVerificationTokenAndUpdateStatus(LoginInfo user) {
		VerificationInfo verificationToken = emailDaoService.getVerificationInfoByUser(user);
		updateVerificationLinkStatus(verificationToken, VerificationStatus.UNVERIFIED.getVerificationStatus());		
	}

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

	public String getSafePassword(String password) {
		String hPassword = "";
		if(! StringUtils.isEmpty(password)){
			hPassword = PasswordUtil.hashPassword(password);
		}		
		return hPassword;		
	}

	public String generateUserId(String email) {
		String userid = "";
		if(!StringUtils.isEmpty(email)) {
			userid = IdAppender.MAIL.getIdGenerator() + email;
		}
		return userid;
	}

	private LoginInfo getUserByEmail(String email) {
		System.out.println("service email :: "+email);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = emailDaoService.getUserByEmail(email);	
		}
		return usr;
	}

	public LoginInfo checkIfUserExistByEmail(String email) {
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = getUserByEmail(email);
		}		
		return ((usr != null) && isVerifiedUser(usr)) ? usr : null;		
	}
	
	public boolean isVerifiedUser(LoginInfo user) {
		if(user != null) {
			VerificationInfo vt = emailDaoService.getVerificationInfoByUser(user);
			if(vt != null && vt.getVerificationStatus().equals(VerificationStatus.VERIFIED.getVerificationStatus())) {
				return true;
			}
		}		
		return false;
	}
	public LoginInfo verifyUserByEmailAndPassword(LoginInfo user) {
		LoginInfo usr = null;
		if(user != null && !StringUtils.isEmpty(user.getEmail())) {
			LoginInfo u = getUserByEmail(user.getEmail());
			if((isPasswordVerified(user.getPassword(), u.getPassword())) && isVerifiedUser(u)) {
				usr = u;
			}
		}
		return usr;
	}

	private boolean isPasswordVerified(String userPassword, String savedPassword) {
		boolean isVerified = false;
		if(!StringUtils.isEmpty(savedPassword) && !StringUtils.isEmpty(userPassword)) {
			isVerified  = PasswordUtil.checkPassword(userPassword, savedPassword);
		}
		return isVerified;
	}

	public LoginInfo getCreatedUserByEmail(String email) {
		LoginInfo usr = getUserByEmail(email);
		return usr!=null ? usr :null;
	}

}
