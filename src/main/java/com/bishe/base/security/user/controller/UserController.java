package com.bishe.base.security.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bishe.base.security.role.service.RoleService;
import com.bishe.base.security.user.entity.User;
import com.bishe.base.security.user.service.UserService;
import com.bishe.core.Constants;
import com.bishe.core.ServiceException;
import com.bishe.core.controller.BaseController;
import com.bishe.core.dao.jpa.Page;

@Controller
@RequestMapping("/security/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("roleList",roleService.findAll());
		model.addAttribute("user", new User());
		return "security/user/edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("roleList",roleService.findAll());
		model.addAttribute("user", userService.find(id));
		return "security/user/edit";
	}

	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Map<String, Object> save(@ModelAttribute("user") User user,
			Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.save(user);
			map.put("status", "true");
		} catch (ServiceException se) {
			map.put("status", "false");
		}
		return map;
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		User user = userService.find(id);
		if (user != null) {
			userService.remove(user);
			redirectAttributes.addFlashAttribute(REDIRECT_MSG,
					user.getLoginId() + "删除成功");
		} else {
			redirectAttributes.addFlashAttribute(REDIRECT_MSG, "未找到要删除的内容");
		}
		return "redirect:/security/user/list";
	}

	@RequestMapping(value = "loginIdExists")
	@ResponseBody
	public String loginIdExists(@RequestParam("loginId") String loginId,
			@RequestParam("id") String id) {
		User user = new User();
		user.setId(StringUtils.isNumeric(id) ? Long.valueOf(id) : null);
		user.setLoginId(loginId);
		if (userService.exists(user, "loginId")) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 判断loginId是否存在
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	// TODO:与loginIdExists方法基本相同，只是传参方式不同
	@ResponseBody
	@RequestMapping("judgeLoginId")
	public Map<String, Object> judgeLoginId(HttpServletRequest request,
			Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String loginId = StringUtils.trimToEmpty(request
				.getParameter("loginId"));
		String uId = StringUtils.trimToEmpty(request.getParameter("uId"));
		User user = new User();
		user.setId(StringUtils.isNumeric(uId) ? Long.valueOf(uId) : null);
		user.setLoginId(loginId);
		if (userService.exists(user, "loginId")) {
			map.put("status", true);
		} else {
			map.put("status", false);
		}
		return map;
	}

	@RequestMapping(value = "updatePwd", method = RequestMethod.GET)
	public String updatePwd(Model model, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("");
		model.addAttribute("user", u);
		return "security/user/updatePwd";
	}

	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String saveNewPwd(@RequestParam("id") Long id,
			@RequestParam("oldPwd") String oldPwd,
			@RequestParam("newPwd") String newPwd,
			@RequestParam("rePwd") String rePwd, Model model) {

		if (newPwd.equals(rePwd)) {
			User u = new User();
			u.setId(id);
			try {
				userService.updatePws(u, oldPwd, newPwd);
				model.addAttribute("isUpdate", true);
				model.addAttribute(MODEL_MSG, "密码修改完毕，下次登录请使用新密码！");
			} catch (ServiceException se) {
				model.addAttribute(MODEL_MSG, se.getMessage());
			}
		} else {
			model.addAttribute(MODEL_MSG, "两次输入密码不一致！");
		}

		return "security/user/updatePwd";
	}

	// 当不指定method类型的时候GET/POST都可以请求
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		Page page = buildPage(request, 20, Constants.ASC, "loginId");
		page = userService.pageQuery(page, buildFilters(request));
		model.addAttribute("page", page);
		return "security/user/list";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行获取用户的代码.
	 */
	@ModelAttribute
	public void preModelAttribute(
			@RequestParam(value = "id", required = false) Long id, Model model) {
		if (id != null) {
			User user = userService.find(id);
			model.addAttribute("user", user);// user属性赋值
		}
		model.addAttribute("statusMap", Constants.TF_MAP);
	}
}
