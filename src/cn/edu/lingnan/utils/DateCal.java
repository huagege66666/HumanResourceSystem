package cn.edu.lingnan.utils;

import java.util.Calendar;
import java.util.Date;

public class DateCal {

	/**
	 * ���ָ���·ݺ������
	 * @param month
	 * @return
	 */
	public static Date afterMonth(int month){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return new Date(c.getTime().getTime());
	}
	
}
