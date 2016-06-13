package com.yunshan.testframe.beans;

import java.io.Serializable;

/**
*@author wangl
*/
@SuppressWarnings("serial")
public class Authsysauth implements Serializable{ 

/**  */
	private Integer id;
/** 请求url */
	private String url;
/** 0父级,无URL只做是否显示判断 */
	private Integer parent;

	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public String getUrl() {
		return this.url;
	}
	public void setParent(Integer parent) {
		this.parent=parent;
	}
	public Integer getParent() {
		return this.parent;
	}
}