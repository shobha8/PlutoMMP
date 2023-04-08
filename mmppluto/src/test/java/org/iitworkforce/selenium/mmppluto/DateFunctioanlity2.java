package org.iitworkforce.selenium.mmppluto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFunctioanlity2 {

	public static void main(String[] args) {
		DateFunctioanlity2  d2 = new DateFunctioanlity2();
		//String result = d2.getFutureDate(10);
		
		String result=d2.getFutureDate(25,"MM/dd/yyyy");
		System.out.println(result);
		String dateArr[]=result.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);

		
		
	}
	public String getFutureDate(int noofdays,String pattern)
	{

		Calendar cal = 	Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, noofdays);
		
		Date d = cal.getTime();
		System.out.println("Current Date :" + d);
				
		SimpleDateFormat sdf = new SimpleDateFormat();
		String defaultformat = sdf.format(d);
		System.out.println(defaultformat);
		
		sdf = new SimpleDateFormat(pattern);
		String formatDate = sdf.format(d);
		System.out.println(formatDate);
		return formatDate;
	}

}
