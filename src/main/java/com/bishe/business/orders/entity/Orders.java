package com.bishe.business.orders.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.bishe.business.customer.entity.Customer;
import com.bishe.core.entity.IdEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "orders")
public class Orders extends IdEntity {

	// 订单编号
	private String ddno;
	// 客户
	private Customer customer;
	// 商品集合id_num,id_num
	private String pids;
	// 合计金额
	private Integer hjje;
	// 收件人
	private String sjr;
	// 收件人电话
	private String sjrdh;
	// 地址
	private String address;
	// 订单状态 0未支付，1已支付，2已发货，3确认收货 9代表全部
	private Integer state;
	// 订单备注
	private String ddbz;
	// 创建时间
	private Date createDate;
	// 物流
	private String wlxx;
	// 物流单号
	private String wlno;

	// 订单详情
	List<Map<String, Object>> pList;

	@Transient
	public List<Map<String, Object>> getpList() {
		return pList;
	}

	public void setpList(List<Map<String, Object>> pList) {
		this.pList = pList;
	}

	@Column(name = "ddno")
	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cid", nullable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "pid")
	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	@Column(name = "hjje")
	public Integer getHjje() {
		return hjje;
	}

	public void setHjje(Integer hjje) {
		this.hjje = hjje;
	}

	@Column(name = "sjr")
	public String getSjr() {
		return sjr;
	}

	public void setSjr(String sjr) {
		this.sjr = sjr;
	}

	@Column(name = "sjrdh")
	public String getSjrdh() {
		return sjrdh;
	}

	public void setSjrdh(String sjrdh) {
		this.sjrdh = sjrdh;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "ddbz")
	public String getDdbz() {
		return ddbz;
	}

	public void setDdbz(String ddbz) {
		this.ddbz = ddbz;
	}

	@Column(name = "createdate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "wlxx")
	public String getWlxx() {
		return wlxx;
	}

	public void setWlxx(String wlxx) {
		this.wlxx = wlxx;
	}

	@Column(name = "wlno")
	public String getWlno() {
		return wlno;
	}

	public void setWlno(String wlno) {
		this.wlno = wlno;
	}

}
