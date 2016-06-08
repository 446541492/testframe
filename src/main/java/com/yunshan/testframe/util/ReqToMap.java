package com.yunshan.testframe.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class ReqToMap {
	private static Logger logger = Logger.getLogger(ReqToMap.class);
	/**
	 * 封装req 至map同名封装成 string[]
	 * @param req
	 * @return
	 */
	public static Map toMap(HttpServletRequest req){
		Map paramMap=new HashMap<String,Object>();
		Map map=req.getParameterMap();
		Iterator iter = map.keySet().iterator();
	    while (iter.hasNext()) {
	      String key = (String)iter.next();
	      String[] value = (String[])map.get(key);
	      if (value.length == 1)
	        paramMap.put(key, value[0]);
	      else
	        paramMap.put(key, value);
	    }
		return paramMap;
	}
	public static void initKeyTOmap(Map map,String key){
		if(map.get("key")==null) map.put(key,"");
	}
	/**
	 * 初始化时间  string ===》 int
	 * @param map
	 */
	public static void init_strTOint(Map map){
		stringToInt(map,"begin_date");stringToInt(map,"end_date");
		stringToInt(map,"begin_hour");stringToInt(map,"end_hour");
	}
	/**
	 * 初始化时间   int===》 string
	 * @param map
	 */
	public static void init_intTOstring(Map map){
		intTOstring(map,"begin_date", 1);intTOstring(map,"end_date", 1);
		intTOstring(map,"begin_hour", 2);intTOstring(map,"end_hour", 2);
	}
	private static void intTOstring(Map map,String key,int type){
		String value =null;
		Object obj=map.get(key);
		if(obj instanceof Integer){
			value=String.valueOf(obj);
		}else{value=(String)obj;}
		if (value != null) {
			if(type==1){ 
				value=value.substring(0,4)+"-"+value.substring(4,6)+"-"+value.substring(6);
			}else{
				if(value.length()==3) value="0"+value;
				if(value.length()==2) value="00"+value;
				if(value.length()==1) value="000"+value;
				value=value.substring(0,2)+":"+value.substring(2,4);
			}
		}else{value="";}
		map.put(key, value);
	}
	private static String stringToInt(Map map,String key){
		String value = (String) map.get(key);
		if (value != null) {
			if (!value.matches("^[0-9]*$")){ value = value.replaceAll("[^0-9]", "");	map.put(key,value);}
		}else value=new String();
		return value;
	}
}
