package com.org.cygs.util.common;

import java.util.Calendar;

public class GetCurrentDate {

	Calendar currentDate = Calendar.getInstance();
	int currentYear = currentDate.get(Calendar.YEAR);
	int currentMonth = currentDate.get(Calendar.MONTH)+1;
	int currentDay = currentDate.get(Calendar.DATE);
	public int getCurrentYear()
	{
		return currentYear;
	}
	public int getCurrentMonth()
	{
		return currentMonth;
	}
	public int getCurrentDay()
	{
		return currentDay;
	}
}
