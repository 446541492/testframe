package com.yunshan.testframe.beans;
/** 
 * @Title: OperateMessage.java 
 * @Package com.yunshan.monitor.beans 
 * @Description: TODO(操作消息反馈类) 
 * @author  <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月16日 下午3:08:08 
 * @version V1.0 
 * Update Logs: * **************************************************** 
 * Name: 
 * Date: 
 * Description: ****************************************************** 
 */
public class OperateMessage
{
	/**
	 * 操作码，0失败，1成功
	 */
	private int code = 0;
	/**
	 * 失败信息提示
	 */
	private String msg;
	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	
}

