/**
 * 
 */
package com.dashda.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dashda.service.components.ServicesManager;

/**
 * @author mhanafy
 *
 */
public class DateUtilities {

	public static final String DATE_FORMATE_PATTERN = "yyyy-MM-dd";
	
	public static boolean isThisDateValid(String dateToValidate){
		
		final Logger log = LoggerFactory.getLogger(DateUtilities.class);
		
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
		return new SimpleDateFormat(DateUtilities.DATE_FORMATE_PATTERN).format(date);
	}
	
	public static Date convertToDate(String date, String formate) throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat(formate);
		return inputFormat.parse(date);
	}
	
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * 1 date1 is before date2 <br>
	 * -1 date1 is after date2 <br>
	 * 0 date1 and date2 are equal <br>
	 */
	public static int compareTwoDates(Date date1, Date date2) {
		Date sDate = getZeroTimeDate(date1);
		Date eDate = getZeroTimeDate(date2);
		if (sDate.before(eDate)) {
			return 1;
		}
		if (sDate.after(eDate)) {
			return -1;
		}
		return 0;
	}

	public static Date getZeroTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
	
	public static Date increaseDateByHours(Date date, int hours) {
	    Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(date); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, hours); // adds one hour
	    
		return cal.getTime();
	}
	
	public static Date decreaseDateByHours(Date date, int hours) {
	    Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(date); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, -hours); // adds one hour
	    
		return cal.getTime();
	}
	
	
	public static Date calendarYear(int years) {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, years); // to get previous year add -1
		Date nextYear = cal.getTime();
		
		return nextYear;
	}
}
