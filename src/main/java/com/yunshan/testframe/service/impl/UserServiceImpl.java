package com.yunshan.testframe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunshan.testframe.beans.User;
import com.yunshan.testframe.dao.UserDao;
import com.yunshan.testframe.interceptor.PageInterceptor;
import com.yunshan.testframe.service.UserService;

/**
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @version 1.0.0, 2016/6/3. 13:15
 */

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public User select(int id) {

        return userDao.select(id);
    }

    @Override
    public PageInterceptor.Page selectList(int pageNum,int pageSize) {
    	//分页开始
        PageInterceptor.startPage(pageNum, pageSize);
        List<User> users = userDao.selectList();
        PageInterceptor.Page page=PageInterceptor.endPage();
        //分页结束
        return page;
    }
    /** 
     * 事务处理必需抛出异常 spring 才会帮事务回滚 
     */  
    @Transactional  
	@Override
	public void insert(User u) {
		userDao.insert(u);
		throw new RuntimeException();
	}
    

}
