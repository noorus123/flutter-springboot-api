package com.app.family.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.app.family.dao.RepositoryService;
import com.app.family.models.PersonalInfo;
import com.app.family.models.PersonalInfoDTO;
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

	@Override
	public PersonalInfo getUserPersonalInfoByLoginId(String loginId) {
		PersonalInfo usr = null;
		if(!StringUtils.isEmpty(loginId)) {
			usr = repositoryService.getPersonalInfoByPersonalId(loginId);
		}		
		return usr;	
	}
	
	@Override
	public List<PersonalInfoDTO> getAllPersonalInfoDTO() {
		System.out.println("executing getAllPersonalInfoDTO");
		List<PersonalInfoDTO> usr = null;
		usr = repositoryService.getAllPersonalInfoSelectedFields();
		return (!CollectionUtils.isEmpty(usr))  ? usr : null;
	}

}
