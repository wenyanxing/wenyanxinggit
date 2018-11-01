package com.bishe.business.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishe.business.cart.entity.Cart;
import com.bishe.business.product.entity.Product;
import com.bishe.business.product.service.ProductService;
import com.bishe.core.Constants;
import com.bishe.core.service.BaseGenericsService;

@Service
public class CartService extends BaseGenericsService<Cart> {

	@Autowired
	private ProductService pService;

	// 检查购物车中是否已有商品
	public String ishave(Long cid, Long pid) {
		Product product = pService.find(pid);
		String jql = "from Cart c where c.cid = :cid and c.product = :product";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("cid", cid);
		args.put("product", product);
		Cart cart = find(jql, args);
		if (cart != null) {
			return Constants.YES;
		} else {
			return Constants.NO;
		}
	}

	// 获取单条记录
	public Cart getCart(Long cid, Long pid) {
		Product product = pService.find(pid);
		String jql = "from Cart c where c.cid = :cid and c.product = :product";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("cid", cid);
		args.put("product", product);
		return find(jql, args);
	}

	// 我的购物车
	public List<Cart> myCart(Long cid) {
		Map<String, Object> args = new HashMap<String, Object>();
		StringBuffer jql = new StringBuffer();
		jql.append("from Cart c where c.cid = :cid");
		args.put("cid", cid);
		return query(jql.toString(), args);
	}

	// 提交订单后，删除购物车商品
	@Transactional
	public void removeAfterToOrder(Long cid, String pids) {
		String ppzh = "";
		String[] pid = pids.split(",");
		for (int i = 0; i < pid.length; i++) {
			String[] tt = pid[i].split("_");
			ppzh += tt[0];
		}
		String[] ppzhs = ppzh.split(",");
		for (int i = 0; i < ppzhs.length; i++) {
			Cart cart = getCart(cid, Long.parseLong(ppzhs[i]));
			if(cart != null){
				this.remove(cart);
			}
		}
	}

}
