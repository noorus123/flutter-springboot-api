package com.app.family.service;

import com.app.family.models.LoginInfo;

public interface PhoneService {

	public LoginInfo savePhoneUser(LoginInfo u);

	public LoginInfo checkIfUserExistByPhone(String phone);

	public LoginInfo verifyUserByPhoneAndPassword(LoginInfo user);
	
	

}
