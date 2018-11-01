package com.bishe.business.customer.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bishe.business.customer.entity.Customer;
import com.bishe.core.dao.jpa.Page;
import com.bishe.core.service.BaseGenericsService;

@Service
public class CustomerService extends BaseGenericsService<Customer> {

	public Page getEntityPage(Page page, String phone) {
		StringBuffer jql = new StringBuffer();
		jql.append("select @rowno:=@rowno + 1 AS num,a.* FROM customer a,(SELECT @rowno:=0) b ");
		if (StringUtils.isNotBlank(phone)) {
			jql.append("where a.phone = " + phone);
		}
		Object[] paramsEnd = new Object[0];
		page = pageQuery(page, jql.toString(), paramsEnd);
		return page;
	}

	// 用户登录
	public Customer cusLogin(String username, String pwd) {
		String hql = "from Customer u where u.username = :username and u.pwd = :pwd";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", username);
		args.put("pwd", pwd);
		return find(hql, args);
	}
}
