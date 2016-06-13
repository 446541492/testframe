package com.yunshan.testframe.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangl
 */
@SuppressWarnings("serial")
public class Authuser implements Serializable {

	/**  */
	private Integer id;
	/** 用户名 */
	private String userName;
	/** 用户密码 */
	private String password;
	/** 0否，1是 */
	private Integer isAdmin;
	/**
	 * 用户权限列表
	 */
	private List<String> authList;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}
	
}