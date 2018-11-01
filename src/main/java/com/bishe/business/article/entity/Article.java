package com.bishe.business.article.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import com.bishe.core.entity.IdEntity;

/**
 * 新闻通知
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "article")
public class Article extends IdEntity {

	/** 标题 */
	private String title;
	
	/** 概要 */
	private String outline;

	/** 内容 */
	private String content;

	/** 发布时间 */
	private Date pubTime = new Date();

	@Column(name = "outline")
	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	@Column(name = "title", length = 255, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Type(type = "text")
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "pub_time", nullable = false)
	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

}
