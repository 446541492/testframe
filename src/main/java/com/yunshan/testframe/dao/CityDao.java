package com.yunshan.testframe.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunshan.testframe.beans.Cityarea;


/** 
 * @Title: CityDao.java 
 * @Package com.yunshan.monitor.dao 
 * @Description: TODO(省市区查询) 
 * @author  <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月17日 上午10:19:38 
 * @version V1.0 
 * Update Logs: * **************************************************** 
 * Name: 
 * Date: 
 * Description: ****************************************************** 
 */
@Repository(value = "cityDao")
public interface CityDao
{
	/**
	 * 
	 * @Title: getCityListByParentId 
	 * @Description: 根据父级ID查询城市数据
	 * @author   <a href="http://www.wanglay.com">Lei Wang</a>
	 * @throws 
	 * @param parentId
	 * @return
	 */
	public List<Cityarea> getCityListByParentId(int parentId);
	/**
	 * 
	 * @Title: getCityListByLevel 
	 * @Description: 根据城市的级别查 
	 * @author   <a href="http://www.wanglay.com">Lei Wang</a>
	 * @throws 
	 * @param level
	 * @return
	 */
	public List<Cityarea> getCityListByLevel(int level);
}

