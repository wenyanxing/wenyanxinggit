package com.bishe.business.comment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bishe.core.entity.IdEntity;
//商品评论
@SuppressWarnings("serial")
@Entity
@Table(name = "comment")
public class Comment extends IdEntity {
	// 用户ID
	private Long cid;
	// 商品ID
	private Long pid;
	// h,z,c 好中差
	private String dj;
	// 内容
	private String content;
	// 评论时间
	private Date createdate;

	@Column(name = "cid")
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "createdate")
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "pid")
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "dj")
	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
