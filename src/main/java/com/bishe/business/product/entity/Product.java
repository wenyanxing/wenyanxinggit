package com.bishe.business.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bishe.business.catalog.entity.Catalog;
import com.bishe.core.entity.IdEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "product")
public class Product extends IdEntity {

	// 商品名
	private String name;
	// 备注、备用字段
	private String bz;
	// 价格描述
	private Integer price;

	// 0正常 1缺货
	private Integer kczt;

	// 封面
	private String recover;
	// 品种
	private Catalog catalog;
	// 详细说明
	private String content;
	// 优惠状态0默认 1优惠
	private Integer state;

	@Column(name = "kczt")
	public Integer getKczt() {
		return kczt;
	}

	public void setKczt(Integer kczt) {
		this.kczt = kczt;
	}

	@Column(name = "state", nullable = false, columnDefinition = "INT default 0")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "catid", nullable = false)
	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "bz")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "price")
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "recover")
	public String getRecover() {
		return recover;
	}

	public void setRecover(String recover) {
		this.recover = recover;
	}

	@Type(type = "text")
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
