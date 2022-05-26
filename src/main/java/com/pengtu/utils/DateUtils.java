package com.pengtu.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.pengtu.Constants;

/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 * 
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 * @version $Revision: 1.2 $ $Date: 2016/03/16 03:16:44 $
 */
public class DateUtils {
	// ~ Static fields/initializers
	// =============================================

	private static Log log = LogFactory.getLog(DateUtils.class);
	public static String defaultDatePattern = "yyyy-MM-dd";
	public static String yearPattern = "yyyy年";
	public static String monthPattern = "yyyy年MM月";
	public static String dayTimePattern = "yyyy-MM-dd HH:mm:ss";
	public static int day  = 90;
	public static String timePattern = "yyyy年MM月dd日HH时mm分ss秒";
	// ~ Methods
	// ================================================================


	/**
	 * Return default datePattern (yyyy-MM-dd)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDefaultDatePattern() {
		try {
			defaultDatePattern = "yyyy-MM-dd";
		} catch (MissingResourceException mse) {
			defaultDatePattern = "yyyy-MM-dd";
		}

		return defaultDatePattern;
	}

	/**
	 * Return default datePattern (yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDefaultDateTimePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		try {
			dayTimePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY,
					locale).getString("datetime.format");
		} catch (MissingResourceException mse) {
			dayTimePattern = "yyyy-MM-dd HH:mm:ss";
		}
		return dayTimePattern;
	}


	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 核心String转化成date方法
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		if(StringUtils.isEmpty(strDate)){
			return (null);
		}
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	
	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 核心date转化成String方法
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String convertDateToString(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate == null) {
			// log.error("aDate is null!");
			return "";
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return returnValue;
	}
	
	/**
	 * 转化成系统默认的时间字符串类型 (yyyy-MM-dd)
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return convertDateToString(getDefaultDatePattern(), aDate);
	}
	/**
	 * 转化成系统默认的时间字符串类型 (yyyy-MM-dd HH:mm:ss)
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToStringV2(Date aDate) {
		return convertDateToString( "yyyy-MM-dd HH:mm:ss", aDate);
	}
	
	/**
	 * 转化成系统默认的时间字符串类型 (yyyy-MM-dd HH:mm:ss)
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToStringV3(Date aDate) {
		return convertDateToString("yyyyMMddHHmmss", aDate);
	}
	
	/**
	 *  系统默认时间串(yyyy-MM-dd)转化成的时间类型
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;
		aDate = convertStringToDate(getDefaultDatePattern(), strDate);
		return aDate;
	}
	
	public static Date convertStringToDate2(String strDate)
			throws ParseException {
		Date aDate = null;
		aDate = convertStringToDate("dd-MM-yyyy HH:mm", strDate);
		return aDate;
	}
	
	
     /**
      * 获得当前的日期
      * getToday:
      * 适用:
      * @return
      * @throws ParseException 
      * @exception 
      * @see Calendar
      * @since  1.0.0
      */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = convertDateToString(getDefaultDatePattern(), today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}
	
	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Date getTodayDate() throws ParseException {
		return getToday().getTime();
		
	}
	/**
	 * DFormat:
	 * 适用:
	 * @param value
	 * @return 
	 * @exception 
	 * @since  1.0.0
	*/
	public static String DFormat(String value) {
		if(StringUtils.isNotBlank(value) && value.length() > 9){
			return value.substring(0, 10);
		} else {
			return value;
		}
	}
	/**
	 * 
	 * yyyy-MM-dd HH:mm:ss";
	 * 
	 * @param strDate
	 *            the date to convert yyyy-MM-dd HH:mm:ss";
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDatetime(String strDate)
			throws ParseException {
		return convertStringToDate(getDefaultDateTimePattern(),strDate);
	}

	/**
	 * 
	 * yyyy-MM-dd HH:mm:ss;
	 * 
	 * @param dateTime
	 *             convert yyyy-MM-dd HH:mm:ss to ;
	 */
	public static String convertDatetimeToString(Date dateTime)
			throws ParseException {
			return convertDateToString(getDefaultDateTimePattern(),dateTime);
	}
	
	/**
	 * 转换成yyyy年MM月 形,字符串
	 * 
	 */
	public static String convertDateToMonthString(Date sDate) {
		return convertDateToString(monthPattern, sDate);

	}
	

