package com.bishe.business.cart.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bishe.business.cart.entity.Cart;
import com.bishe.business.cart.service.CartService;
import com.bishe.business.customer.entity.Customer;
import com.bishe.business.customer.service.CustomerService;
import com.bishe.business.orders.entity.Orders;
import com.bishe.business.orders.service.OrderService;
import com.bishe.business.product.entity.Product;
import com.bishe.business.product.service.ProductService;
import com.bishe.core.Constants;
import com.bishe.core.controller.BaseController;
import com.bishe.core.utils.StringUtil;

//购物车
@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService pService;
	@Autowired
	private CustomerService cService;
	@Autowired
	private OrderService oService;

	// 添加购物车
	@ResponseBody
	@RequestMapping("/addCart")
	public Map<String, Object> addCart(Model model, HttpServletRequest request) {
		Long cid = Long.parseLong(request.getParameter("cid"));
		Long pid = Long.parseLong(request.getParameter("pid"));
		Product product = pService.find(pid);
		Integer np = Integer.parseInt(request.getParameter("np"));
		Cart cart = new Cart();
		cart.setCid(cid);
		cart.setProduct(product);
		cart.setNp(np);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (cartService.ishave(cid, pid).equals(Constants.YES)) {
				resultMap.put("msg", "购物车中已有该商品！");
			} else {
				cartService.save(cart);
				resultMap.put("msg", "已加入购物车！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("msg", "添加失败，稍后再试！");
		}
		return resultMap;
	}

	// 移除商品
	@RequestMapping("/removeProduct")
	public String removeProduct(HttpServletRequest request) {
		Customer fuser = (Customer) request.getSession().getAttribute("fuser");
		Long pid = Long.parseLong(request.getParameter("pid"));
		cartService.remove(cartService.getCart(fuser.getId(), pid));
		return "redirect:/cart/mycart";
	}

	// 我的购物车页面
	@RequestMapping("/mycart")
	public String mycart(HttpServletRequest request) {
		Customer fuser = (Customer) request.getSession().getAttribute("fuser");
		if (fuser != null) {
			List<Cart> cartList = cartService.myCart(fuser.getId());
			request.setAttribute("cartList", cartList);
			return "/front/cart";
		} else {
			return "redirect:/customer/login";
		}
	}

	// 结算确认页面
	@SuppressWarnings("rawtypes")
	@RequestMapping("/confirm")
	public String confirm(HttpServletRequest request) {
		Customer fuser = (Customer) request.getSession().getAttribute("fuser");
		// 错误！！！
		List<Cart> cartList = new ArrayList<Cart>();
		String argJson = request.getParameter("argJson");
		List<Map> obList = JSON.parseArray(argJson, Map.class);
		String pids = "";
		for (int i = 0; i < obList.size(); i++) {
			String pid = obList.get(i).get("pid").toString();
			String np = obList.get(i).get("np").toString();
			pids += pid + "_" + np + ",";
			cartList.add(cartService.getCart(fuser.getId(), Long.parseLong(pid)));
		}
		pids = pids.substring(0, pids.lastIndexOf(","));
		request.setAttribute("cartList", cartList);
		request.setAttribute("hjje", request.getParameter("hjje"));
		request.setAttribute("cid", fuser.getId());
		request.setAttribute("pids", pids);
		return "/front/confirm";
	}

	// 支付页面付款
	@RequestMapping("/payment")
	public String payment(Orders orders, HttpServletRequest request) {
		Long cid = Long.parseLong(request.getParameter("cid"));
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 17位
		String ddno = fmt.format(new Date()) + StringUtil.getRandomString(4);// 21位
		orders.setCustomer(cService.find(cid));
		orders.setDdno(ddno);
		orders.setCreateDate(new Date());
		oService.save(orders);
		request.setAttribute("orders", orders);
		cartService.removeAfterToOrder(orders.getCustomer().getId(),
				orders.getPids());
		return "/front/payment";
	}
}
