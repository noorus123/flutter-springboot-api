package com.app.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.family.dao.RepositoryService;
import com.app.family.enums.IdAppender;
import com.app.family.enums.SignInMode;
import com.app.family.models.LoginInfo;
import com.app.family.service.RegistrationService;
import com.app.family.utils.LoginIdGenerator;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	RepositoryService repositoryService;	

	@Override
	public LoginInfo saveUser(LoginInfo u) {
		System.out.println("executing ::: saveUser");
		LoginInfo user = null;
		if(u != null) {	
			u = createUserIdbySignInMode(u);					
			user = repositoryService.saveLoginInfo(u);			
		}		
		return user;
	}

	private LoginInfo createUserIdbySignInMode(LoginInfo u) {
		String appender=null;
		if(u.getSignInMode().equals(SignInMode.FACEBOOK.getText())) {
			appender = IdAppender.FACEBOOK.getText();
			u.setLoginId(LoginIdGenerator.generateUserId(u.getFbId(), appender));	
		}
		if(u.getSignInMode().equals(SignInMode.GOOGLE.getText())) {
			appender = IdAppender.GOOGLE.getText();
			u.setLoginId(LoginIdGenerator.generateUserId(u.getEmail(), appender));	
		}
		if(u.getSignInMode().equals(SignInMode.PHONE.getText())) {
			appender = IdAppender.PHONE.getText();
			u.setLoginId(LoginIdGenerator.generateUserId(u.getPhone(), appender));	
		}
		if(u.getSignInMode().equals(SignInMode.EMAIL.getText())) {
			appender = IdAppender.EMAIL.getText();
			u.setLoginId(LoginIdGenerator.generateUserId(u.getEmail(), appender));	
		}
		return u;
	}

	@Override
	public LoginInfo checkIfUserExist(String loginText, String signInMode) {
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(loginText) && !StringUtils.isEmpty(signInMode)) {
			usr = getUserByLoginTextInSignInMode(loginText, signInMode);
		}		
		return usr;	
	}

	private LoginInfo getUserByLoginTextInSignInMode(String loginText, String signInMode) {
		LoginInfo user = null;
		if(signInMode.equals(SignInMode.PHONE.getText())) {
			user = repositoryService.getUserByPhone(loginText);
		}
		if(signInMode.equals(SignInMode.FACEBOOK.getText())) {
			user = repositoryService.getUserByFBId(loginText);
		}
		if(signInMode.equals(SignInMode.GOOGLE.getText()) || signInMode.equals(SignInMode.EMAIL.getText())) {
			user = repositoryService.getUserByEmail(loginText);
		}		
		return user;
	}

}
