package com.email.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.email.model.EmailConfiguration;

@Service
public class EmailService {
	
	@Autowired
	private EmailConfiguration email;
	
	public Boolean generateMail(){
		Properties props = new Properties();
		System.out.println("email.getHost()"+email.getHost());
		System.out.println("email.getPort()" + email.getPort());
		props.put("mail.smtp.host", email.getHost()); 
		props.put("mail.smtp.port", email.getPort()); 
		props.put("mail.smtp.auth", email.getAuth()); 
		props.put("mail.smtp.starttls.enable", email.getEnable()); 

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFromEmail(), email.getPassword());
			}
		};
		return sendEmail(Session.getInstance(props, auth),email);	
	}

    private Boolean sendEmail(Session session,EmailConfiguration email){    	
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
          msg.setText(email.getBody()+" "+"Text bodyyyyyyyyyy ....", UTF); 
          msg.setSentDate(new Date());
 
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nooruskhan786@gmail.com", false));
          Transport.send(msg); 
          mailFlag = Boolean.TRUE;
        }
        catch (Exception e) {
          mailFlag = Boolean.FALSE;
        }
		return mailFlag;
    }
}
