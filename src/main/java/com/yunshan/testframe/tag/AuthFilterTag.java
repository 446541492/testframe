package com.yunshan.testframe.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.yunshan.testframe.Listener.SysAuthListener;
import com.yunshan.testframe.beans.Authuser;
import com.yunshan.testframe.util.KeyConstant;
import com.yunshan.testframe.util.WebUtil;

/**
 * @Title: AuthFilterTag.java
 * @Package com.yunshan.testframe.tag
 * @Description: TODO(权限验证标签)
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月13日 上午10:43:42
 * @version V1.0 Update Logs: *
 *          **************************************************** Name: Date:
 *          Description: ******************************************************
 */
public class AuthFilterTag extends TagSupport
{
	/**
	 * 请求路径如：/index
	 */
	private String requestPath;

	public int doStartTag() throws JspException
	{
		boolean result = false;
		HttpSession session = ((HttpServletRequest) pageContext.getRequest()).getSession();
		//测试代码，请删除
		Authuser loginUser = new Authuser();
		loginUser.setId(1);
		loginUser.setIsAdmin(0);
		loginUser.setUserName("王磊");
		List<String> userAuthList = new ArrayList<String>();
		userAuthList.add("/test");
		loginUser.setAuthList(userAuthList);
		WebUtil.setSessionUser(session, loginUser);
		//测试代码，请删除
		if (session == null || WebUtil.getSessionUser(session) == null)
		{
			try
			{
				((HttpServletResponse) pageContext.getResponse()).sendRedirect(KeyConstant.TO_LOGIN);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}else{
			Authuser user = (Authuser)WebUtil.getSessionUser(session);// 获取此时登陆的用户
			// 既符合系统权限，又符合用户权限才显示
			if (SysAuthListener.authList.contains(requestPath) && user.getAuthList().contains(requestPath))
			{
				result = true;
			}
		}
		
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;// 真：返回EVAL_BODY_INCLUDE（执行标签）；假：返回SKIP_BODY（跳过标签不执行）
	}

	public String getRequestPath()
	{
		return requestPath;
	}

	public void setRequestPath(String requestPath)
	{
		this.requestPath = requestPath;
	}
}
