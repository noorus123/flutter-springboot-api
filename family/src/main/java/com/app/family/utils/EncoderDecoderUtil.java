package com.app.family.utils;

import java.util.Base64;

public class EncoderDecoderUtil {
	
	public static String encodeString(String originalString){
		String encodedString = null;
		if(originalString != null) {
			encodedString =  Base64.getUrlEncoder().encodeToString(originalString.getBytes());
		}		
		return encodedString;		
	}

	public static String decodeString(String encodedString){
		String originalString = null;
		if(encodedString != null) {
			byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedString);
			originalString = new String(decodedBytes);
		}		
		return originalString;		
	}
}
