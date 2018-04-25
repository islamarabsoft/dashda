/**
 * 
 */
package com.dashda.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dashda.service.components.ServicesManager;

/**
 * @author mhanafy
 *
 */
public class DateValidator {

	public static final String DATE_FORMATE_PATTERN = "yyyy-MM-dd";
	
	public static boolean isThisDateValid(String dateToValidate){
		
		final Logger log = LoggerFactory.getLogger(DateValidator.class);
		
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_PATTERN);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			
		
		} catch (ParseException e) {
			
			log.error("ParseException :::: ", e);
			return false;
		}
		
		return true;
	}
	
	public static String dateFormate(String date) throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s", Locale.US);
	
		return dateFormate(inputFormat.parse(date)); 
	}
	
	public static String dateFormate(Date date) throws ParseException {
		return new SimpleDateFormat(DateValidator.DATE_FORMATE_PATTERN).format(date);
	}
	
	public static Date convertToDate(String date, String formate) throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat(formate);
		return inputFormat.parse(date);
	}
}
