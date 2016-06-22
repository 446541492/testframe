package com.yunshan.testframe.beans;

import java.io.Serializable;

/**
*@author wangl
*/
@SuppressWarnings("serial")
public class Cityarea implements Serializable{ 

/** ID */
	private Integer id;
/** 栏目名 */
	private String areaname;
/** 父栏目 */
	private Integer parentid;
/**  */
	private String shortname;
/**  */
	private Integer areacode;
/**  */
	private Integer zipcode;
/**  */
	private String pinyin;
/**  */
	private String lng;
/**  */
	private String lat;
/** 1省或直辖市，2市，3区或县，4乡镇 */
	private String level;
/**  */
	private String position;
/** 排序 */
	private String sort;

	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setAreaname(String areaname) {
		this.areaname=areaname;
	}
	public String getAreaname() {
		return this.areaname;
	}
	public void setParentid(Integer parentid) {
		this.parentid=parentid;
	}
	public Integer getParentid() {
		return this.parentid;
	}
	public void setShortname(String shortname) {
		this.shortname=shortname;
	}
	public String getShortname() {
		return this.shortname;
	}
	public void setAreacode(Integer areacode) {
		this.areacode=areacode;
	}
	public Integer getAreacode() {
		return this.areacode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode=zipcode;
	}
	public Integer getZipcode() {
		return this.zipcode;
	}
	public void setPinyin(String pinyin) {
		this.pinyin=pinyin;
	}
	public String getPinyin() {
		return this.pinyin;
	}
	public void setLng(String lng) {
		this.lng=lng;
	}
	public String getLng() {
		return this.lng;
	}
	public void setLat(String lat) {
		this.lat=lat;
	}
	public String getLat() {
		return this.lat;
	}
	public void setLevel(String level) {
		this.level=level;
	}
	public String getLevel() {
		return this.level;
	}
	public void setPosition(String position) {
		this.position=position;
	}
	public String getPosition() {
		return this.position;
	}
	public void setSort(String sort) {
		this.sort=sort;
	}
	public String getSort() {
		return this.sort;
	}
}