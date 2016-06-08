package com.yunshan.testframe.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**

 * ClassName: User
 *
 * @author wangl
 * @date 2015-1-7
 */
public class User implements Serializable {

    /**
     */
    private static final long serialVersionUID = 843976590753806624L;

    /**
     * 用户ID	PK，
     */
    private String id;
    /**
     * 密码 MD5加密
     */
    private String password;

    private String repassword;
    private int age;
    /**
     * 姓名
     */
    private String name;

    private Short sex = 1;

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 	0正常,1冻结
     */
    private Short status;

    /**
     * 
     * 用户与创建人存在N-1关联
     */
    private User creater;

    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修
     * 用户与修改人存在N-1关联
     */
    private User modifier;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 
     * 用户与审核人存在N-1关联
     */
    private User checker;

    /**
     * 审核时间
     */
    private Date checkDate;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getModifier() {
        return modifier;
    }

    public void setModifier(User modifier) {
        this.modifier = modifier;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
