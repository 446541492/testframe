package com.yunshan.testframe.util.config;


import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunshan.testframe.util.PropUtil;





public class FileSimpleConfig extends SimpleConfig implements ReloadListener{

	private static Logger logger=LoggerFactory.getLogger(FileSimpleConfig.class);
	
	private String configFile;
	
	public FileSimpleConfig(){
		
	}
	public FileSimpleConfig(String configFile){
		this.configFile=configFile;
		init();
	}
	
	public void setMonitor(ReloadMonitor monitor){
		monitor.addReloadListener(configFile,this);
	}
	
	public void init(){
		LinkedList<Properties> propList=new LinkedList<Properties>();
		loadPropertyFile(configFile,propList);
		for(Properties prop:propList){
			config.putAll((Hashtable)prop);
		}
		
		processVar();
	}
	
	private void loadPropertyFile(String fileName,LinkedList<Properties> propList){
		Properties prop=PropUtil.loadChineseProperties(fileName);
		if(prop==null||prop.size()==0){
			logger.warn("load config file error:"+fileName);
			return;
		}
		propList.addFirst(prop);
		String includeFile=prop.getProperty("include");
		if(includeFile==null)
			return;
		String propFiles[]=includeFile.split(",");
		for(int i=0;i<propFiles.length;i++){
			String propFileName=convertFilePath(fileName,propFiles[i]);
			loadPropertyFile(propFileName,propList);
		}	
	}
	
	private String convertFilePath(String curFileName,String includeFileName){
		includeFileName=replaceVar(includeFileName,null);
		File includeFile=new File(includeFileName);
		if(includeFile.isAbsolute())
			return includeFileName;
		
		File curFile=new File(curFileName);
		String parent=curFile.getParent();
		if(parent==null)
			return includeFileName;
		else
			return parent+File.separatorChar+includeFile;
	}
	private void processVar(){
		Set<String> keySet=config.keySet();
		for(String key:keySet){
			String value=(String)config.get(key);
			config.put(key, replaceVar(value,config));
		}
		
	}
	
	private String replaceVar(String value,Map varMap){
		int leftPos;
		while((leftPos=value.indexOf("${"))>=0){
			int rightPos=value.indexOf("}", leftPos+2);
			if(rightPos>leftPos){
				String varName=value.substring(leftPos+2, rightPos);
				String varValue=null;
				if(varMap!=null)
					varValue=(String)config.get(varName);
				if(varValue==null)
					varValue=System.getProperty(varName);
				if(varValue==null)
					varValue=System.getenv(varName);
				if(varValue==null)
					varValue="";
				value=value.replace("${"+varName+"}", varValue);
			}
		}
		
		return value;
	}
	public void reload(String key) {
		logger.info("reload config file:"+configFile);
		init();	
	}
	
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

}
