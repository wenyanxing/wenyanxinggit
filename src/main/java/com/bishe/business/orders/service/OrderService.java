package com.bishe.business.orders.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishe.business.customer.entity.Customer;
import com.bishe.business.customer.service.CustomerService;
import com.bishe.business.orders.entity.Orders;
import com.bishe.business.product.service.ProductService;
import com.bishe.core.service.BaseGenericsService;

@Service
public class OrderService extends BaseGenericsService<Orders> {

	@Autowired
	private ProductService pService;
	@Autowired
	private CustomerService cService;

	// 获取订单中的商品信息
	public List<Map<String, Object>> getPinOrder(String pids) {
		String[] perp = pids.split(",");
		List<Map<String, Object>> pList = new ArrayList<Map<String, Object>>();
		for (int m = 0; m < perp.length; m++) {
			String pn = perp[m];
			String[] pnfg = pn.split("_");
			Map<String, Object> pmap = new HashMap<String, Object>();
			pmap.put("product", pService.find(Long.parseLong(pnfg[0])));
			pmap.put("num", Integer.parseInt(pnfg[1]));
			pList.add(pmap);
		}
		return pList;
	}

	// 获取某人订单列表
	public List<Orders> getOrders(Long cid, Integer state) {
		Map<String, Object> args = new HashMap<String, Object>();
		Customer customer = cService.find(cid);
		String jql = "";
		if (state == 9) {
			jql = "from Orders o where o.customer = :customer order by o.createDate desc";
		} else {
			jql = "from Orders o where o.customer = :customer and o.state = :state order by o.createDate desc";
			args.put("state", state);
		}
		args.put("customer", customer);
		return query(jql, args);
	}
}
