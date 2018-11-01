package com.bishe.business.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishe.business.catalog.entity.Catalog;
import com.bishe.business.catalog.service.CatalogService;
import com.bishe.business.product.entity.Product;
import com.bishe.core.dao.jpa.Page;
import com.bishe.core.service.BaseGenericsService;

@Service
public class ProductService extends BaseGenericsService<Product> {
	@Autowired
	private CatalogService catService;

	public Page getEntityPage(Page page, String cid, Integer state) {
		StringBuffer jql = new StringBuffer();
		jql.append("select @rowno:=@rowno + 1 AS num,a.*,c.name cname FROM product a LEFT JOIN catalog c on c.id = a.catid,(SELECT @rowno:=0) b ");
		jql.append("where 1 = 1 ");
		if (StringUtils.isNotBlank(cid)) {
			jql.append(" and a.catid = " + cid);
		}
		if(state != 9){
			jql.append(" and state = " + state);
		}
		Object[] paramsEnd = new Object[0];
		page = pageQuery(page, jql.toString(), paramsEnd);
		return page;
	}

	// 获取某状态的list
	public List<Product> getByState(Integer state, String cid) {
		Map<String, Object> args = new HashMap<String, Object>();
		StringBuffer jql = new StringBuffer();
		if (StringUtils.isNotBlank(cid)) {
			Long lcid = Long.parseLong(cid);
			Catalog catalog = catService.find(lcid);
			jql.append("from Product p where p.state = :state and p.catalog = :catalog order by p.catalog");
			args.put("catalog", catalog);
		} else {
			jql.append("from Product p where p.state = :state order by p.catalog");
		}
		args.put("state", state);
		return query(jql.toString(), args);
	}
}
