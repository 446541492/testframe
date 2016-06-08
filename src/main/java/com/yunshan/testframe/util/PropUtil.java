package com.yunshan.testframe.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropUtil {

	private static Logger logger=LoggerFactory.getLogger(PropUtil.class);
	
	public static Properties loadProperties(String filename){
		
		Properties prop=new Properties();
		FileInputStream fin=null;
		try {
			fin=new FileInputStream(filename);
			prop.load(fin);
		} catch (FileNotFoundException e) {
			logger.error("load prop file error", e);
		} catch (IOException e) {
			logger.error("load prop file error", e);
		}finally{
			if(fin!=null)
				try {
					fin.close();
				} catch (IOException e) {
				}
		}
		
		return(prop);
	}
	
	public static Properties loadChineseProperties(String filename){
		
		Properties prop=loadProperties(filename);
		if(prop!=null)
			supportChinese(prop);
		
		return(prop);
	}
	
	public static void supportChinese(Properties props){
		
		Set set=props.keySet();
		Iterator iter=set.iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			String value=props.getProperty(key);
			props.setProperty(key,convert(value));
		}
	}
	
	private static String convert(String s){
		
		if(s==null)
			return(null);
		char ccc[]=s.toCharArray();
        byte bbb[]=new byte[ccc.length];
        for(int i=0;i<bbb.length;i++){
       	        bbb[i]=(byte)ccc[i];
        }	
        return(new String(bbb));
	}
}