	/**
	 * 转换成yyyy年 形,字符串
	 * 
	 */
	public static String convertDateToYearString(Date sDate) {
		return convertDateToString(yearPattern, sDate);

	}

	/**
	 * 转换成yyyy年 形,字符串字符串
	 */
	public static String convertDateToYearString(String s) {
		String sDate = null;
		if (s != null && s.length() >= 5) {
			sDate = s.substring(0, 5);
		}
		return sDate;

	}

	/**
	 * 转换成yyyy年MM月 形,字符串
	 */
	public static String convertDateToMonthString(String s) {
		String sDate = null;
		if (s != null && s.length() >= 8) {
			sDate = s.substring(0, 8);
		}
		return sDate;

	}
	/**
	 * 获得当前系统时间
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());

	}
	
	/**
	 * 获得当前系统时间
	 * 
	 * @return String
	 * @throws 
	 */
	public static String getCurrentStrDate() {
		return convertDateToString(new Date(System.currentTimeMillis()));

	}

	/**
	 * 获得当前年份
	 *    631wj int
	 */
	public static int getCurrentYear() {
		return getYearofDate(getCurrentDate());
	}
	/**
	 * 获得当前月份
	 * 631wj int
	 */
	public static int getCurrentMonth() {
		return getMonthofDate(getCurrentDate());
	}
	
