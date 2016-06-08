package com.yunshan.testframe.service;

import com.yunshan.testframe.beans.User;
import com.yunshan.testframe.interceptor.PageInterceptor;


/**
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @version 1.0.0, 2016/6/3. 13:15
 */
public interface UserService {
    public User select(int id);
    public PageInterceptor.Page selectList(int pageNum,int pageSize);
    public void insert(User u);
}
