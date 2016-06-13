package com.yunshan.testframe.dao;

import java.util.List;

/** 
 * @Title: AuthDao.java 
 * @Package com.yunshan.testframe.dao 
 * @Description: TODO(用一句话描述该类做什么) 
 * @author  <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月12日 下午4:13:56 
 * @version V1.0 
 * Update Logs: * **************************************************** 
 * Name: 
 * Date: 
 * Description: ****************************************************** 
 */
public interface AuthDao {
	public List<String> sysAuthList();
	public List<String> userAuthList(int userid);
}

