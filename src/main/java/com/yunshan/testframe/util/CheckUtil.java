package com.yunshan.testframe.util;

public class CheckUtil {
	/**
	 * 字符串是否为空
	 * @param strs
	 * @return 空返回true  否则返回false
	 */
	public static boolean isNullStrng(String... strs){
		for (String str:strs) {
			if(str==null||"".equals(str)) return true;
		}
		return false;
	}
	/**
	 * 验证手机号格式
	 * @param mobile	手机号
	 * @return
	 */
	public static boolean isMobileNum(String mobile){
		if(!isNullStrng(mobile)&&mobile.matches("^1[34578]\\d{9}$")){return true;}
		else{return false;}
	}
}
