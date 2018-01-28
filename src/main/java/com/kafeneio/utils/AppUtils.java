package com.kafeneio.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.kafeneio.constants.ApplicationConstant;

public class AppUtils {
	public static String createDate(){
		Calendar now = Calendar.getInstance();
		int year=now.get( Calendar.YEAR) ;
		int month=now.get(Calendar.MONTH);
		int day=now.get(Calendar.DATE);
		return year+"-"+month+"-"+day;
	}


	public static String getTomorrow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstant.DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1); // to get previous year add -1
		Date nextDay = cal.getTime();
		String tomorrow = dateFormat.format(nextDay);
		return tomorrow;
	}
	
	public static String getYesterday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstant.DATE_TIME_FORMAT);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1); // to get previous year add -1
		Date nextDay = cal.getTime();
		String tomorrow = dateFormat.format(nextDay);
		return tomorrow;
	}
}
