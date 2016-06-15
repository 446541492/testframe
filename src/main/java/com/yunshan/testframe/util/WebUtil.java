package com.yunshan.testframe.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.chainsaw.Main;

import com.yunshan.testframe.KeyConstant;

/** 
 * @Title: WebUtil.java 
 * @Package com.yunshan.testframe.util 
 * @Description: TODO(用一句话描述该类做什么) 
 * @author  <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月13日 上午11:38:12 
 * @version V1.0 
 * Update Logs: * **************************************************** 
 * Name: 
 * Date: 
 * Description: ****************************************************** 
 */
public class WebUtil
{
	public static void  setSessionUser(HttpSession session,Object user){
		if(session!=null){
			session.setAttribute(KeyConstant.SESSION_KEY_USER, user);
		}
	}
	public static Object  getSessionUser(HttpSession session){
		if(session!=null){
			return session.getAttribute(KeyConstant.SESSION_KEY_USER);
		}else{
			return null;
		}
	}
	public static void main(String[] args)
	{
	}
}

