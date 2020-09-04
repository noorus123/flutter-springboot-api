package com.app.family.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.models.LoginInfo;
import com.app.family.service.PhoneService;

@RestController
@RequestMapping(value="/phone")
public class PhoneController {

	@Autowired
	PhoneService service;
	
	@RequestMapping(value = "/addPhoneUser", method = RequestMethod.POST)
	public LoginInfo addPhoneUser(@RequestBody LoginInfo user) {
		System.out.println("executing ::: addPhoneUser");
		LoginInfo usr = null;		
		try {			
			usr = service.savePhoneUser(user);			
		}catch (Exception e) {
			System.out.println("Failed adding user with phone .... "+ e.getMessage());	
		}
		return usr;
	}
	
	@RequestMapping(value="/checkUser/{phone}", method = RequestMethod.GET)
    public LoginInfo checkIfUserByPhoneExist(@PathVariable(value = "phone") String phone) {
		System.out.println("executing ::: checkIfUserByPhoneExist");
		LoginInfo usr = service.checkIfUserExistByPhone(phone);
		return usr!=null ? usr : null; 		
	}
	
	@RequestMapping(value = "/loginVerification", method = RequestMethod.POST)
	public LoginInfo verifyPhoneAndPassword(@RequestBody LoginInfo user) {
		System.out.println("executing ::: verifyPhoneAndPassword");
		LoginInfo usr = service.verifyUserByPhoneAndPassword(user); 
        return usr!=null ? usr : null;        
	}
}
