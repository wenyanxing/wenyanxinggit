package com.bishe.business.customer.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bishe.business.customer.entity.Customer;
import com.bishe.business.customer.service.CustomerService;
import com.bishe.core.Constants;
import com.bishe.core.ServiceException;
import com.bishe.core.controller.BaseController;
import com.bishe.core.dao.jpa.Page;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService cuService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("customer", new Customer());
		return "admin/customer/edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", cuService.find(id));
		return "admin/customer/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customer") Customer customer,
			Model model, HttpServletRequest request) {
		try {
			cuService.save(customer);
			return "redirect:/customer/list";
		} catch (ServiceException se) {
			model.addAttribute(MODEL_MSG, se.getMessage());
			return "admin/customer/edit";
		}
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		Customer customer = cuService.find(id);
		if (customer != null) {
			cuService.remove(customer);
			redirectAttributes.addFlashAttribute(REDIRECT_MSG, "删除成功");
		} else {
			redirectAttributes.addFlashAttribute(REDIRECT_MSG, "未找到要删除的内容");
		}
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "list")
	public String list(Model model, ServletRequest request) {
		String phone = request.getParameter("phone");
		Page page = new Page(getPageNo(request), Constants.DEF_PAGE_SIZE);
		page = cuService.getEntityPage(page, phone);
		model.addAttribute("page", page);
		return "admin/customer/list";
	}

	@ModelAttribute
	public void preModelAttribute(
			@RequestParam(value = "id", required = false) Long id, Model model) {
		if (id != null) {
			Customer customer = cuService.find(id);
			model.addAttribute("customer", customer);
		}
	}

	// ---------------------------前台-----------------------------
	// 用户注册
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request) {
		return "/front/regist";
	}

	@RequestMapping("/sregist")
	public String sregist(Customer customer, HttpServletRequest request) {
		cuService.save(customer);
		return "redirect:/index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "/front/login";
	}

	// 用户登录
	@RequestMapping("/flogin")
	public String frontLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		Customer customer = cuService.cusLogin(username, pwd);
		if(customer != null){
			request.getSession().setAttribute("fuser", customer);
			return "redirect:/index";
		}else{
			return "/front/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("fuser");
		return "redirect:/index";
	}
}