	/**
	 * 获得时间的年份
	 *  631wj int
	 */
	public static Integer getYearofDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**获取上n个小时整点小时时间
	 * @param date
	 * @return
	 */
	public static String getLastHourTime(Date date,int n){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY)-n);
		date = ca.getTime();
		return sdf.format(date);
	}
	/**获取当前时间的整点小时时间
	 * @param date
	 * @return
	 */
	public static String getCurrHourTime(Date date){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		date = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return sdf.format(date);
	}


	/**获取当前时间的日期
	 * @param date
	 * @return
	 */
	public static String getCurrDayTime(Date date){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		date = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**获取当前时间的日期
	 * @param date
	 * @return
	 */
	public static String getCurrMonthTime(Date date){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		date = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}

	/**
	 * 获得日期的日
	 */
	public static int getDayofDate(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 * 获得日期的月份
	 */
	public static int getMonthofDate(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}


	/**
	 * 获得当前月份的字符串类型 形如(yyyy年MM月)
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getCurrentMonthStrDate() {
		return convertDateToMonthString(new Date(System.currentTimeMillis()));

	}

	/**
	 * 将 yyyy、yyyyMM、yyyyMMdd 格式的字符串转换为日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDate(String str) {
		Date date = null;
		if (!StringUtils.isEmpty(str)) {
			try {
				if (str.length() == 12) {
					date = DateUtils.convertStringToDate("yyyyMMddHHmm",
							str.substring(0, 12));
				} else if (str.length() == 8) {
					date = DateUtils.convertStringToDate("yyyyMMdd",
							str.substring(0, 8));
				} else if (str.length() == 6) {
					date = DateUtils.convertStringToDate("yyyyMM",
							str.substring(0, 6));
				} else if (str.length() == 4) {
					date = DateUtils.convertStringToDate("yyyy",
							str.substring(0, 4));
				}
			} catch (ParseException e) {
				return date;
			}
		}
		return date;
	}

	/**
	 * 将 yyyy.、yyyy.MM、yyyy.MM.dd 格式的字符串转换为日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDotDate(String str) {
		Date date = null;
		if (!StringUtils.isEmpty(str)) {
			try {
				if (str.length() >= 10) {
					date = DateUtils.convertStringToDate("yyyy.MM.dd",
							str.substring(0, 10));
				} else if (str.length() == 7) {
					date = DateUtils.convertStringToDate("yyyy.MM",
							str.substring(0, 7));
				} else if (str.length() == 4) {
					date = DateUtils.convertStringToDate("yyyy",
							str.substring(0, 4));
				}
			} catch (ParseException e) {
				return date;
			}
		}
		return date;
	}

	/**
	 * 将 yyyy、yyyy-MM、yyyy-MM-dd 格式的字符串转换为日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date convertStringToStrigulaDate(String str) {
		Date date = null;
		if (!StringUtils.isEmpty(str)) {
			try {
				if (str.length() >= 10) {
					date = DateUtils.convertStringToDate("yyyy-MM-dd",
							str.substring(0, 10));
				} else if (str.length() == 7) {
					date = DateUtils.convertStringToDate("yyyy-MM",
							str.substring(0, 7));
				} else if (str.length() == 4) {
					date = DateUtils.convertStringToDate("yyyy",
							str.substring(0, 4));
				}
			} catch (ParseException e) {
				return date;
			}
		}
		return date;
	}

	/**
	 * 将 yyyy年、yyyy年MM月、yyyy年MM月dd日 格式的字符串转换为日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date convertStringToMoreDate(String str) {
		Date date = null;
		if (!StringUtils.isEmpty(str)) {
			try {
				if (str.length() > 11) {
					date = DateUtils.convertStringToDatetime(str);
				}
				if (str.length() == 11) {
					date = DateUtils.convertStringToDate("yyyy年MM月dd日", str);
				} else if (str.length() == 8) {
					date = DateUtils.convertStringToDate(monthPattern, str);
				} else if (str.length() == 5) {
					date = DateUtils.convertStringToDate(yearPattern, str);
				}
			} catch (ParseException e) {
				return date;
			}
		}
		return date;
	}


	/**
	 * date:2010.03.01 author:jw 日期加天数返回日期
	 */
	public static Date addDate(Date d, long day) throws ParseException {
		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);

	}

	/**
	 * 获得月份加减后的字符串
	 * 
	 * @param date
	 * @param num
	 *            (包括+,-)
	 * @param pattern
	 * @return
	 */
	public static String getMonthSubString(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		SimpleDateFormat df = new SimpleDateFormat(monthPattern);
		return df.format(cal.getTime());
	}

	/**
	 * 获得日期加减
	 * 
	 * @param date
	 * @param num
	 *            (包括+,-)
	 * @param pattern
	 * @return
	 */
	public static Date getDayUpOrDown(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}


	/**
	 * 获得同年的两个月份之间的月份数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthSubNum(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return (c2.get(1) - c1.get(1)) * 12 + (c2.get(2) - c1.get(2));
	}
	/**
	 * 获得获得时间的天数差值
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	 public static int betweenDays(Calendar beginDate, Calendar endDate) {
		      return (int)((beginDate.getTimeInMillis() - endDate.getTimeInMillis())/(1000*60*60*24));
		    
	 }
	/**
	 * 获得获得时间的天数差值
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int betweenDays(Date beginDate, Date endDate) {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String s1 = sdf.format(beginDate);
				String s2 = sdf.format(endDate);
				c1.setTime(sdf.parse(s1));
				c2.setTime(sdf.parse(s2));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return betweenDays(c1,c2);
	 }
	
	 public static String getYear(Date date) {
		 if(null==date){
			  return null;
		  }
		  Calendar calendar = GregorianCalendar.getInstance(); 
		  calendar.setTimeInMillis(date.getTime()); 
		  int m = calendar.get(Calendar.YEAR);
		  return String.valueOf(m)+"年度";
	    }
	  public static String getMonth(Date date) {
		  if(null==date){
			  return null;
		  }
		  Calendar calendar = GregorianCalendar.getInstance(); 
		  calendar.setTimeInMillis(date.getTime()); 
		  int m = calendar.get(Calendar.MONTH);
		  String[] str = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
		  return str[m];
	    }
	  public static String getQuarter(Date date) {
		  if(null==date){
			  return null;
		  }
		  Calendar calendar = GregorianCalendar.getInstance(); 
		  calendar.setTimeInMillis(date.getTime()); 
		  int m = calendar.get(Calendar.MONTH)+1;
		  int q = m/4;
		  String[] str = {"第一季度","第二季度","第三季度","第四季度"};
		  return str[q];
	  }
	  
	  /** 
	     * 将Date类转换为XMLGregorianCalendar 
	     * @param date 
	     * @return  
	     */  
	    public static XMLGregorianCalendar dateToXmlDate(Date date){  
	            Calendar cal = Calendar.getInstance();  
	            cal.setTime(date);  
	            DatatypeFactory dtf = null;  
	            XMLGregorianCalendar dateType = null;
	             try {  
	                dtf = DatatypeFactory.newInstance(); 
	                dateType = dtf.newXMLGregorianCalendar();  
		            dateType.setYear(cal.get(Calendar.YEAR));  
		            //由于Calendar.MONTH取值范围为0~11,需要加1  
		            dateType.setMonth(cal.get(Calendar.MONTH)+1);  
		            dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));  
		            dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));  
		            dateType.setMinute(cal.get(Calendar.MINUTE));  
		            dateType.setSecond(cal.get(Calendar.SECOND));  
	            } catch (DatatypeConfigurationException e) {  
	            }  
	            return dateType;  
	        }   
	    /** 
	     * 将XMLGregorianCalendar转换为Date 
	     * @param cal 
	     * @return  
	     */  
	    public static Date xmlDate2Date(XMLGregorianCalendar cal){  
	        return cal.toGregorianCalendar().getTime();  
	    }  

	/**
	 * 之排斥周末不排斥节假日
	 * @param startDate 开始时间
	 * @param workDay   工作日
	 * @return 结束时间
	 */
	public static Date getWorkDay(Date startDate, int workDay) {
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(startDate);
	    for (int i = 1; i <= workDay; i++) {
	    	c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 1);
	        if (c1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
	        	workDay = workDay + 1;
	            c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 1);
	            continue;
	        }
	    }
	    return c1.getTime();
	}
	
	/**
	 * 验证非空值
	 * @param startDate 需验证值
	 * @param planDate 默认值
	 * @return
	 */
	public static Date checkedNull(Date startDate,Date planDate){
		if(startDate==null){
			return planDate;
		}
		return startDate;
	}
	
	/**
	 * 验证是否还没有修改密码或者距离上次修改密码的时间超过了90天
	 * @param date
	 * @return
	 */
	public static  Boolean  checkChangePwdTime(Date date){
		if(date==null||betweenDays(new Date(), date)<=day){
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 添加一天
	 * @param date
	 * @return
	 */
	public static String addOneDay(String date){
		if(StringUtils.isNotBlank(date)){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				long time = 24 * 60 * 60 * 1000;
				Date d = sdf.parse(date);
				date = sdf.format(new Date(d.getTime()+time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			}
		return date;
	}
	/**
	 * 添加num天
	 * @param date
	 * @return
	 */
	public static long addNumDay(Date date, int num) {
		long time = num*24 * 60 * 60 * 1000;
		return date.getTime()+time;
	} 
	/**
	 * 添加num天
	 * @param date
	 * @return
	 */
	public static long addNumDay(Date date, Double num) {
		long time = (long) (num*24 * 60 * 60 * 1000);
		return date.getTime()+time;
	} 
	/**
	 * 减去num天
	 * @param date
	 * @return
	 */
	public static long detractNumDay(Date date, Double num) {
		long time = (long) (num*24 * 60 * 60 * 1000);
		return date.getTime()-time;
	}
	public static Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern);
		return sdf.format(date);
	}
	
	
	/**
	 * 转换成yyyy年MM月dd日HH时mm分ss秒,字符串
	 * 
	 */
	public static String convertDateToTimeString(Date sDate) {
		return convertDateToString(timePattern, sDate);

	}
	
	/**
	* 根据 年、月 获取对应的月份 的 天数
	*/
	public static int getDaysByYearMonth(int year, int month) {
		Calendar  a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxdate = a.get(Calendar.DATE);
		return maxdate;
	}
	
	/**
	 * 
	 * getDateByAdd:
	 * 适用:
	 * @param date
	 * @param num
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static Date getDateByAdd(Date date,int num){
		Calendar  a = Calendar.getInstance();
		a.setTime(date);
		a.add(GregorianCalendar.DATE, num);
		return a.getTime();
	}
	
	/**
	 * 适用：根据案件时限时长获取新的日期
	 * @param args
	 * @return Date
	 */
	public static Date getDisposeDate(double disposeTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getCurrentDate());
		cal.add(Calendar.HOUR, new Double(disposeTime).intValue());
		Date date = cal.getTime();
		return date;
	}
	
    /**
     * 当天的开始时间
     * @return
     */
    public static Date startOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date=calendar.getTime();
        return date;
    }
    
    /**
     * 当天的结束时间
     * @return
     */
    public static Date endOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date=calendar.getTime();
        return date;
    }
    
    /**
     * 
     * startOfMonth:
     * 适用:获取某年某月某日开始时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfMonth(int year,int month,int day) throws ParseException {
    	Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day, 00, 00 , 00);
		//按格式输出
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dayTimePattern);
		String time = convertDatetimeToString(d);
    	return sdf.parse(time);
    }
    
    /**
     * 
     * startOfMonth:
     * 适用:获取某年某月开始时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfMonth(int year,int month) throws ParseException{
    	Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 01, 00, 00 , 00);
		//按格式输出
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dayTimePattern);
		String time = convertDatetimeToString(d);
    	return sdf.parse(time);
    }
    
    /**
     * 
     * startOfMonth:
     * 适用:获取某年某月开始时间
     * @param date
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("static-access")
	public static Date startOfMonth(Date date) throws ParseException{
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH), 01, 00, 00 , 00);
		//按格式输出
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dayTimePattern);
		String time = convertDatetimeToString(d);
    	return sdf.parse(time);
    }
    
    /**
     * 
     * startOfMonth:
     * 适用:获取上个月开始时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfLastMonth() throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH);
    	return startOfMonth(year,month);
    }
    
    /**
     * 
     * startOfMonth:
     * 适用:获取上个月开始时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfLastMonth(Date date) throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH);
    	return startOfMonth(year,month);
    }
    
    /**
     * 
     * startOfLastMonth:
     * 适用:获取指定年月上个月开始时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfLastMonth(int year,int month) throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	cal.set(year, month-1, 1);
    	int y = cal.get(Calendar.YEAR);
    	int m = cal.get(Calendar.MONTH);
    	return startOfMonth(y,m);
    }
    
    /**
     * 
     * endOfLastMonth:
     * 适用:获取指定年月上个月结束时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date endOfLastMonth(int year,int month) throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	cal.set(year, month-1, 1);
    	int y = cal.get(Calendar.YEAR);
    	int m = cal.get(Calendar.MONTH);
    	return endOfMonth(y,m);
    }
    
    /**
     * 
     * startOfLastTwoMonth:
     * 适用:获取上两月开始时间
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfLastTwoMonth() throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH-1);
    	return startOfMonth(year,month);
    }

    
    /**
     * 
     * startOfMonth:
     * 适用:获取当月开始时间
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfMonth() throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH )+1;
    	return startOfMonth(year,month);
    }
    
    /**
     * 
     * endOfMonth:
     * 适用:获取某年某月最后时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("static-access")
    public static Date endOfMonth(int year,int month) throws ParseException{
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, year);
    	calendar.set(Calendar.MONTH, month - 1);
		int monthMaxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		calendar.set(year, month-1, monthMaxDay , 23, 59 , 59);
		//按格式输出
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dayTimePattern);
		String time = convertDatetimeToString(d);
    	return sdf.parse(time);
    }
    
    /**
     * 
     * endOfMonth:
     * 适用:获取某年某月最后时间
     * @param date
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("static-access")
	public static Date endOfMonth(Date date) throws ParseException{
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
		int monthMaxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), monthMaxDay , 23, 59 , 59);
		//按格式输出
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dayTimePattern);
		String time = convertDatetimeToString(d);
    	return sdf.parse(time);
    }
    
    /**
     * 
     * endOfLastMonth:
     * 适用:获取上一个月最后时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date endOfLastMonth() throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH);
    	return endOfMonth(year,month);
    }
    
    /**
     * 
     * endOfLastMonth:
     * 适用:获取上一个月最后时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date endOfLastMonth(Date date) throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH);
    	return endOfMonth(year,month);
    }
    
    /**
     * 
     * endOfMonth:
     * 适用:获取当月结束时间
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date endOfMonth() throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH )+1;
    	return endOfMonth(year,month);
    }
    
    
    
    /**
     * 
     * endOfDay:
     * 适用:获取某日最后时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date endOfDay(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) , 23, 59 , 59);
		//按格式输出
		Date d = calendar.getTime();
		return d;
    }
    
    /**
     * 
     * startOfDay:
     * 适用:获取某日开始时间
     * @param year
     * @param month
     * @return
     * @throws ParseException 
     * @exception 
     * @since  1.0.0
     */
    public static Date startOfDay(Date date){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) , 00, 00 , 00);
		//按格式输出
		Date d = calendar.getTime();
		return d;
    }
    
    /**
     * 
     * countTimeDifference:
     * 适用:计算两个日期之间的小时差
     * @return 
     * @exception 
     * @since  1.0.0
     */
    public static String countTimeDifference(Date startDate,Date endDate){
    	String d = "";
    	if (null == startDate || null == endDate) {
    		return d;
    	}
    	long time = endDate.getTime() - startDate.getTime();
    	DecimalFormat df=new DecimalFormat("0.00");
    	d = df.format((float)time/(1000*60*60));
    	return d;
    }
	
	/**
     * 
     * getdisPoseDate:
     * 适用:根据案件起始计算时间和时限长获取案件处理结束时间
     * @param beginDate
     * @param times
     * @return 
     * @exception 
     * @since  1.0.0
     */
	public static Date getdisPoseDate(Date beginDate, double times) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.add(Calendar.HOUR_OF_DAY, (new Double(times)).intValue());
		return calendar.getTime();
	}
	
	/**
	 * 
	 * getYesterday:
	 * 适用:获取指定日期的昨天
	 * @param date
	 * @return
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("static-access")
	public static Date getYesterday(Date date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern);
		String time = convertDatetimeToString(d);
    	return sdf.parse(time);
	}
	
	/**
	 * 
	 * getTomorrow:
	 * 适用:获取指定日期的明天
	 * @param date
	 * @return
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("static-access")
	public static Date getTomorrow(Date date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE,+1);
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern);
		String time = convertDatetimeToString(d);
    	return sdf.parse(time);
	}
	
	/**
	 * 
	 * formatDate:
	 * 适用:将普通时间格式化成日历时间
	 * @param date
	 * @return
	 * @throws DatatypeConfigurationException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static XMLGregorianCalendar formatDate(Date date) throws DatatypeConfigurationException{
		if (null == date) {
			return null;
		}
		GregorianCalendar gcal =new GregorianCalendar();
		gcal.setTime(date);
		XMLGregorianCalendar xmlDate= DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		return xmlDate;
	}
	
	/**
	 * getLastMonthDate
	 * 适用：获取上个月的起始日期和结束日期 如2017-12-01 00:00:00 =< 查询日期 < 2018-01-01 00:00:00
	 */
	public static Date[] getLastMonthDate() {
		String[] sts = getCurrentMonth(null, null);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date [] dates = new Date[2];
		Date beginDate;
		Date endDate;
		try {
			beginDate = format.parse(sts[0]);
			dates[0] = beginDate;
			endDate = format.parse(sts[1]);
			dates[1] = endDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}
	
	/**
	 * getCurrentMonth:
	 * 适用:start =< 查询日期 <end形式  获取当前月开始到结束时间段
	 * @param calendar
	 * @return 
	 * @exception 
	 * @since  1.0.0
	*/
	public static String[] getCurrentMonth(Integer year, Integer month) {
		String[] sts = new String[2];
		String start = year+"-"+month+"-01 00:00:00";
		String end = year+"-"+(month+1)+"-01 00:00:00";
		if(month == 12) {
			end = (year+1)+"-01-01 00:00:00";
		}
		sts[0]=start;
		sts[1]=end;
		return sts;
	}
	
	/**
	 * getCurrentMonth:
	 * 适用:start =< 查询日期 <end形式  获取当前月开始到结束时间段
	 * @param calendar
	 * @return 
	 * @exception 
	 * @since  1.0.0
	*/
	@Deprecated
	public static String[] getCurrentMonth(Integer year, Integer month, String type) {
		String[] sts = new String[2];
		String start = year+"-"+month+"-01 00:00:00";
		if(!"1".equals(type)) {
			start = year+"-"+month+"-16 00:00:00";
		}
		String end = year+"-"+(month+1)+"-01 00:00:00";
		if("1".equals(type)) {
			end  = year+"-"+(month)+"-16 00:00:00";
		}
		if(month == 12) {
			end = (year+1)+"-01-01 00:00:00";
			if("1".equals(type)) {
				end  = (year)+"-12-16 00:00:00";
			}
		}
		sts[0]=start;
		sts[1]=end;
		return sts;
	}
	
	/**
	 * getMonthSE:
	 * 适用:start =< 查询日期 < end形式  获取当前月开始到结束时间段
	 * @param calendar
	 * @return 
	 * @exception 
	 * @since  1.0.0
	*/
	public static Date[] getMonthSE(Integer year, Integer month) {
		Date[] sts = new Date[2];
		String start = year+"-"+month+"-01 00:00:00";
		String end = year+"-"+(month+1)+"-01 00:00:00";
		if(month == 12) {
			end = (year+1)+"-01-01 00:00:00";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sts[0]=sdf.parse(start);
			sts[1]=sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sts;
	}

	//获取今年是哪一年
	public static Integer getNowYear(Date date ) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}
	
	//获取本月是哪一月
	public static int getNowMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(2) + 1;
	}
	
	/**
	 * 
	 * betweenDate:
	 * 适用:判断时间是否在某时间段内
	 * @param nowDate
	 * @param beginDate
	 * @param endDate
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean betweenDate(Date nowDate, Date beginDate, Date endDate){
        Calendar date = Calendar.getInstance();
        date.setTime(nowDate);
        
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginDate);
        
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
		
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
	}
	
	/**
	 * 
	 * formatDate:
	 * 适用:格式化时间
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static Date convertDate(Date date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(date);
		return sdf.parse(s);
	}
	
	/**
	 * 
	 * getLastMonthDateNum:
	 * 适用:获得上个月天数
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static int getLastMonthDateNum(){
	  Calendar  cal = Calendar.getInstance();   
	  cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-1);
	  int maxDate = cal.getActualMaximum(Calendar.DATE);
	  return maxDate;
	}
	
	/**
	 * 
	 * @describe 获得当前月的天数
	 * @see 
	 * @return 
	 * @exception
	 */
	public static int getCurrentMonthDays(){
		Calendar  cal = Calendar.getInstance();   
		cal.set(Calendar.MONTH,cal.get(Calendar.MONTH));
		int maxDate = cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 
	 * getMonthDateNum:
	 * 适用:
	 * @param date
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static int getMonthDateNum(Date date){
		  Calendar  cal = Calendar.getInstance(); 
		  cal.setTime(date);
		  int maxDate = cal.getActualMaximum(Calendar.DATE);
		  return maxDate;
	}
	
	/**
	 * 
	 * makeDateMap:
	 * 适用:
	 * @param date
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<String> makeDateList(Date sDate,Date eDate){
		List<String> list = new ArrayList<String>();
		list.add(convertDateToString("MM-dd",sDate));
		int num = betweenDays(eDate, sDate);
		for (int i=1; i<=num; i++) {
			Date d = getDateByAdd(sDate,i);
			if (betweenDays(eDate, d) > 0) {
				list.add(convertDateToString("MM-dd",d));
			}
		}
		Collections.reverse(list);
		return list;
	}
	
	/**
	 * 
	 * getDatePoor:
	 * 适用:查询日期差
	 * @param endDate
	 * @param nowDate
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static int getDatePoor(Date endDate, Date nowDate) {
		long end = endDate.getTime();
		long now = nowDate.getTime();
		int num = (int) ((now - end)/(1000 * 24 * 60 * 60));
		return num;
	}
	
	public static void main(String[] args) {
		
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar c1 =Calendar.getInstance();
//		Calendar c2 =Calendar.getInstance();
//		c1.set(Calendar.YEAR, 2019);
//		c1.set(Calendar.MONTH,11);//从0开始，0表是1月，1表示2月依次类推
//		c1.set(Calendar.DAY_OF_MONTH, 11);
//		c1.set(Calendar.HOUR_OF_DAY, 0);
//		c1.set(Calendar.MINUTE, 0);
//		c1.set(Calendar.SECOND, 0);
//		
//		c2.set(Calendar.YEAR, 2019);
//		c2.set(Calendar.MONTH,11);//从0开始，0表是1月，1表示2月依次类推
//		c2.set(Calendar.DAY_OF_MONTH,14);
//		c2.set(Calendar.HOUR_OF_DAY, 0);
//		c2.set(Calendar.MINUTE, 0);
//		c2.set(Calendar.SECOND, 0);
//		System.out.println(getCurrentMonth());
		
	}
	
	/**
	 * 当天凌晨时间戳
	 * @return
	 */
	public static Date getStartToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date start = calendar.getTime();
		return start;
	}
	/**
	 * 第二天凌晨时间戳
	 * @return
	 */
	public static Date getEndToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date start = calendar.getTime();
		return start;
	}
	
	/**
	 * 当天凌晨时间戳
	 * @return
	 */
	public static Date getStartToday(String originDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(originDate));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date start = calendar.getTime();
		return start;
	}
	
	/**
	 * 第二天凌晨时间戳
	 * @return
	 */
	public static Date getEndToday(String originDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(originDate));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date start = calendar.getTime();
		return start;
	}
}
