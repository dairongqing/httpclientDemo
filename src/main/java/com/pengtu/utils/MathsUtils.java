/**
 * <p>Copyright @ 2009 深圳市金绎科技发展有限公司</p>
 * <p>All right reserved. </p>
 * <p>项目名称				： 佛山市地方公路信息资源整合</p>
 * <p>JDK使用版本号		： jdk1.5 </P>
 * <p>描述				： </p>
 * @版本					： 1.0.0 
 * @author				： 谢庚才
 *
 * <p>修改历史 </p>
 *
 * <p>修改时间            修改人员    修改内容 </p>
 *  <p>2009-5-15			谢庚才	  新建    </p>
 */
package com.pengtu.utils;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * 该工具类的计算方法皆忽略为 null 参数，即
 * sum(1,null,1)=2,sub(10,null,5)=2,mul(5,null,3)=15,div(10,null,5)=2
 * 
 * @author 谢庚才
 * 
 */
public final class MathsUtils {
	private MathsUtils() {
	}



	/**
	 * 以指定的舍入模式格式化双精度浮点型小数
	 * 
	 * @param d
	 *            需格式化小数
	 * @param precision
	 *            保留小数位数
	 * @param roundingMode
	 *            舍入模式
	 * @return
	 */
	public static Double formatDouble(Double d, int precision, int roundingMode) {
		if (d == null) {
			return null;
		}
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(precision, roundingMode).doubleValue();
	}

	/**
	 * 以指定的舍入模式格式化双精度 返回整型
	 * 
	 * @param d
	 *            需格式化小数
	 * @param precision
	 *            保留小数位数
	 * @param roundingMode
	 *            舍入模式
	 * @return
	 */
	public static Integer formatInteger(Double d, int precision, int roundingMode) {
		if (d == null) {
			return null;
		}
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(precision, roundingMode).intValue();
	}

