package com.bishe.business.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bishe.business.catalog.entity.Catalog;
import com.bishe.core.dao.jpa.Page;
import com.bishe.core.service.BaseGenericsService;

@Service
public class CatalogService extends BaseGenericsService<Catalog>{

	public List<Catalog> getCatalog(){
		String hql="from Catalog";
		return (List<Catalog>)this.query(hql, null);
	}
	
	public Page getEntityPage(Page page) {
		StringBuffer jql = new StringBuffer();
		jql.append("select @rowno:=@rowno + 1 AS num,a.* FROM catalog a,(SELECT @rowno:=0) b ");
		Object[] paramsEnd = new Object[0];
		page = pageQuery(page, jql.toString(), paramsEnd);
		return page;
	}
}
