package com.yunshan.testframe.Listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.yunshan.testframe.CommonConfig;
import com.yunshan.testframe.dao.AuthDao;

/**
 * @Title: SysAuthListener.java
 * @Package com.yunshan.testframe.Listener
 * @Description: TODO(加载系统权限列表)
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月12日 下午4:27:25
 * @version V1.0 Update Logs: *
 *          **************************************************** Name: Date:
 *          Description: ******************************************************
 */
@Component("sysAuthListener")
public class SysAuthListener implements
		ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(SysAuthListener.class);
	@Autowired
	AuthDao authDao;
	/**
	 * 权限URL列表，用来做请求过滤
	 */
	public  static List<String> authList = null;
	/**
	 * 权限KEY列表，用来做页面展示过滤
	 */
	public  static List<String> authKeyList = null;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		authList = authDao.sysAuthList();
		logger.info("系统权限数据读取成功========="+authList.toString());
		logger.info("系统配置文件读取成功========="+CommonConfig.jsCssVersion);
	}

}
