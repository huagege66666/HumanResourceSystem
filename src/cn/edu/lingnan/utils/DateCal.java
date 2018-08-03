package cn.edu.lingnan.utils;

import java.util.Calendar;
import java.util.Date;

public class DateCal {

	/**
	 * 获得指定月份后的日期
	 * @param month
	 * @return
	 */
	public static Date afterMonth(int month){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return new Date(c.getTime().getTime());
	}
	
}
