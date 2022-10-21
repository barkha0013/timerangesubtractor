package com.test.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	private static final String DATE_FORMAT = "MM-dd-yyyy HH:mm";
	private static final String TIME_FORMAT = "HH:mm";
	private static final String strDate = "01-01-2022 ";
	
	public static long getEpochTime(String hourMin) {
		try {
			String dateString = strDate + hourMin;
			return new SimpleDateFormat(DATE_FORMAT).parse(dateString).getTime();
		}catch(ParseException pe) {
			System.out.println("Exception parsing date...");
		}
		return 0;
	}
	
	public static String getTimeFromEpoch(Long dateTime) {
		try {
			Date dt = new Date(dateTime);
			return new SimpleDateFormat(TIME_FORMAT).format(dt);
		}catch(Exception pe) {
			System.out.println("Exception formatting date...");
		}
		return "";
	}
}
