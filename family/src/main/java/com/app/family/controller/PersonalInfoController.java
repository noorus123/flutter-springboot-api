package com.app.family.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.models.PersonalInfo;
import com.app.family.service.PersonalInfoService;

@RestController
@RequestMapping(value="/personal")
public class PersonalInfoController {

	@Autowired
	PersonalInfoService service;
	
	@RequestMapping(value = "/addUserPersonalInfo", method = RequestMethod.POST)
	public PersonalInfo addUserPersonalInfo(@RequestBody PersonalInfo user) {
		System.out.println("executing ::: addUserPersonalInfo");
		PersonalInfo usr = null;
		if (user != null) {
			System.out.println("executing ::: addUserPersonalInfo with user values " + user.toString());
			try {
				usr = service.saveUserPersonalInfo(user);
			} catch (Exception e) {
				System.out.println("Failed adding userPersonalInfo .... " + e.getMessage());
			} 
		}
		return usr;
	}
	
	@RequestMapping(value="/getUserPersonalInfo/{loginId}", method = RequestMethod.GET)
    public PersonalInfo checkIfPersonalInfoExist(@PathVariable(value = "loginId") String loginId) {
		System.out.println("executing ::: checkIfPersonalInfoExist");
		PersonalInfo usr = service.getUserPersonalInfoByLoginId(loginId);
		return usr!=null ? usr : null; 		
	}	
}
