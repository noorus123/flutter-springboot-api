package com.app.family.service;

import com.app.family.models.PersonalInfo;

public interface PersonalInfoService {

	public PersonalInfo saveUserPersonalInfo(PersonalInfo user);

	public PersonalInfo getUserPersonalInfoByLoginId(String loginId);
}
