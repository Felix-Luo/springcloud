package com.msg.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {

	public DateFormatUtil() {
	}

	public static String format(String date) {
		String formatString = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatString = df.format(date);
		return formatString;
	}
	public static String getDateStringByformat(String dateStr,String format) {
		if(dateStr==null||"".equals(dateStr)){
			return "";
		}
		if(format==null||"".equals(format)){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		String formatString = "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		formatString = df.format(dateStr);
		return formatString;
	}
	public static String getDateStringByformat(Date data, String formatType) {
		return new SimpleDateFormat(formatType).format(data);
	}
	@SuppressWarnings("rawtypes")
	public String getStr(ArrayList cols, ArrayList values) {
		String rtn = "";
		if (cols.size() == values.size()) {
			rtn += cols.get(0).toString() + "=" + values.get(0).toString();
			for (int i = 1; i < cols.size(); i++) {
				rtn += "#" + cols.get(i).toString() + "=" + values.get(i).toString();
			}
		}
		return rtn;
	}

	public String getHour(String startTime) {

		String hour = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = new Date();
			start = df.parse(startTime);
			SimpleDateFormat ff = new SimpleDateFormat("HH");
			hour = ff.format(start);
			return hour;
		} catch (ParseException e) {
			return hour;

		}

	}

	public String getWeek(String startTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = new Date();
		try {
			start = df.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(start);
		return Integer.toString(rightNow.get(Calendar.DAY_OF_WEEK) - 1);
	}

	public static String getDay(String startTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = new Date();
		try {
			start = df.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat ff = new SimpleDateFormat("dd");
		String day = ff.format(start);
		return day;
	}

	public static String formatTime(Date date) {
		String dateString = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateString = df.format(date);
		return dateString;
	}

	public static long formatTime(String date) {
		long time = -1L;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = df.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

	public static String formatTime(Date date, String dfStr) {
		String formatString = "";
		SimpleDateFormat df = new SimpleDateFormat(dfStr);
		formatString = df.format(date);
		return formatString;
	}

	public static Date getDateByFormat(String dateStr, String dfStr) {
		Date d = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(dfStr);
			d = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 时间转换为不含特殊字符的字符串
	 * 
	 * @param date
	 * 
	 * @return 返回时间字符串
	 */
	public static String formatTimeToStr(Date date) {
		String dateString = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		dateString = df.format(date);
		return dateString;
	}

	/**
	 * 日期加毫秒数
	 * 
	 * @param date 日期
	 * 
	 * @param millis 毫秒数
	 * 
	 * @return 返回相加后的日期
	 * 
	 */
	public static Date addMillis(Date date, long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + millis);
		return c.getTime();
	}

	/**
	 * 日期减毫秒数
	 * 
	 * @param date 日期
	 * 
	 * @param millis 毫秒数
	 * 
	 * @return 返回相加减的日期
	 * 
	 */
	public static Date diffMillis(Date date, long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - millis);
		return c.getTime();
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date 日期
	 * 
	 * @return 返回毫秒
	 * 
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 日期加天数
	 * 
	 * @param date 日期
	 * 
	 * @param days 天数
	 * 
	 * @return 返回相加后的日期
	 * 
	 */
	public static Date addDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) days) * 24 * 60 * 60 * 1000);
		return c.getTime();
	}

	/**
	 * 日期减天数
	 * 
	 * @param date 日期
	 * @param days 天数
	 * @return 返回相减后的日期
	 * 
	 */
	public static Date diffDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) days) * 24 * 60 * 60 * 1000);
		return c.getTime();
	}

	/**
	 * 日期减分钟
	 * 
	 * @param date 日期
	 * @param minuts 分钟数
	 * @return 返回相减后的日期
	 * 
	 */
	public static Date diffDateMinutes(Date date, int minuts) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) minuts) * 60 * 1000);
		return c.getTime();
	}
	/**
	 * 日期减秒数
	 *
	 * @param date 日期
	 * @param seconds 秒数
	 * @return 返回相减后的日期
	 *
	 */
	public static Date subDateSeconds(Date date, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) seconds) * 1000);
		return c.getTime();
	}
	/**
	 * 日期加秒数
	 *
	 * @param date 日期
	 * @param seconds 秒数
	 * @return 返回相加后的日期
	 *
	 */
	public static Date addDateSeconds(Date date, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) seconds) * 1000);
		return c.getTime();
	}
	/**
	 * 获取本周第一天的日期
	 * @return
	 */
	public static Date getWeekStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	/**
	 * 获取某天的开始时间
	 * @param date
	 * @return
	 */
	public static Date getDaysStart(Date date) {
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf0.format(date) ;
		dateStr = dateStr + " 00:00:00" ;
		Date reDate = null;
		try {
			reDate = sdf1.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reDate ;
	}

	/**
	 * 获取某天的结束时间
	 * @param date
	 * @return
	 */
	public static Date getDaysEnd(Date date) {
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf0.format(date) ;
		dateStr = dateStr + " 23:59:59" ;
		Date reDate = null;
		try {
			reDate = sdf1.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reDate ;
	}

	/**
	 * 获取当前月第一天
	 * 
	 * @return
	 */
	public static Date getMonthStart() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return c.getTime();
	}

	/**
	 * 获取当前月最后一天
	 * 
	 * @return
	 */
	public static Date getMonthLast() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		return ca.getTime();
	}

	public static Timestamp getDateTimeStamp(Date date){
		Timestamp timestamp = null;
		if(date!=null){
			timestamp = new Timestamp(date.getTime());
		}else{
			/*获取当前系统时间戳*/
			timestamp = new Timestamp(new Date().getTime());
		}
		return timestamp;
	}

	public static long getTimeByDate(Date date){
		if(date==null){
			date = new Date();
		}
		return date.getTime();
	}

	public static void main(String[] args){
		/*Date date = new Date();
		date.setTime(1563261827378L);
		String str = getDateStringByformat(date,"yyyy-MM-dd HH:mm:ss");*/
		Date date = getDateByFormat("2019-07-18 10:19:20","yyyy-MM-dd HH:mm:ss");
		String str = formatTime(date,"yyyy-MM-dd");
		System.out.println(str);
	}


}
