/**
 * 
 */
package com.dashda.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dashda.service.components.ServicesManager;

/**
 * @author mhanafy
 *
 */
public class DateValidator {

	public static boolean isThisDateValid(String dateToValidate, String dateFromat){
		
		final Logger log = LoggerFactory.getLogger(DateValidator.class);
		
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			
			log.info(date+"");
		
		} catch (ParseException e) {
			
			log.error("ParseException :::: ", e);
			return false;
		}
		
		return true;
	}
}
