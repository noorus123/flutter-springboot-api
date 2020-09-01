package com.app.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.enums.SignInMode;
import com.app.family.models.LoginInfo;
import com.app.family.service.EmailService;


@RestController
@RequestMapping(value="/email")
public class EmailController {	

	@Autowired
	EmailService service;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public LoginInfo sendMail(@RequestBody LoginInfo user) {
		System.out.println("executing ::: sendEmail");
		LoginInfo usr = null;
		System.out.println("sending email....");
		LoginInfo u = new LoginInfo();
		u.setEmail(user.getEmail());
		u.setSignInMode(SignInMode.EMAIL.getText());
		u.setPassword(service.getSafePassword(user.getPassword()));
		u.setLoginId(service.generateUserId(user.getEmail()));	
		u.setName(user.getName());
		System.out.println("controller ::: "+u.toString());
		try {			
			if(service.generateMail(u)) {
				System.out.println("Mail sent successfully to user ");
				usr = service.getCreatedUserByEmail(user.getEmail());				
			}			
		}catch (Exception e) {
			System.out.println("Failed sending email.... "+ e.getMessage());
			
		}
		return usr;
	}
	
	@RequestMapping(value = "/emailLinkVerification/{encodedString}")
    public String emailVerification(@PathVariable("encodedString") String encodedString) {
		System.out.println("executing ::: emailVerification");
		String message = "";		
		String token = service.getTokenFromEncodedURLString(encodedString);		
		if(service.verifyLinkAndUpdateStatus(token)) {
			message = "Welcome to the application !! Your signup has been completed successfully !!";
		}else{
			message = "Email Verification link has been expied";
		}		
        return message;   
    }
	
	@RequestMapping(value="/checkUser/{email}", method = RequestMethod.GET)
    public LoginInfo checkIfUserByEmailExist(@PathVariable(value = "email") String email) {
		System.out.println("executing ::: checkIfUserByEmailExist");
		LoginInfo usr = service.checkIfUserExistByEmail(email);
		return usr!=null ? usr : null; 		
	}
	
	@RequestMapping(value = "/loginVerification", method = RequestMethod.POST)
	public LoginInfo verifyMailAndPassword(@RequestBody LoginInfo user) {
		System.out.println("executing ::: verifyMailAndPassword");
		LoginInfo usr = service.verifyUserByEmailAndPassword(user); 
        return usr!=null ? usr : null;        
	}
}
