package com.app.family.service;

import com.app.family.models.LoginInfo;

public interface EmailService {

	public Boolean generateMailAndAddUser(LoginInfo user);
	public boolean verifyLinkAndUpdateStatus(String token);
	public String getTokenFromEncodedURLString(String encodedString);
	public LoginInfo checkIfUserExistByEmail(String email);
	public LoginInfo verifyUserByEmailAndPassword(LoginInfo user);
	public LoginInfo getCreatedUserByEmail(String email);

}
