package com.bishe.business.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bishe.business.customer.entity.Customer;
import com.bishe.business.orders.entity.Orders;
import com.bishe.business.orders.service.OrderService;
import com.bishe.core.controller.BaseController;

@Controller
@RequestMapping("/percenter")
public class PercenterController extends BaseController {
	@Autowired
	private OrderService oService;

	@RequestMapping("/index")
	public String percenter(Model model, HttpServletRequest request) {
		Customer fuser = (Customer) request.getSession().getAttribute("fuser");
		int state = 9;
		if (StringUtils.isNotBlank(request.getParameter("state"))) {
			state = Integer.parseInt(request.getParameter("state"));
		}
		List<Orders> oList = oService.getOrders(fuser.getId(), state);
		// 获取订单内的商品信息
		for (Orders orders : oList) {
			orders.setpList(oService.getPinOrder(orders.getPids()));
		}
		model.addAttribute("oList", oList);
		request.setAttribute("state", state);
		return "/front/record";
	}

	@RequestMapping("/deleteOrder")
	public String deleteOrder(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Orders order = oService.find(id);
		oService.remove(order);
		return "redirect:/percenter/index";
	}
	
	@RequestMapping("/confirmOrder")
	public String confirmOrder(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Orders orders = oService.find(id);
		orders.setpList(oService.getPinOrder(orders.getPids()));
		request.setAttribute("orders", orders);
		return "/front/confirm2";
	}

	@RequestMapping("/payOrder")
	public String payOrder(Orders orders, HttpServletRequest request) {
		Orders orders2 = oService.find(orders.getId());
		orders2.setAddress(orders.getAddress());
		orders2.setDdbz(orders.getDdbz());
		orders2.setSjrdh(orders.getSjrdh());
		oService.save(orders2);
		request.setAttribute("orders", orders2);
		return "/front/payment2";
	}
}
