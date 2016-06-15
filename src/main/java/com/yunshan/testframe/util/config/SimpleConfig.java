package com.yunshan.testframe.util.config;

import java.util.HashMap;

public class SimpleConfig {

	protected HashMap<String, String> config=new HashMap<String, String>();
	
	public String getStringConfig(String key){
		return getStringConfig(key,"");
	}
		
	public String getStringConfig(String key,String defaultValue){
		if(key==null)
			return(defaultValue);
		String value=(String)config.get(key);
		if(value==null)
			return(defaultValue);
		else
			return(value);
	}
	
	public int getIntConfig(String key,int defaultValue){
		String value=getStringConfig(key,null);
		if(value==null)
			return(defaultValue);
		try{
			int x=Integer.parseInt(value);
			return(x);
		}catch(NumberFormatException e){
			return(defaultValue);
		}
	}
	
	public boolean getBoolConfig(String key,boolean defaultValue){
		String value=getStringConfig(key,null);
		if(value==null)
			return(defaultValue);
		if(value.equalsIgnoreCase("YES")||value.equalsIgnoreCase("TRUE")||
				value.equalsIgnoreCase("1"))
			return(true);
		else
			return(false);
	}
	
	public void setConfig(String key,String value){
		if(key==null||value==null)
			return;
		config.put(key,value);
	}
}
