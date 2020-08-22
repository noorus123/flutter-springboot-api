package com.email.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtil {
	
	public static Date StringToDate(String strDate) {
		Date date = null;
		 try {
			 if(!StringUtils.isEmpty(strDate)) {
				 date = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(strDate);
			 }		
		} catch (ParseException e) {
			System.out.println("conversion process String to date declined :: "+e.getMessage());			
		}  
		return date;
	}
	
	public static String dateToString(Date date) {
		String strDate = null;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		if(date != null) {
			strDate = dateFormat.format(date);
		}		
		return strDate;
	}
	
	public static Date calculateExpiryDate(Date date, int expireDurationInMinute) {
		Date expiryDate = null;
		if(date != null) {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MINUTE, expireDurationInMinute);
	        expiryDate = new Date(cal.getTime().getTime()); 
		}        
        return expiryDate;
    }	
}
