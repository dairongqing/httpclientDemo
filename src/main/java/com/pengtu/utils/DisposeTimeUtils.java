package com.pengtu.utils;

/**
 * 通过损坏类型编码获取时限
 * @author hlq
 *
 */
public class DisposeTimeUtils {
	public static double getDisposeTime(String damageType) {
		Double disposeTime = null;
		if (damageType.equals("11") || damageType.equals("12") || damageType.equals("19")) {
			disposeTime = 72.0;
		}else if (damageType.equals("13") || damageType.equals("14")) {
			disposeTime = 48.0;
		}else if (damageType.equals("15") || damageType.equals("16")) {
			disposeTime = 48.0;
		}else if (damageType.equals("17") || damageType.equals("18")) {
			disposeTime = 24.0;
		}else if (damageType.equals("20")) {
			disposeTime = 24.0;
		}else {
			disposeTime = 48.0;
		}
		return disposeTime;
	}
}
