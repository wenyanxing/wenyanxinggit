package com.bishe.business.orders.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bishe.business.orders.entity.Orders;
import com.bishe.business.orders.service.OrderService;
import com.bishe.core.Constants;
import com.bishe.core.controller.BaseController;
import com.bishe.core.dao.jpa.Page;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {

	@Autowired
	private OrderService oService;

	// 异步更新订单状态
	@ResponseBody
	@RequestMapping("/updateState")
	public Map<String, Object> updateState(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Integer state = Integer.parseInt(request.getParameter("state"));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Orders oders = oService.find(id);
		oders.setState(state);
		oService.save(oders);
		resultMap.put("msg", "模拟支付成功！");
		return resultMap;
	}

	// 订单分页
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request) {
		Page page = buildPage(request, Constants.DESC, "ddno");
		page = oService.pageQuery(page, buildFilters(request));
		model.addAttribute("page", page);
		return "/admin/orders/list";
	}

	// 订单详情
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Orders orders = oService.find(id);
		List<Map<String, Object>> pList = oService
				.getPinOrder(orders.getPids());
		orders.setpList(pList);
		model.addAttribute("orders", orders);
		return "/admin/orders/edit";
	}

	// 保存订单
	@RequestMapping("/save")
	public String save(@ModelAttribute("orders") Orders orders, Model model,
			HttpServletRequest request) {
		Orders orders2 = oService.find(orders.getId());
		orders2.setWlxx(orders.getWlxx());
		orders2.setWlno(orders.getWlno());
		orders2.setState(2);
		oService.save(orders2);
		return "redirect:/orders/list";
	}
}
