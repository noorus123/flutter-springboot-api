package com.app.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.family.dao.RepositoryService;
import com.app.family.models.PersonalInfo;
import com.app.family.service.PersonalInfoService;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {
	
	@Autowired
	RepositoryService repositoryService;

	@Override
	public PersonalInfo saveUserPersonalInfo(PersonalInfo u) {
		System.out.println("executing ::: saveUserPersonalInfo");
		PersonalInfo user = null;
		if(u != null) {				
			user = repositoryService.savePersonalInfo(u);			
		}		
		return user;
	}

}
