package com.app.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.family.dao.RepositoryService;
import com.app.family.enums.IdAppender;
import com.app.family.models.LoginInfo;
import com.app.family.service.PhoneService;
import com.app.family.utils.LoginIdGenerator;
import com.app.family.utils.PasswordUtil;

@Service
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	RepositoryService repositoryService;	
	
	@Override
	public LoginInfo savePhoneUser(LoginInfo u) {
		System.out.println("executing ::: savePhoneUser");
		LoginInfo user = null;
		if(u != null) {	
			u.setPassword(PasswordUtil.getSafePassword(u.getPassword()));					
			u.setLoginId(LoginIdGenerator.generateUserId(u.getPhone(), IdAppender.PHONE.getText()));			
			user = repositoryService.saveLoginInfo(u);			
		}		
		return user;
	}

	@Override
	public LoginInfo checkIfUserExistByPhone(String phone) {
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(phone)) {
			usr = getUserByPhone(phone);
		}		
		return usr;	
	}

	@Override
	public LoginInfo verifyUserByPhoneAndPassword(LoginInfo user) {
		LoginInfo usr = null;
		if(user != null && !StringUtils.isEmpty(user.getPhone())) {
			LoginInfo u = getUserByPhone(user.getPhone());
			if(u!=null && PasswordUtil.isPasswordVerified(user.getPassword(), u.getPassword())) {
				usr = u;
			}else {
				System.out.println("password verification failed !!");
			}
		}
		
		return usr;
	}

	private LoginInfo getUserByPhone(String phone) {
		System.out.println("service phone :: "+phone);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(phone)) {
			usr = repositoryService.getUserByPhone(phone);	
		}
		return usr;
	}	
}
