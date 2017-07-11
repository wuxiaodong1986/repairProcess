package com.common.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils 
{
	public static String getNow(String formate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate);
		return simpleDateFormat.format(new Date());
	}
}
