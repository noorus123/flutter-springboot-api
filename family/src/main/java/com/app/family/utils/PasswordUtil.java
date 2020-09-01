package com.app.family.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
	
	public static String hashPassword(String password) {
		String hashPassowrd = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashPassowrd;
	}
	
	public static boolean checkPassword(String password, String hashPassword) {
		boolean isPasswordCorrect = BCrypt.checkpw(password, hashPassword);
		return isPasswordCorrect;
	}	
}
