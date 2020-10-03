package com.app.family.service;

import java.util.List;

import com.app.family.models.PersonalInfo;
import com.app.family.models.PersonalInfoDTO;

public interface PersonalInfoService {

	public PersonalInfo saveUserPersonalInfo(PersonalInfo user);

	public PersonalInfo getUserPersonalInfoByLoginId(String loginId);
	
	public List<PersonalInfoDTO> getAllPersonalInfoDTO();
}
