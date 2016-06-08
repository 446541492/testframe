package com.yunshan.testframe.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunshan.testframe.beans.User;


/**
 * mybatis Dao测试接口
 * 
 * @author wangl
 * 
 */
@Repository(value = "userDao")
public interface UserDao  {
    public User select(int id);
    public List<User> selectList();
    public void insert(User u);
}
