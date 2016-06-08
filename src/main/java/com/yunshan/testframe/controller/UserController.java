package com.yunshan.testframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunshan.testframe.base.BaseController;
import com.yunshan.testframe.beans.User;
import com.yunshan.testframe.interceptor.PageInterceptor;
import com.yunshan.testframe.service.UserService;

/**
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @version 1.0.0, 2016/6/3. 13:15
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {
	@Autowired
	UserService userService;

	@RequestMapping("index")
	public String index() {
		String name = userService.select(1).getName();
		System.out.println(name);
		System.out.println("分页 ");
		PageInterceptor.Page page = userService.selectList(1, 2);
		request.setAttribute("page", page);
		return "index";
	}

	@RequestMapping("test")
	public String test() {
		User u = new User();
		u.setAge(11);
		u.setName("123");
		userService.insert(u);
		return "index";
	}

}
