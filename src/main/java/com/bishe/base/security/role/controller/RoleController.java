package com.bishe.base.security.role.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bishe.base.security.role.entity.Role;
import com.bishe.base.security.role.service.RoleService;
import com.bishe.core.controller.BaseController;

/**
 * 角色管理
 */
@RequestMapping("/security/role")
@Controller
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		model.addAttribute("roles", roleService.findAll());
		return "/security/role/index";
	}

	/**
	 * 获得全部角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Object list(ServletRequest request) {
		return roleService.findAll();
	}

	@RequestMapping("create")
	public String create(Model model) {
		model.addAttribute("role", new Role());
		return "/security/role/edit";
	}

	/**
	 * 跳转修改角色页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id") Long id) {
		model.addAttribute("role", roleService.find(id));
		return "/security/role/edit";
	}

	/**
	 * 保存角色
	 * 
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Map<String, Object> save(@ModelAttribute("role") Role role,
			Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (role.getId() != null) {
				// 验证角色是否存在
				boolean isExists = roleService.exists(role.getName(),
						role.getId());

				if (isExists) {
					model.addAttribute(MODEL_MSG, "角色名称“" + role.getName()
							+ "”已存在，请更换角色名称！");
					resultMap.put("status", false);
					resultMap.put("err", "角色名称“" + role.getName()
							+ "”已存在，请更换角色名称！");
				}
			}

			roleService.save(role);
			resultMap.put("status", true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultMap.put("status", false);
			resultMap.put("err", "保存角色“" + role.getName() + "”失败，您可以稍后再试！");
		}
		return resultMap;
	}

	/**
	 * 删除一个角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@PathParam("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 验证角色是否存在
			Role role = roleService.find(id);
			if (role == null) {
				result.put(MODEL_MSG, "未找到要删除的角色");
			} else {
				roleService.remove(role);
				result.put(MODEL_MSG, "success");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put(ERROR_MSG, "error");
			result.put("msg", e.getMessage());
		}
		return result;
	}

}
