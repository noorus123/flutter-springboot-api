package com.email.service;

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

import com.email.dao.EmailDaoService;
import com.email.model.EmailConfiguration;
import com.email.model.IdGenerator;
import com.email.model.User;
import com.email.model.VerificationStatus;
import com.email.model.VerificationToken;
import com.email.util.DateUtil;
import com.email.util.EncoderDecoderUtil;
import com.email.util.PasswordUtil;

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
	
	public Boolean generateMail(User user){
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

    private Boolean sendEmail(Session session,EmailConfiguration email, User user){    	
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
    
    private String getBodyForEmailVerificationLink(User user) {
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

    private Map<String, String> getVerificationTokenMap(User user) {
    	Map<String, String> map = new HashMap<>();
    	VerificationToken verificationToken = emailDaoService.getVerificationTokenByUser(user);
    	if(verificationToken == null) {
    		verificationToken = new VerificationToken();
    		String uniqueKey = UUID.randomUUID().toString();
    		verificationToken.setToken(uniqueKey);
    		String originalTokenString = user.getName()+VerificationToken.TOKEN_SEPARATOR+uniqueKey;
    		String encodedString = EncoderDecoderUtil.encodeString(originalTokenString);
    		String encodedVerificationUrl = email.getEmailVerificationLinkURL()+encodedString;
    		verificationToken.setVerificationUrl(encodedVerificationUrl);
    		map.put("VERIFICATION_URL", encodedVerificationUrl);
    		verificationToken.setUser(user);
    		System.out.println(user.toString());
    		emailDaoService.saveUser(user);
    		System.out.println(verificationToken.toString());
    		emailDaoService.saveVerificationToken(verificationToken);    		
    	} else {
    		map.put("VERIFICATION_URL", verificationToken.getVerificationUrl());    		
    	}
    	return map;
    }
	
	
	public boolean verifyLinkAndUpdateStatus(String token) {
		boolean isValidToken = false;		
		VerificationToken vt = emailDaoService.getVerificationTokenByToken(token);		
		if(vt != null) {
			isValidToken = isEmailVerificationLinkValid(vt);
		}else {
			System.out.println("Expired :: token is not Valid");
		}
		if(isValidToken) {
			updateVerificationLinkStatus(vt, VerificationStatus.VERIFIED.getVerificationStatus());
		}
		return isValidToken;		
	}

	public boolean isEmailVerificationLinkValid(VerificationToken vt) {
		boolean isValid = false;		
		if(vt != null) {
			String create = vt.getCreateDate();
			Date verifyDate = new Date();
			Date d1 = DateUtil.StringToDate(create);
			Date expiredDate = DateUtil.calculateExpiryDate(d1, vt.EXPIRATION);	
			if(expiredDate != null) {
				isValid = verifyDate.before(expiredDate) || verifyDate.equals(expiredDate);
			}else {
				System.out.println("expiredDate calculated as empty !!");
			}			
		}
		return isValid;
	}
	
	
	private void updateVerificationLinkStatus(VerificationToken verificationToken, String status) {
		emailDaoService.updateUserEmailVerificationStatus(verificationToken, status);
	}

	private void getVerificationTokenAndUpdateStatus(User user) {
		VerificationToken verificationToken = emailDaoService.getVerificationTokenByUser(user);
		updateVerificationLinkStatus(verificationToken, VerificationStatus.UNVERIFIED.getVerificationStatus());		
	}
	
	public String getTokenFromEncodedURLString(String encodedString){
		String token = "";
		if(! StringUtils.isEmpty(encodedString)) {
			String originalString = EncoderDecoderUtil.decodeString(encodedString);
			String originalValues[] = originalString.split(VerificationToken.TOKEN_SEPARATOR);
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
			userid = IdGenerator.MAIL.getIdGenerator() + email;
		}
		return userid;
	}

	public User getUserByEmail(String email) {
		System.out.println("service email :: "+email);
		User usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = emailDaoService.getUserByEmail(email);	
		}
		return usr;
	}

	public User verifyUserByEmail(User user) {
		User usr = null;
		if(user != null && !StringUtils.isEmpty(user.getEmail())) {
			User u = getUserByEmail(user.getEmail());
			if((isPasswordVerified(user.getPassword(), u.getPassword())) &&
				(u.getVerificationStatus().equals(VerificationStatus.VERIFIED.getVerificationStatus()))) {
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
}
