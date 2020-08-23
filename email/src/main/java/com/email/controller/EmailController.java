package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.User;
import com.email.service.EmailService;

@RestController
@RequestMapping(value="/email")
public class EmailController {	

	@Autowired
	EmailService service;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String sendMail(@RequestBody User user) {
		String message = "";
		System.out.println("sending email....");
		User u = new User();
		u.setEmail(user.getEmail());
		u.setPassword(service.getSafePassword(user.getPassword()));
		u.setUserId(service.generateUserId(user.getEmail()));	
		u.setName(user.getName());
		try {			
			if(service.generateMail(u)) {
				message = "Mail sent successfully";
				System.out.println("Mail sent successfully");
			}			
		}catch (Exception e) {
			System.out.println("Failed sending email.... "+ e.getMessage());
			
		}
		return message;
	}
	
	@RequestMapping(value = "/emailVerified/{encodedString}")
    public String verifyEmail(@PathVariable("encodedString") String encodedString) {
		String message = "";		
		String token = service.getTokenFromEncodedURLString(encodedString);		
		if(service.verifyLinkAndUpdateStatus(token)) {
			message = "Welcome to the application !! Your signup has been completed successfully !!";
		}else{
			message = "Email Verification link has been expied";
		}		
        return message;   
    }
	
	@RequestMapping(value="/user/{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable(value = "email") String email) {
        User usr = service.getUserByEmail(email); 
        return usr;
	}
}
