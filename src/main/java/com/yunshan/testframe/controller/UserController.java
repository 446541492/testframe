package com.yunshan.testframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value="index", method = RequestMethod.GET)
	public String index( @RequestParam(value="pageNum", required=true, defaultValue="1") int pageNum,@RequestParam(value="pageSize", required=true, defaultValue="3") int pageSize) {
		
	
		String name = userService.select(1).getName();
		System.out.println(name);
		System.out.println("分页 ");
		PageInterceptor.Page page = userService.selectList(pageNum, pageSize);
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
