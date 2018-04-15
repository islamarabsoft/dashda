/**
 * 
 */
package com.dashda.utilities;

import java.util.Calendar;
import java.util.Date;

/**
 * @author mhanafy
 *
 */
public class CompareDateWithoutTime {

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * 1 Start date is before end date <br>
	 * -1 Start date is after end date <br>
	 * 0 Start date and end date are equal <br>
	 */
	public static int compareTwoDates(Date startDate, Date endDate) {
		Date sDate = getZeroTimeDate(startDate);
		Date eDate = getZeroTimeDate(endDate);
		if (sDate.before(eDate)) {
			return 1;
		}
		if (sDate.after(eDate)) {
			return -1;
		}
		return 0;
	}

	private static Date getZeroTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

}