	/**
	 * 以 四舍五入的舍入模式格式化双精度浮点型小数
	 * 
	 * @param d
	 *            需格式化小数
	 * @param precision
	 *            保留小数位数
	 * @return
	 */
	public static Double formatDouble(Double d, int precision) {
		return formatDouble(d, precision, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 以指定的舍入模式格式化单精度浮点型小数
	 * 
	 * @param d
	 *            需格式化小数
	 * @param precision
	 *            保留小数位数
	 * @param roundingMode
	 *            舍入模式
	 * @return
	 */
	public static Float formatFloat(Float d, int precision, int roundingMode) {
		if (d == null) {
			return null;
		}
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(precision, roundingMode).floatValue();
	}

	/**
	 * 以 四舍五入的舍入模式格式化双精度浮点型小数
	 * 
	 * @param d
	 *            需格式化小数
	 * @param precision
	 *            保留小数位数
	 * @return
	 */
	public static Float formatFloat(Float d, int precision) {
		return formatFloat(d, precision, BigDecimal.ROUND_HALF_EVEN);
	}

	public static Number sum(Number... values) {
		if (values instanceof Double[]) {
			Double[] dvalues = (Double[]) values;
			Double result = new Double(0);
			for (Double value : dvalues) {
				if (value != null) {
					result += value;
				}
			}
			return result;

		} else if (values instanceof Integer[]) {
			Integer[] ivalues = (Integer[]) values;
			Integer result = new Integer(0);
			for (Integer value : ivalues) {
				if (value != null) {
					result += value;
				}
			}
			return result;
		} else if (values instanceof Float[]) {
			Float[] fvalues = (Float[]) values;
			Float result = new Float(0);
			for (Float value : fvalues) {
				if (value != null) {
					result += value;
				}
			}
			return result;
		} else if (values instanceof Long[]) {
			Long[] lvalues = (Long[]) values;
			Long result = new Long(0);
			for (Long value : lvalues) {
				if (value != null) {
					result += value;
				}
			}
			return result;
		}
		return 0;

	}

	/**
	 * 求和运算
	 * 
	 * @param ds
	 *            参数列表
	 * @return
	 */
	public static Double sum(Double... values) {

		Double result = new Double(0);
		for (Double value : values) {
			if (value != null) {
				result += value;
			}
		}
		return result;
	}

	public static Integer sum(Integer... values) {
		Integer result = new Integer(0);
		for (Integer value : values) {
			if (value != null) {
				result += value;
			}
		}
		return result;
	}

	public static Float sum(Float... values) {
		Float result = new Float(0);
		for (Float value : values) {
			if (value != null) {
				result += value;
			}
		}
		return result;
	}

	public static Long sum(Long... values) {
		Long result = new Long(0);
		for (Long value : values) {
			if (value != null) {
				result += value;
			}
		}
		return result;
	}

	/**
	 * 减法运算，取第一个参数为被减数
	 * 
	 * @param ds
	 * @return
	 */
	public static Double sub(Double... values) {
		Double result = 0d;
		if (values.length > 0) {
			if (values[0] != null) {
				result = values[0];
			}
		}
		for (int i = 1; i < values.length; i++) {
			if (values[i] != null) {
				result -= values[i];
			}
		}
		return result;
	}

	public static Float sub(Float... values) {
		Float result = 0f;
		if (values.length > 0) {
			if (values[0] != null) {
				result = values[0];
			}
		}
		for (int i = 1; i < values.length; i++) {
			if (values[i] != null) {
				result -= values[i];
			}
		}
		return result;
	}

	public static Integer sub(Integer... values) {
		Integer result = 0;
		if (values.length > 0) {
			if (values[0] != null) {
				result = values[0];
			}
		}
		for (int i = 1; i < values.length; i++) {
			if (values[i] != null) {
				result -= values[i];
			}
		}
		return result;
	}

	public static Long sub(Long... values) {
		Long result = 0L;
		if (values.length > 0) {
			if (values[0] != null) {
				result = values[0];
			}
		}
		for (int i = 1; i < values.length; i++) {
			if (values[i] != null) {
				result -= values[i];
			}
		}
		return result;
	}

	/**
	 * 乘法运算，如果第一个参数为“0”，不管后面的参数怎样结果都为“0”
	 * 
	 * @param ds
	 * @return
	 */
	public static Double mul(Number... numbers) {
		Double result = 0d;
		if (numbers.length > 0 && numbers[0] != null) {
			result = numbers[0].doubleValue();
			for (int i = 1; i < numbers.length; i++) {
				if (numbers[i] != null) {
					if (numbers[i].doubleValue() == 0) {
						result = 0d;
					} else {
						result *= numbers[i].doubleValue();
					}
				}
			}
		}
		return result;
	}

	public static Double mul(Double... ds) {
		Double result = 0d;
		if (ds.length > 0 && ds[0] != null) {
			result = ds[0];
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i] == 0) {
						result = 0d;
					} else {
						result *= ds[i];
					}
				}
			}
		}

		return result;
	}

	public static Float mul(Float... ds) {
		Float result = 0f;
		if (ds.length > 0 && ds[0] != null) {
			result = ds[0];
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i] == 0) {
						result = 0f;
					} else {
						result *= ds[i];
					}
				}
			}
		}
		return result;
	}

	public static Integer mul(Integer... ds) {
		Integer result = 0;
		if (ds.length > 0 && ds[0] != null) {
			result = ds[0];
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i] == 0) {
						result = 0;
					} else {
						result *= ds[i];
					}
				}
			}
		}

		return result;
	}

	public static Long mul(Long... ds) {
		Long result = 0L;
		if (ds.length > 0 && ds[0] != null) {
			result = ds[0];
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i] == 0) {
						result = 0L;
					} else {
						result *= ds[i];
					}
				}
			}
		}

		return result;
	}

	/**
	 * 除法运算，如果第一个参数为“0”，不管后面的参数怎样结果都为“0”； 若第一个参数不为“0”，后面的参数也不能为零，否则抛出除“0”异常
	 * 
	 * @param ds
	 * @return
	 */
	public static Integer div(Integer... ds) {
		Integer result = 0;
		if (ds.length > 0 && ds[0] != null) {
			result = ds[0];
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i] != 0) {
						result /= ds[i];
					} else {
						throw new ArithmeticException("除数不能为“0”");
					}
				}
			}
		}
		return result;
	}

	/**
	 * 除法运算，如果第一个参数为“0”，不管后面的参数怎样结果都为“0”； 若第一个参数不为“0”，后面的参数也不能为零，否则抛出除“0”异常
	 * 
	 * @param ds
	 * @return
	 */
	public static Double div(Double... ds) {
		Double result = 0d;
		if (ds.length > 0 && ds[0] != null) {
			result = ds[0];
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i] != 0) {
						result /= ds[i];
					} else {
						throw new ArithmeticException("除数不能为“0”");
					}
				}
			}
		}
		return result;
	}

	/**
	 * 除法运算，如果第一个参数为“0”，不管后面的参数怎样结果都为“0”； 若第一个参数不为“0”，后面的参数也不能为零，否则抛出除“0”异常
	 * 
	 * @param ds
	 * @param precision
	 *            保留几位 使用整型写法
	 * @return 结果保留 precision 位小数 默认使用四舍五入的方式处理结果
	 */
	public static Double div(String precision, Double... ds) {
		Double result = 0d;
		result = div(ds);
		return formatDouble(result, Integer.valueOf(precision));
	}

	public static Float div(Float... ds) {
		Float result = 0f;
		if (ds.length > 0 && ds[0] != null) {
			result = ds[0];
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i] != 0) {
						result /= ds[i];
					} else {
						throw new ArithmeticException("除数不能为“0”");
					}
				}
			}
		}
		return result;
	}

	/**
	 * 除法运算，如果第一个参数为“0”，不管后面的参数怎样结果都为“0”； 若第一个参数不为“0”，后面的参数也不能为零，否则抛出除“0”异常
	 * 
	 * @param ds
	 * @param precision
	 *            保留几位 使用整型写法
	 * @return 结果保留 precision 位小数 默认使用四舍五入的方式处理结果
	 */
	public static Float div(String precision, Float... ds) {
		Float result = 0f;
		result = div(ds);
		return formatFloat(result, Integer.valueOf(precision));
	}

	/**
	 * 除法运算，如果第一个参数为“0”，不管后面的参数怎样结果都为“0”； 若第一个参数不为“0”，后面的参数也不能为零，否则抛出除“0”异常
	 * 
	 * @param ds
	 * @return
	 */
	public static Double div(Number... ds) {
		Double result = 0d;
		if (ds.length > 0 && ds[0] != null) {
			result = new Double(ds[0].doubleValue());
			for (int i = 1; i < ds.length; i++) {
				if (ds[i] != null) {
					if (ds[i].doubleValue() != 0) {
						result /= ds[i].doubleValue();
					} else {
						throw new ArithmeticException("除数不能为“0”");
					}
				}
			}
		}
		return result;
	}

	/**
	 * 除法运算，如果第一个参数为“0”，不管后面的参数怎样结果都为“0”； 若第一个参数不为“0”，后面的参数也不能为零，否则抛出除“0”异常
	 * 
	 * @param ds
	 * @param precision
	 *            保留几位 使用整型写法
	 * @return 结果保留 precision 位小数 默认使用四舍五入的方式处理结果
	 */
	public static Double div(String precision, Number... ds) {
		Double result = 0d;
		result = div(ds);
		return formatDouble(result, Integer.valueOf(precision));
	}

	/*
	 * $Id: MathsUtils.java,v 1.2 2016/03/16 03:16:44 zwm Exp $ Copyright (c)
	 * 2008-2010 ... Co. Ltd. All Rights Reserved Changelog: Li Guoliang - Jan
	 * 27, 2010: Initial version
	 */

	/**
	 * 为给定的分数列表计算对应的标准分, 返回对应的标准分列表.
	 * 
	 * @param rawScores
	 *            原始分列表
	 * @return [平均分, 方差, 原始分对应的标准分列表(顺序同原始分列表)]
	 */
	public static Object[] calculateStandardScore(Double... values) {
		List<Double> listStandardScore = new ArrayList<Double>();

		double scoreMean = calculateScoreMean(values); // 1. 获得平均分
		// 2. 计算标准差
		double standardDeviation = calculateSTD(scoreMean, values);
		for (int i = 0; i < values.length; i++) {
			double standardScore = (values[i] - scoreMean) / standardDeviation;
			listStandardScore.add(i, standardScore); // 将标准分按顺序放入列表中.
		}

		Object[] consolidateValues = new Object[3];
		consolidateValues[0] = scoreMean;
		consolidateValues[1] = standardDeviation;
		consolidateValues[2] = listStandardScore;

		return consolidateValues;
	}

	/**
	 * 获得平均分.
	 * 
	 * @param rawScores
	 * @return
	 */
	public static Double calculateScoreMean(Double... values) {
		double scoreAll = 0.0;
		int length =0;
		for (Double score : values) {
			if (score != null) {
				length ++; 
				scoreAll += score;
			}
		}
		if(length == 0) {
			return 0d;
		}
		return scoreAll /length;
	}

	/**
	 * 计算标准差
	 * 
	 * @param rawScores
	 * @param scoreMean
	 * @return
	 */
	public static double calculateSTD(double scoreMean, Double... values) {
		double allSquare = 0.0;
		int length =0;
		for (Double rawScore : values) {
			if (rawScore != null) {
				length ++; 
				allSquare += (rawScore - scoreMean) * (rawScore - scoreMean);
			}
		}
		// (xi - x(平均分)的平方 的和计算完毕
		double denominator = (length- 1);
		if(denominator == 0){
			return 0d;
		}
		return Math.sqrt(allSquare / denominator);
	}

	/**
	 * 计算矩形对角线长度
	 * 
	 * @param rawScores
	 * @param scoreMean
	 * @return
	 */
	public static Double calculateDiagonal(Double length, Double width) {
		return Math.sqrt(sum(mul(length, length), mul(width, width)));
	}
	
	
	public static boolean isBig0(Double d){
		if(d!=null && d>0){
			return true;
		}else{
			return false;
		}
		 
	}
	
	public static Long getRandom(){
		Long value = new SecureRandom().nextLong();
		return value<=0?-value:value;
	}
	
	/**
	 * 
	 * diseaseRemind:
	 * 适用: 事件修复提醒
	 * @param type
	 * @param dealDate
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unused")
	public static String diseaseRemind(String type,Date foundTime ,String isDeal,Integer delayHour,Date disposeTime){
		/**
		 * 钢筋混凝土/砼类	        72小时	
		 * 砌体、铺装类	            48小时         
		 * 钢结构、安装类                                  48小时          
		 * 更换、沥青路面类                              24小时          
		 * 桥涵、隧道等结构类                           按批准时限    
		 * 保洁类                                                   24小时          
		 */
		if(null == delayHour){
			delayHour = 0;
		}
		
		if(null == foundTime){
			return "发现时间有误!";
		}
		
		if(null == disposeTime) {
			return "处理时限有误!";
		}
		
		if(StringUtils.isEmpty(type)){
			return "损坏类型有误!";
		}
		long delay = delayHour*60*60*1000;
		if("0".equals(isDeal)){
			long n = System.currentTimeMillis(); //当前时间
			long f = foundTime.getTime();
			double t1 = 60*60*1000; // 1小时
			if(null != disposeTime){
				long dis= disposeTime.getTime();
				// 当前时间 
				if(n-(delay+dis) >0){
					return "超出时限";
				}else{
					return (formatInteger((dis - n)/t1,0,BigDecimal.ROUND_UP)+delayHour)+"";
				}
			} else {
				Double t72 = (72.0*t1 +delayHour*t1);//72小时内处理
				Double t48 = (48.0*t1 +delayHour*t1);//48小时内处理
				Double t24 = (24.0*t1 +delayHour*t1);//24小时内处理
			
			if("11".equals(type) || "12".equals(type)){
				if(n-f>t72){
					return "超出时限";
				}else{
					return formatInteger(((t72 - (n-f))/t1),0,BigDecimal.ROUND_UP)+"";
				}
			}else if("13".equals(type) || "14".equals(type)){
				if(n-f>t48){
					return "超出时限";
				}else{
					return formatInteger(((t48 - (n-f))/t1),0,BigDecimal.ROUND_UP)+"";
				}
			}else if("15".equals(type) || "16".equals(type)){
				if(n-f>t48){
					return "超出时限";
				}else{
					return formatInteger(((t48 - (n-f))/t1),0,BigDecimal.ROUND_UP)+"";
				}
			}else if("17".equals(type) || "18".equals(type)){
				if(n-f>t24){
					return "超出时限";
				}else{
					return formatInteger(((t24 - (n-f))/t1),0,BigDecimal.ROUND_UP)+"";
				}
			}else if("19".equals(type)){
				return "按批准时限";
			}else if("99".equals(type)){
				if(n-f>t72){
					return "超出时限";
				}else{
					return formatInteger(((t72 - (n-f))/t1),0,BigDecimal.ROUND_UP)+"";
				}
			}else if("20".equals(type)){
				if(n-f>t24){
					return "超出时限";
				}else{
					return formatInteger(((t24 - (n-f))/t1),0,BigDecimal.ROUND_UP)+"";
				}
			}else{
				return "按批准时限";
			}
			}
		}else if("02".equals(isDeal)){
			return "申报中";
		}else if("03".equals(isDeal)){
			return "修复中";
		}else if("04".equals(isDeal)){
			return "退案未通过";
		}else if("44".equals(isDeal)){
			return "退案中";
		}else if("45".equals(isDeal)){
			return "已退案";
		}else if("99".equals(isDeal)){
			return "已修复";
		}else if("05".equals(isDeal)){
			return "抽查未通过";
		}else if("98".equals(isDeal)){
			return "已快速结办";
		}else{
			return "状态有误!";
		}
	}
	
	/**
	 * 
	 * countRoadNextInspectTime:
	 * 适用:计算道路下次应巡查时间
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String countRoadNextInspectTime(String techGrade,Date lastInspectTime){
		String t = techGrade;
		try {
			if (null != lastInspectTime && !StringUtils.isBlank(techGrade)) {
				if(t.equals("121") || t.equals("111") || t.equals("212") || t.equals("222")){ 
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 1));
				}else if(t.equals("313") || t.equals("323")){
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 2));
				}else{
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 3));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * countBridgeNextInspectTime:
	 * 适用:计算桥梁下次应巡查时间
	 * @param techGrade
	 * @param lastInspectTime
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String countBridgeNextInspectTime(String techGrade,Date lastInspectTime){
		/**
		 * I等养护桥梁，四、五类桥梁，D、E级人行天桥 每天 
		 * II等养护桥梁，二、三类桥梁，B、C级人行天桥 每三天
		 * III等养护桥梁，一类桥梁，A级人行天桥 每七天
		 */
		try {
			if (null != lastInspectTime && !StringUtils.isBlank(techGrade)) {
				String k = techGrade;
				if("1".equals(k)){
					//I等级养护桥梁
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 1));
				}else if("2".equals(k)){
					//II等级养护桥梁
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 3));
				}else if("3".equals(k)){
					//III等级养护桥梁
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 7));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * countTunnelNextInspectTime:
	 * 适用:计算隧道下次应巡查时间
	 * @param lastInspectTime
	 * @return 
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String countTunnelNextInspectTime(Date lastInspectTime){
		try {
			if (null != lastInspectTime) {
				return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * countSlopeNextInspectTime:
	 * 适用:计算边坡下次应巡查时间
	 * @param lastInspectTime
	 * @param mainLevel
	 * @return
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String countSlopeNextInspectTime(Date lastInspectTime,String mainLevel){
		try {
			if (null != lastInspectTime && !StringUtils.isBlank(mainLevel)) {
				if ("1".equals(mainLevel)) {
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 1));
				} else if("2".equals(mainLevel)){
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 3));
				} else if("3".equals(mainLevel)) {
					return DateUtils.convertDateToString(DateUtils.addDate(lastInspectTime, 7));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * fitTimeliness:
	 * 适用:保养及时性判断
	 * @param type
	 * @param foundTime
	 * @param fitTime
	 * @param disposeTime
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String fitTimeliness(String type,Date foundTime ,Date fitTime,Date disposeTime){
		/**
		 * 钢筋混凝土/砼类	        72小时	
		 * 砌体、铺装类	            48小时         
		 * 钢结构、安装类                                  48小时          
		 * 更换、沥青路面类                              24小时          
		 * 桥涵、隧道等结构类                           按批准时限    
		 * 保洁类                                                   24小时          
		 */
		if(StringUtils.isEmpty(type)){
			return "";
		}
		long foundtime = foundTime.getTime();
		long fittime = fitTime.getTime();
		double t1 = 60*60*1000; // 1小时
		Double t72 = (72.0*t1);//72小时内处理
		Double t48 = (48.0*t1);//48小时内处理
		Double t24 = (24.0*t1);//24小时内处理
		
		long val = fittime - foundtime;
		if("11".equals(type) || "12".equals(type)){
			if(val>t72){
				return "0";
			}else{
				return "1";
			}
		}else if("13".equals(type) || "14".equals(type)){
			if(val>t48){
				return "0";
			}else{
				return "1";
			}
		}else if("15".equals(type) || "16".equals(type)){
			if(val>t48){
				return "0";
			}else{
				return "1";
			}
		}else if("17".equals(type) || "18".equals(type)){
			if(val>t24){
				return "0";
			}else{
				return "1";
			}
		}else if("19".equals(type)){
			//todo 按比准时限未判断是够及时
			return "";
		}else {
			return "";
		}
	}
	
	/**
	 * 
	 * fitDisposeTime:
	 * 适用:计算保养处理时长
	 * @param foundTime
	 * @param fitTime
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static Double fitDisposeTime(Date foundTime ,Date fitTime){
		double t1 = 60*60*1000; // 1小时
		long foundtime = foundTime.getTime();
		long fittime = fitTime.getTime();
		return (fittime - foundtime)/t1;
	}
	
	/**
	 * 
	 * roadInsNum:
	 * 适用:
	 * @param roadGrade 道路等级
	 * @param date 天
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static Double roadInsNum(String roadGrade,int date){
		/**
		 * 121高速公路、111城市快速路、212主干道、222一级公路 每天
		 * 313次干道、323二级公路	每两天
		 * 414支路及以下等级道路、424三级公路	每三天
		 */
		String t = roadGrade;
		double num;
		if(t.equals("121") || t.equals("111") || t.equals("212") || t.equals("222")){ 
			num = date/1;
		}else if(t.equals("313") || t.equals("323")){
			num = date/2;
		}else{
			num = date/3;
		}
		return num;
	}
	
	/**
	 * 
	 * roadFailIns:
	 * 适用:计算道路两个时间之间不合格的巡查记录
	 * @param roadGrade
	 * @param startDate
	 * @param endDate
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String roadFailIns(String roadGrade,Date startDate,Date endDate) throws ParseException{
		/**
		 * 121高速公路、111城市快速路、212主干道、222一级公路 每天
		 * 313次干道、323二级公路	每两天
		 * 414支路及以下等级道路、424三级公路	每三天
		 */
		String t = roadGrade;
		String str="";
		if (StringUtils.isBlank(t)) {
			return str;
		}
		if (null == startDate || null == endDate) {
			return str;
		}
		int num = DateUtils.betweenDays(endDate, startDate);
		if(t.equals("121") || t.equals("111") || t.equals("212") || t.equals("222")){ 
			if (num > 1) {
				str = recursive(startDate,endDate,str,1);
			}
			return str;
		}else if(t.equals("313") || t.equals("323")){
			if (num > 2) {
				str = recursive(startDate,endDate,str,2);
			}
			return str;
		}else{
			if (num > 3) {
				str = recursive(startDate,endDate,str,3);
			}
		}
		return str;
	}
	
	/**
	 * 
	 * bridgeFailIns:
	 * 适用:计算桥梁两个时间之间不合格的巡查记录
	 * @param roadGrade
	 * @param startDate
	 * @param endDate
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String bridgeFailIns(String roadGrade,Date startDate,Date endDate) throws ParseException{
		/**
		 * I等养护桥梁，四、五类桥梁，D、E级人行天桥 每天 
		 * II等养护桥梁，二、三类桥梁，B、C级人行天桥 每三天
		 * III等养护桥梁，一类桥梁，A级人行天桥 每七天
		 */
		String str="";
		int num = DateUtils.betweenDays(endDate, startDate);
		if("1".equals(roadGrade)){
			//I等级养护桥梁
			if (num > 1) {
				str = recursive(startDate,endDate,str,1);
			}
			return str;
		}else if("2".equals(roadGrade)){
			//II等级养护桥梁
			if (num > 3) {
				str = recursive(startDate,endDate,str,3);
			}
			return str;
		}else if("3".equals(roadGrade)){
			//III等级养护桥梁
			if (num > 7) {
				str = recursive(startDate,endDate,str,7);
			}
			return str;
		}
		return str;
	}
	
	/**
	 * 
	 * slopeFailIns:
	 * 适用:计算挡墙两个时间之间不合格的巡查记录
	 * @param roadGrade
	 * @param startDate
	 * @param endDate
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String slopeFailIns(String mainLevel,Date startDate,Date endDate) throws ParseException{
		/**
		 * 1等每天一巡 2等每三天一巡 3等每七天一巡
		 */
		String str="";
		int num = DateUtils.betweenDays(endDate, startDate);
		if("1".equals(mainLevel)){
			if (num > 1) {
				str = recursive(startDate,endDate,str,1);
			}
			return str;
		} else if ("2".equals(mainLevel)) {
			if (num > 3) {
				str = recursive(startDate,endDate,str,3);
			}
			return str;
		} else if ("3".equals(mainLevel)) {
			if (num > 7) {
				str = recursive(startDate,endDate,str,7);
			}
			return str;
		}
			return str;
	}
	/**
	 * roadFailIns:
	 * 适用:
	 * @param mainLevel
	 * @param foundTime
	 * @return
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	*/
	public static long roadFailIns(String roadGrade, Date foundTime) {
		/**
		 * 121高速公路、111城市快速路、212主干道、222一级公路 每天
		 * 313次干道、323二级公路	每两天
		 * 414支路及以下等级道路、424三级公路	每三天
		 */
		String t = roadGrade;
		if (StringUtils.isBlank(t)) {
			return DateUtils.addNumDay(foundTime, 3);
		}
		if(t.equals("121") || t.equals("111") || t.equals("212") || t.equals("222")){ 
			return DateUtils.addNumDay(foundTime, 1);
		} else if(t.equals("313") || t.equals("323")){
			return DateUtils.addNumDay(foundTime, 2);
		} else {
			return DateUtils.addNumDay(foundTime, 3);
		}
	}
	/**
	 * bridgeFailIns:
	 * 适用:
	 * @param mainLevel
	 * @param foundTime
	 * @return
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	*/
	public static long bridgeFailIns(String mainLevel, Date foundTime) throws ParseException{
		/**
		 * I等养护桥梁，四、五类桥梁，D、E级人行天桥 每天 
		 * II等养护桥梁，二、三类桥梁，B、C级人行天桥 每三天
		 * III等养护桥梁，一类桥梁，A级人行天桥 每七天
		 */
		if("1".equals(mainLevel)){
			return DateUtils.addNumDay(foundTime, 1);
		} else if ("2".equals(mainLevel)) {
			return  DateUtils.addNumDay(foundTime, 3);
		} else if ("3".equals(mainLevel)) {
			return  DateUtils.addNumDay(foundTime, 7);
		}
		return DateUtils.addNumDay(foundTime, 7);
	}
	/**
	 * slopeFailIns:
	 * 适用:
	 * @param mainLevel
	 * @param foundTime
	 * @return
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	*/
	public static long slopeFailIns(String mainLevel, Date foundTime) throws ParseException{
		/**
		 * 1等每天一巡 2等每三天一巡 3等每七天一巡
		 */
		if("1".equals(mainLevel)){
			return DateUtils.addNumDay(foundTime, 1);
		} else if ("2".equals(mainLevel)) {
			return  DateUtils.addNumDay(foundTime, 3);
		} else if ("3".equals(mainLevel)) {
			return  DateUtils.addNumDay(foundTime, 7);
		}
		return DateUtils.addNumDay(foundTime, 7);
	}
	
	/**
	 * tunnelFailIns:
	 * 适用:
	 * @param mainLevel
	 * @param foundTime
	 * @return 
	 * @exception 
	 * @since  1.0.0
	*/
	public static long tunnelFailIns(Date foundTime) {
		/**
		 * 隧道每天一巡
		 */
		return DateUtils.addNumDay(foundTime, 1);
	}
	
	/**
	 * 
	 * tunnelFailIns:
	 * 适用:计算隧道两个时间之间不合格的巡查记录
	 * @param roadGrade
	 * @param startDate
	 * @param endDate
	 * @return 
	 * @throws ParseException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String tunnelFailIns(Date startDate,Date endDate) throws ParseException{
		/**
		 * 隧道每天一巡
		 */
		String str="";
		int num = DateUtils.betweenDays(endDate, startDate);
		if (num > 1) {
			str = recursive(startDate,endDate,str,1);
		}
			return str;
	}
	
	/**
	 * 
	 * recursive:
	 * 适用:递归计算出不合格的巡查日期
	 * @param startDate
	 * @param endDate
	 * @param str
	 * @param num
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String recursive(Date startDate,Date endDate,String str,int num){
		int n = DateUtils.betweenDays(endDate,startDate);
		if (n <= num) {
			return str;
		}
		startDate = DateUtils.getDateByAdd(startDate, num);
		str = str + ',' + DateUtils.parseDate(startDate);
		boolean flag = startDate.before(endDate);
		if (flag) {
			return recursive(startDate,endDate,str,num);
		} else {
			return str;
		}
	}
	
	/**
	 * 
	 * countTimeOut:
	 * 适用:保养台账计算是否超时
	 * @param i 批准时间
	 * @param timeDifference 实际处理时间
	 * @exception 
	 * @since  1.0.0
	 */
	public static String countTimeOut(Date startDate,double disposeTime,Date endDate){
		long start = startDate.getTime() + (long)disposeTime*1000*60*60;
		long end = endDate.getTime();
		if (end > start) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/**
	 * 
	 * countMoney:
	 * 适用:计算工程量金额
	 * @param isRegulation
	 * @param regulation
	 * @param amount
	 * @param price
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static Double countMoney(String isRegulation,Double regulation,Double amount,String price){
		double d=0.0;
		if ("0".equals(isRegulation)||StringUtils.isEmpty(isRegulation)) {
			//非增减倍数
			if (StringUtils.isNotBlank(price)) {
				d = BigDecimalUtils.multiply(amount, Double.parseDouble(price));
			}
		} else {
			if (StringUtils.isNotBlank(price)) {
				double p = BigDecimalUtils.multiply(amount, Double.parseDouble(price));
				d = BigDecimalUtils.multiply(regulation,p);
			}
		}
		return BigDecimalUtils.round(d,2,BigDecimal.ROUND_HALF_UP);
	}
	
	
	/**
	 * 
	 * countPassRate:
	 * 适用:计算合格率
	 * @param checkqty //抽查总数
	 * @param qty //合格数
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String countPassRate(Integer checkqty, Integer qty){
		String successRate = "";
		if (null != checkqty && checkqty != 0) {
			successRate = BigDecimalUtils.multiply(BigDecimalUtils.divide(Double.parseDouble(qty.toString()),Double.parseDouble(checkqty.toString()),4,BigDecimal.ROUND_HALF_UP),100.0) + "%";
		} else {
			successRate = "100.0%";
		}
		return successRate;
	}
	
	/**
	 * 
	 * @describe 计算百分率
	 * @see 
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @return 
	 * @exception
	 */
	public static String countCentage(Integer divisor,Integer dividend){
		String successRate = "";
		if (null != divisor && dividend != 0) {
			successRate = BigDecimalUtils.multiply(BigDecimalUtils.divide(Double.parseDouble(divisor.toString()),Double.parseDouble(dividend.toString()),4,BigDecimal.ROUND_HALF_UP),100.0) + "%";
		}
		return successRate;
	}
	
	/**
	 * 
	 * countLicenseTime:
	 * 适用:计算许可剩余时限(单位:时)
	 * @param beginDate
	 * @param delayTime
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static double countLicenseTime(Date endDate,Double delayTime) {
		Double d = 0.0;
		if (null != endDate) {
			if (null!=delayTime) {
				d = BigDecimalUtils.round(((endDate.getTime() - DateUtils.getCurrentDate().getTime())/(3600*1000) + delayTime*24), 2);
			} else {
				d = BigDecimalUtils.round((endDate.getTime() - DateUtils.getCurrentDate().getTime())/(3600*1000), 2);
			}
		}
		return d;
	}
	
	public static void main(String[] args) {
	}
	
	
	/**
	 * 
	 * sumDouble:
	 * 适用:double类型数据相加
	 * @param d1
	 * @param d2
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static double sumDouble(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}
}
