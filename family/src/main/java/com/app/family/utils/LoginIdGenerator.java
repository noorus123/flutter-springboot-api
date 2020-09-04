package com.app.family.utils;

import org.springframework.util.StringUtils;

public class LoginIdGenerator {
	
	public static String generateUserId(String email, String appender) {
		String userid = "";
		if(!StringUtils.isEmpty(email) && !StringUtils.isEmpty(appender)) {
			userid = appender + email;
		}
		return userid;
	}

}
