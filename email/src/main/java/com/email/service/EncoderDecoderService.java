package com.email.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class EncoderDecoderService {

	public String encodeString(String originalString){
		String encodedString = null;
		if(originalString != null) {
			encodedString =  Base64.getUrlEncoder().encodeToString(originalString.getBytes());
		}		
		return encodedString;		
	}

	public String decodeString(String encodedString){
		String originalString = null;
		if(encodedString != null) {
			byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedString);
			originalString = new String(decodedBytes);
		}		
		return originalString;		
	}

}
