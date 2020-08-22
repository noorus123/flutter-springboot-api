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
import com.email.model.User;
import com.email.model.VerificationStatus;
import com.email.model.VerificationToken;
import com.email.util.DateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailService {	
	
	private static final String EMAIL_VERIFICATION_URL = "http://localhost:8080/email/emailVerified/";    
	
	/*@Autowired
	SequenceGeneratorService sequenceService;*/
	
	@Autowired
	private EmailConfiguration email;
	
	@Autowired
	EncoderDecoderService encoderDecoderService;
	
	@Autowired
	EmailDaoService emailDaoService;
	
	private final Configuration templates;
	
	@Autowired
	EmailService(Configuration templates){
        this.templates = templates;
    }
	
	VerificationToken verificationToken = new VerificationToken();
	
	public Boolean generateMail(User user){
		Properties props = new Properties();
		System.out.println("email.getHost()"+email.getHost());
		System.out.println("email.getPort()"+email.getPort());
		props.put("mail.smtp.host", email.getHost()); 
		props.put("mail.smtp.port", email.getPort()); 
		props.put("mail.smtp.auth", email.getAuth()); 
		props.put("mail.smtp.starttls.enable", email.getEnable()); 

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFromEmail(), email.getPassword());
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
          //msg.setText(email.getBody()+" "+"Text bodyyyyyyyyyy ....", UTF);
          msg.setContent(getBodyForEmailVerificationLink(user), "text/html");
 
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nooruskhan786@gmail.com", false));
          Transport.send(msg); 
          updateVerificationLinkStatus(verificationToken, VerificationStatus.UNVERIFIED.getVerificationStatus());
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
            System.out.println("user.toString() ====>>>>"+user.toString());
            Map<String, String> map = getVerificationTokenMap(user);
            body = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
        } catch (Exception ex) {
        	System.out.println("failed in preparing verification link "+ ex.getMessage());
        }
    	return body;    	
    }

	private Map<String, String> getVerificationTokenMap(User user) {
		Map<String, String> map = new HashMap<>();
		//verificationToken.setId(sequenceService.generateSequence(VerificationToken.SEQUENCE_NAME));
		String uniqueKey = UUID.randomUUID().toString();
		verificationToken.setToken(uniqueKey);
		String originalTokenString = user.getUserName()+VerificationToken.TOKEN_SEPARATOR+uniqueKey;
		String encodedString = encoderDecoderService.encodeString(originalTokenString);
		String encodedVerificationUrl = EMAIL_VERIFICATION_URL+encodedString;
		verificationToken.setVerificationURL(encodedVerificationUrl);
		map.put("VERIFICATION_URL", encodedVerificationUrl);
		verificationToken.setUser(user);
		System.out.println(user.toString());
		emailDaoService.saveUser(user);
		System.out.println(verificationToken.toString());
		emailDaoService.saveVerificationToken(verificationToken);
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
	
	public String getTokenFromEncodedURLString(String encodedString){
		String token = "";
		if(! StringUtils.isEmpty(encodedString)) {
			String originalString = encoderDecoderService.decodeString(encodedString);
			String originalValues[] = originalString.split(VerificationToken.TOKEN_SEPARATOR);
			if(originalValues!=null && originalValues.length == 2) {
				token = originalValues[1];
			}			
		}	
		return token;
	}	
}
