package com.yunshan.testframe.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 *@author wangl
 */
public class GsonUtils {

	/**
	 * 将对象转换为json数据(必须带有注解 @Expose 才可以转换)
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(obj);
	}
	
	/**
	 * 将对象转换为json数据,无需带注解就可以转换
	 * @param obj
	 * @return
	 */
	public static void toJsonAll(Object obj ,HttpServletResponse res) {
		res.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter out = res.getWriter();
			String result = new Gson().toJson(obj);
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
