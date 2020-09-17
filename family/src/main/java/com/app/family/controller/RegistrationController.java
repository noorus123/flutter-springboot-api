package com.app.family.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.models.LoginInfo;
import com.app.family.service.RegistrationService;

@RestController
@RequestMapping(value="/account")
public class RegistrationController {

	@Autowired
	RegistrationService service;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public LoginInfo addUser(@RequestBody LoginInfo user) {
		System.out.println("executing ::: addUser");
		LoginInfo usr = null;		
		try {			
			usr = service.saveUser(user);			
		}catch (Exception e) {
			System.out.println("Failed adding user :: "+ e.getMessage());	
		}
		return usr;
	}
	
	@RequestMapping(value="/checkUser/{signInMode}/{loginText}", method = RequestMethod.GET)
    public LoginInfo checkIfUserExist(@PathVariable(value = "signInMode") String signInMode, @PathVariable(value = "loginText") String loginText) {
		System.out.println("executing ::: checkIfUserExist");
		LoginInfo usr = service.checkIfUserExist(loginText, signInMode);
		return usr!=null ? usr : null; 		
	}
}
