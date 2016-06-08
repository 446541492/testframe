package com.yunshan.testframe.base;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @version 1.0.0, 2016/6/3. 16:26
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;



	/*public PersonalCenteruser getSessionUser(){
		return (PersonalCenteruser)session.getAttribute("loginUser");
	}*/

    public static String out(String msg,HttpServletResponse res) {
        res.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try {
            out = res.getWriter();
            out.print(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
        return null;
    }
}
