package com.yunshan.testframe.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

/** 
 * @Title: CityService.java 
 * @Package com.yunshan.monitor.service 
 * @Description: TODO(用一句话描述该类做什么) 
 * @author  <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月17日 上午10:43:27 
 * @version V1.0 
 * Update Logs: * **************************************************** 
 * Name: 
 * Date: 
 * Description: ****************************************************** 
 */
public interface CityService
{
	/**
	 * 
	 * @Title: buildCityDateJs 
	 * @Description: TODO(查出省市区数据，并生成JS数据文件) 
	 * @author   <a href="http://www.wanglay.com">Lei Wang</a>
	 * @throws 
	 * @param session
	 * @throws IOException
	 */
	public void buildCityDateJs(HttpSession  session) throws IOException;
}

