package com.bishe.business.catalog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bishe.core.entity.IdEntity;

/**
 * 商品种类
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "catalog")
public class Catalog extends IdEntity {

	/** 名称 */
	private String name;

	@Column(name = "name", length = 50, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
