package com.yunshan.testframe.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunshan.testframe.beans.Cityarea;
import com.yunshan.testframe.dao.CityDao;
import com.yunshan.testframe.service.CityService;
import com.yunshan.testframe.util.file.MyFileUtils;


/**
 * @Title: CityServiceImpl.java
 * @Package com.yunshan.monitor.service.impl
 * @Description: TODO(用一句话描述该类做什么)
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月17日 上午10:44:40
 * @version V1.0 Update Logs: *
 *          **************************************************** Name: Date:
 *          Description: ******************************************************
 */
@Service
public class CityServiceImpl implements CityService
{
	private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
	@Autowired
	CityDao cityDao;

	@Override
	public void buildCityDateJs(HttpSession session) throws IOException
	{
		List<Cityarea> cityList = cityDao.getCityListByLevel(1);
		String realPath = session.getServletContext().getRealPath("") + "/js/city/";
		MyFileUtils.isExist(realPath, true);
		CharSequence cs = null;
		File file = new File(realPath + "address.js");
		if (!cityList.isEmpty())
		{

			cs = buildJsData(cityList, 1);
			if (null != cs)
			{
				writeFile(cs, 1, file);
			}
		}
		cityList = cityDao.getCityListByLevel(2);
		if (!cityList.isEmpty())
		{
			cs = buildJsData(cityList, 2);
			if (null != cs)
			{
				writeFile(cs, 2, file);
			}
		}
		cityList = cityDao.getCityListByLevel(3);
		if (!cityList.isEmpty())
		{
			cs = buildJsData(cityList, 3);
			if (null != cs)
			{
				writeFile(cs, 3, file);
			}
		}
/*		cityList = cityDao.getCityListByLevel(4);
		if (!cityList.isEmpty())
		{
			cs = buildJsData(cityList, 4);
			if (null != cs)
			{
				writeFile(cs, 4, file);
			}
		}*/
	}

	/**
	 * @Title: buildJsData
	 * @Description: TODO(拼接地区JS数据)
	 * @author <a href="http://www.wanglay.com">Lei Wang</a>
	 * @throws
	 * @param data
	 * @param level
	 *            数据级别 1省 2 市 3区 4乡镇
	 * @return
	 */
	private CharSequence buildJsData(List<Cityarea> data, int level)
	{
		StringBuffer jsData = new StringBuffer();
		if (null != data && data.size() > 0)
		{
			String classify = null;
			if (level == 1)
			{
				classify = "province";
				jsData.append("/*************************************************\n全国各城市数组 id,name,parentid,sort \n **************************************************/\n");
			} else if (level == 2)
			{
				classify = "city";
			} else if (level == 3)
			{
				classify = "area";
			} else if (level == 4)
			{
				classify = "town";
			}
			jsData.append("var ").append(classify).append("=new Array();\n");
			jsData.append("var ").append(classify).append("Count=").append(data.size()).append(";\n");
			int count = 0;
			for (Cityarea city : data)
			{
				jsData.append(classify).append("[").append(count).append("]=new Array(").append(city.getId()).append(",\"").append(city.getAreaname()).append("\",")
						.append(city.getParentid()).append(",").append(city.getSort()).append(");\n");
				count++;
			}
			return jsData;
		}
		return null;

	}
/**
 * 
 * @Title: writeFile 
 * @Description: 输出地区数据到address.js文件 
 * @author   <a href="http://www.wanglay.com">Lei Wang</a>
 * @throws 
 * @param cs 
 * @param level
 * @param file
 * @throws IOException
 */
	private void writeFile(CharSequence cs, int level, File file) throws IOException
	{
		if (null != cs && null != file)
		{

			if (level == 1)
			{
				FileUtils.write(file, cs, "utf-8");
			} else if (level == 2)
			{
				FileUtils.write(file, cs, "utf-8", true);
			} else if (level == 3)
			{
				FileUtils.write(file, cs, "utf-8", true);
			} else if (level == 4)
			{
				FileUtils.write(file, cs, "utf-8", true);
			}

		}
	}
}
