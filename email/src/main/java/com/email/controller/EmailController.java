package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.User;
import com.email.model.VerificationStatus;
import com.email.model.VerificationToken;
import com.email.repository.UserRepository;
import com.email.repository.VerificationTokenRepository;
import com.email.service.EmailService;
import com.email.service.EncoderDecoderService;

@RestController
@RequestMapping(value="/email")
public class EmailController {	

	@Autowired
	EmailService service;

	@RequestMapping(value = "/sendEmail")
	public void sendMail() {
		System.out.println("sending email....");
		User user = new User();
		user.setEmail("bytewheel@gmail.com");
		user.setUserId("mail_bytewheel@gmail.com");		
		user.setUserName("Tinku jiya");
		try {			
			if(service.generateMail(user)) {
				System.out.println("Mail sent successfully");
			}			
		}catch (Exception e) {
			System.out.println("Failed sending email.... "+ e.getMessage());
			
		}
	}
	
	@RequestMapping(value = "/emailVerified/{encodedString}")
    public String sayHelloName(@PathVariable("encodedString") String encodedString) {
		String message = "";		
		String token = service.getTokenFromEncodedURLString(encodedString);		
		if(service.verifyLinkAndUpdateStatus(token)) {
			message = "Welcome to the application !! Your signup has been completed successfully !!";
		}else{
			message = "Email Verification link has been expied";
		}		
        return message;   
    }
}
