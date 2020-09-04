package com.app.family.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;

public class PasswordUtil {
	
	private static String hashPassword(String password) {
		String hashPassowrd = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashPassowrd;
	}
	
	private static boolean checkPassword(String password, String hashPassword) {
		boolean isPasswordCorrect = BCrypt.checkpw(password, hashPassword);
		return isPasswordCorrect;
	}	
	
	public static String getSafePassword(String password) {
		String hPassword = "";
		if(! StringUtils.isEmpty(password)){
			hPassword = hashPassword(password);
		}		
		return hPassword;		
	}
	
	public static boolean isPasswordVerified(String userPassword, String savedPassword) {
		boolean isVerified = false;
		if(!StringUtils.isEmpty(savedPassword) && !StringUtils.isEmpty(userPassword)) {
			isVerified  = checkPassword(userPassword, savedPassword);
		}
		return isVerified;
	}
	public static void main(String[] args) {
		System.out.println(hashPassword("aa"));
	}
}
