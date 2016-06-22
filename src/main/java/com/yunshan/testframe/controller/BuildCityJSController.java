package com.yunshan.testframe.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunshan.testframe.base.BaseController;
import com.yunshan.testframe.beans.OperateMessage;
import com.yunshan.testframe.service.CityService;



/** 
 * @Title: BuildCityJSController.java 
 * @Package com.yunshan.monitor.controller 
 * @Description: 生成Js城市数据
 * @author  <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月17日 下午12:29:57 
 * @version V1.0 
 * Update Logs: * **************************************************** 
 * Name: 
 * Date: 
 * Description: ****************************************************** 
 */
@Controller
@RequestMapping(value = "/build")
public class BuildCityJSController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(BuildCityJSController.class);
	@Autowired
	CityService  cityService;
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/buildCityJs")
	public  OperateMessage buildJs() throws IOException{
		OperateMessage  msg = new OperateMessage();
		cityService.buildCityDateJs(session);
		msg.setCode(1);
		msg.setMsg("生成成功！");
		return msg;
	}
}

