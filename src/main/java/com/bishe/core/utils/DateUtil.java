package com.bishe.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 日期工具类
 */
public final class DateUtil {
	/**
	 * Log of the class
	 */
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 缺省的日期匹配模式
	 */
	private static String defaultDatePattern = null;

	/**
	 * 时间匹配模式
	 */
	private static String timePattern = "HH:mm:ss";
	
	/**
	 * 构造私有化
	 */
	private DateUtil() {
	}

	/**
	 * 获得默认的日期匹配格式
	 */
	public static synchronized String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		defaultDatePattern = ResourceBundle.getBundle(
				ResBundleUtil.DEFAULT_BUNDLE, locale).getString("date.format");
		return defaultDatePattern;
	}

	/**
	 * 获取格式化后的日期字符串
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 根据字符串转换为日期对象
	 * @param aMask
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	 
	public static Date convertStringToDate(String aMask, String strDate){
		SimpleDateFormat df = null;
		Date date = null;
		if (aMask == null || aMask.length() == 0) {
			aMask = getDatePattern();
		}
		if (StringUtils.isNotBlank(strDate)) {
			df = new SimpleDateFormat(aMask);
			try {
				date = df.parse(strDate);
			} catch (ParseException e) {
				logger.warn("日期格式转换错误{}", aMask);
			}
		}
		return date;
	}

	/**
	 * 获取当前的时间HH:mm:ss
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}


	/**
	 * 根据指定的日期格式将日期对象转换为字符串
	 * @param aMask
	 * @param aDate
	 * @return
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = null;
		if (aDate != null) {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 将日期对象转换为默认格式字符串
	 * @param aDate
	 * @return
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}
	

	/**
	 * 比较两个日期相差天数
	 * @param sDate 较小日期
	 * @param bDate 较大日期
	 * @return
	 */
	public static long compareDate(Date sDate, Date bDate){
		long sTime = sDate.getTime();
		long bTime = bDate.getTime();
		if(bTime < sTime){
			return -1;
		}
		long betweenTime = bTime-sTime;
		long betweenDays= betweenTime / (1000 * 60 * 60 * 24);
        return betweenDays;
	}
}
