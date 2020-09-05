package com.app.family.service;

import com.app.family.models.LoginInfo;

public interface RegistrationService {

	public LoginInfo saveUser(LoginInfo user);

	public LoginInfo checkIfUserExist(String loginText, String signInMode);

}
