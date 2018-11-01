package com.bishe.base.security.role.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bishe.core.entity.IdEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_role")
public class Role extends IdEntity {
	@Column(unique = true)
	private String name;// 角色code码,请用英文.
	private String description;// 角色描述

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
