package com.bishe.business.article.service;

import org.springframework.stereotype.Service;

import com.bishe.core.dao.jpa.Page;
import com.bishe.core.service.BaseGenericsService;
import com.bishe.business.article.entity.Article;

@Service
public class ArticleService extends BaseGenericsService<Article> {
	public Page getArticlePage(Page page) {
		StringBuffer jql = new StringBuffer();
		jql.append("select @rowno:=@rowno + 1 AS num,a.* FROM article a,(SELECT @rowno:=0) b ");
		Object[] paramsEnd = new Object[0];
		page = pageQuery(page, jql.toString(), paramsEnd);
		//jdbcTemplate.query(sql, rowMapper);
		return page;
	}
}
