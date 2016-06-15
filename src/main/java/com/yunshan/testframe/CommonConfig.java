package com.yunshan.testframe;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunshan.testframe.util.config.FileSimpleConfig;





public class CommonConfig {
	private static final Logger logger = LoggerFactory.getLogger(CommonConfig.class);
	public static String path;//项目发布路径 /data/service/aaa/
	public static String contextPath;//项目 网站ip后web路径
	public static String fileServer;//文件服务器域名
	public static String imgDomain;//图片服务器域名
	public static String jsCssVersion;//js css 版本号
	
	/*短信参数*/
	public static String sms_spID; 
	public static String sms_password; 
	public static String sms_accessCode;
	public static String sms_content; 
	public static int sms_time;	//有效时间
	/*邮件参数*/
	public static String mail_host; 
	public static String mail_username; 
	public static String mail_password; 
	public static int mail_port; 
	public static String mail_vmPath;
	
	public static int debug;//本地开发标识
	static{
		path=CommonConfig.class.getResource("/").getPath();
		logger.debug("初始化配置文件 path="+path);
		FileSimpleConfig config=new FileSimpleConfig(path+"conf/config.conf");
		contextPath=config.getStringConfig("contextPath","");
		fileServer=config.getStringConfig("fileServer","");
		imgDomain=config.getStringConfig("imgDomain","");
		
		jsCssVersion=config.getStringConfig("jsCssVersion","");
		debug=config.getIntConfig("debug",0);
		
		sms_spID=config.getStringConfig("sms_spID","");
		sms_password=config.getStringConfig("sms_password","");
		sms_accessCode=config.getStringConfig("sms_accessCode","");
		sms_content=config.getStringConfig("sms_content","");
		sms_time=config.getIntConfig("sms_time",0);
		
		mail_host=config.getStringConfig("mail_host","");
		mail_username=config.getStringConfig("mail_username","");
		mail_password=config.getStringConfig("mail_password","");
		mail_port=config.getIntConfig("mail_port",0);
		mail_vmPath=config.getStringConfig("mail_vmPath","/WEB-INF/vm");
	}
}
