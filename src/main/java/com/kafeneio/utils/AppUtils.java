package com.kafeneio.utils;

import java.util.Calendar;

public class AppUtils {
public static String createDate(){
	Calendar now = Calendar.getInstance();
	int year=now.get( Calendar.YEAR) ;
	int month=now.get(Calendar.MONTH);
	int day=now.get(Calendar.DATE);
	return year+"-"+month+"-"+day;
}
}
