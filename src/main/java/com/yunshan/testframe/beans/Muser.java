package com.yunshan.testframe.beans;

import java.io.Serializable;
import java.util.Date;

import com.yunshan.testframe.util.date.TimeUtil;

/**
*@author wangl
*/
@SuppressWarnings("serial")
public class Muser implements Serializable{ 

/**  */
	private Integer id;
/** 帐号 */
	private String name;
/** 密码 */
	private String password;
/** 电话 */
	private String phone;
/** 真实姓名 */
	private String realName;
/** 省ID */
	private String provinceId;
/** 市ID */
	private String cityId;
/** 区ID */
	private String areaId;
/** 联系地址 */
	private String address;
/** 邮箱 */
	private String mail;
/** 公司名称 */
	private String company;
/** 备用电话 */
	private String backPhone;
/** 头像 */
	private String headPic;
/** 用户状态(0-停用 ,1-启用) */
	private int state;
/** 创建时间 */
	private Date createDate;
/** 到期时间 */
	private Date endDate;
/** 所属公司，本系统中超级用户的ID就是公司ID,0是超级用户由运营平台开户 */
	private Integer companyId;
/** 创建人用户ID */
	private Integer createUserId;
/** 创建者类型(0-本地 ,1-运维后台) */
	private int creatorType;

	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public String getPhone() {
		return this.phone;
	}
	public void setRealName(String realName) {
		this.realName=realName;
	}
	public String getRealName() {
		return this.realName;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId=provinceId;
	}
	public String getProvinceId() {
		return this.provinceId;
	}
	public void setCityId(String cityId) {
		this.cityId=cityId;
	}
	public String getCityId() {
		return this.cityId;
	}
	public void setAreaId(String areaId) {
		this.areaId=areaId;
	}
	public String getAreaId() {
		return this.areaId;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public String getAddress() {
		return this.address;
	}
	public void setMail(String mail) {
		this.mail=mail;
	}
	public String getMail() {
		return this.mail;
	}
	public void setCompany(String company) {
		this.company=company;
	}
	public String getCompany() {
		return this.company;
	}
	public void setBackPhone(String backPhone) {
		this.backPhone=backPhone;
	}
	public String getBackPhone() {
		return this.backPhone;
	}
	public void setHeadPic(String headPic) {
		this.headPic=headPic;
	}
	public String getHeadPic() {
		return this.headPic;
	}
	public void setState(int state) {
		this.state=state;
	}
	public int getState() {
		return this.state;
	}
	public String getStateStr() {
		if(getState()==1){
			return "启用";
		}else{
			return "停用";
		}
	}
	public void setCreateDate(Date createDate) {
		this.createDate=createDate;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	public String getCreateDateStr() {
		return null!=getCreateDate()?TimeUtil.formatDateTimeToStr(getCreateDate(), TimeUtil.FULL_PATTERN):"";
	}
	public void setEndDate(Date endDate) {
		this.endDate=endDate;
	}
	public String getEndDateStr() {
		return null!=getEndDate()?TimeUtil.formatDateTimeToStr(getEndDate(), TimeUtil.FULL_PATTERN):"";
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setCompanyId(Integer companyId) {
		this.companyId=companyId;
	}
	public Integer getCompanyId() {
		return this.companyId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId=createUserId;
	}
	public Integer getCreateUserId() {
		return this.createUserId;
	}
	public void setCreatorType(int creatorType) {
		this.creatorType=creatorType;
	}
	public int getCreatorType() {
		return this.creatorType;
	}
}