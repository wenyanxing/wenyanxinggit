package com.bishe.business.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bishe.business.product.entity.Product;
import com.bishe.core.entity.IdEntity;

//购物车
@SuppressWarnings("serial")
@Entity
@Table(name = "cart")
public class Cart extends IdEntity {

	// 客户ID
	private Long cid;
	// 商品
	private Product product;

	private Integer np;

	@Column(name = "np")
	public Integer getNp() {
		return np;
	}

	public void setNp(Integer np) {
		this.np = np;
	}

	@Column(name = "cid")
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@OneToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "pid", nullable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

}
