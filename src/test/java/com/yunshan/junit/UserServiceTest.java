package com.yunshan.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.yunshan.testframe.beans.User;
import com.yunshan.testframe.service.UserService;

/**
 * @Title: UserControllerTest.java
 * @Package com.yunshan.junit
 * @Description: TODO(用一句话描述该类做什么)
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月7日 下午4:37:18
 * @version V1.0 Update Logs: *
 *          **************************************************** Name: Date:
 *          Description: ******************************************************
 */
@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
/**
 * 一个是DependencyInjectionTestExecutionListener 监听测试类中的依赖注入是否正确 不正确的话有什么结果我也不知道 因为我每次都写对的。。。
 另一个是TransactionalTestExecutionListener 监听测试类中的事务 如果测试类中涉及事务 就必须配这个监听器
 *
 */
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	UserService service;

	@Test
	public void testEmployee() {
		User u = service.select(1);
		assertEquals("孤傲苍狼", u.getName());
	}

	/**
	 * 
	 * @Title: testAdd
	 * @Description: 事务回滚测试service.insert()方法里抛出异常，回滚插入操作
	 * @author <a href="http://www.wanglay.com">Lei Wang</a>
	 * @throws
	 */
//	@Transactional
//	@Test
//	public void testAdd() {
//		User u = new User();
//		u.setName("王磊");
//		u.setAge(22);
//		service.insert(u);
//		System.out.println(u.getId());
//	}

}
