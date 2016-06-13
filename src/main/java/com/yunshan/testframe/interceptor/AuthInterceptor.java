package com.yunshan.testframe.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yunshan.testframe.Listener.SysAuthListener;
import com.yunshan.testframe.base.BaseController;
import com.yunshan.testframe.beans.Authuser;
import com.yunshan.testframe.util.KeyConstant;
import com.yunshan.testframe.util.StringUtil;
import com.yunshan.testframe.util.WebUtil;

/**
 * @Title: AuthInterceptor.java
 * @Package com.yunshan.testframe.interceptor
 * @Description: TODO(用一句话描述该类做什么)
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月12日 下午5:50:10
 * @version V1.0 Update Logs: *
 *          **************************************************** Name: Date:
 *          Description: ******************************************************
 */
public class AuthInterceptor implements HandlerInterceptor
{
	private static Logger logger =LoggerFactory.getLogger(AuthInterceptor.class);
	/**
	 * 需要排除的页面
	 */
	private String excludedRequests;

	private String[] excludedPageArray;
	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行
	 * ，而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的
	 * ，这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		boolean isExcludedPage = false;
		String requestPath= request.getServletPath();
		logger.info("请求路径========"+requestPath);
		for (String page : excludedPageArray)
		{// 判断是否在过滤url之外
			if (requestPath.equals(page)&&!SysAuthListener.authList.contains(requestPath))
			{
				isExcludedPage = true;
				break;
			}
		}
		if (isExcludedPage)
		{// 在过滤url之外
			return true;
		} else
		{// 不在过滤url之外，判断session是否存在
			HttpSession session = ((HttpServletRequest) request).getSession();
			
			//模拟登录状态，测试代码，请删除
			Authuser loginUser = new Authuser();
			loginUser.setId(1);
			loginUser.setIsAdmin(0);
			loginUser.setUserName("王磊");
			List<String> userAuthList = new ArrayList<String>();
			userAuthList.add("/test");
			loginUser.setAuthList(userAuthList);
			WebUtil.setSessionUser(session, loginUser);
			//结束，测试通过后删除
			
			
			if (session == null || WebUtil.getSessionUser(session) == null)
			{
				response.sendRedirect(KeyConstant.TO_LOGIN);
				return false;
			} else
			{
				if(loginUser.getAuthList().contains(requestPath)){
					return true;
				}else{
					response.setStatus(403);
					BaseController.out("403无权限访问", (HttpServletResponse) response);
					return false;
				}
				
			}
		}
	}

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，
	 * 它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行
	 * ，也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用
	 * ，这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法
	 * ，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前
	 * ，要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		// TODO Auto-generated method stub

	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
	 * 也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		// TODO Auto-generated method stub

	}

	public String getExcludedRequestss()
	{
		return excludedRequests;
	}

	public void setExcludedRequests(String excludedRequests)
	{
		this.excludedRequests = excludedRequests;
		if (StringUtil.isNotEmpty(excludedRequests))
		{
			excludedPageArray = excludedRequests.split(",");
		}
	}
	
}
