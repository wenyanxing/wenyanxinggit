package com.bishe.base.security.user.entity;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.bishe.base.security.role.entity.Role;
import com.bishe.core.entity.IdEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_user")
public class User extends IdEntity{

	private String loginId;
	
	private String pwd;
	
	private String repwd;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String usable;
	
	private String descn;
	
	private Date createTime;
	
	private Role role;
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Length(min = 4, message = "用户名长度不少于4")
	@Column(length=128, unique = true)
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Length(min = 8, message = "密码长度不少于8")
	@Column(length = 255)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Transient
	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	@Column(length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length = 1)
	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}
	
	@Column(length = 128)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length = 32)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(length = 512)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String desc) {
		this.descn = desc;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
