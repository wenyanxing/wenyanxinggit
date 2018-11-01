package com.bishe.business.catalog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bishe.business.catalog.entity.Catalog;
import com.bishe.business.catalog.service.CatalogService;
import com.bishe.core.Constants;
import com.bishe.core.ServiceException;
import com.bishe.core.controller.BaseController;
import com.bishe.core.dao.jpa.Page;


@Controller
@RequestMapping("/catalog")
public class CatalogController extends BaseController {

	@Autowired
	private CatalogService catalogService;

	/**
	 * 显示栏目列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(Model model, ServletRequest request) {
		Page page = new Page(getPageNo(request), Constants.DEF_PAGE_SIZE);
		page = catalogService.getEntityPage(page);
		model.addAttribute("page", page);
		return "/admin/catalog/list";
	}

	/**
	 * 新建栏目
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("catalog", new Catalog());
		return "/admin/catalog/edit";
	}

	/**
	 * 编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("catalog", catalogService.find(id));
		return "/admin/catalog/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("catalog") Catalog catalog,
			RedirectAttributes redirectAttributes, Model model) {
		try {
			catalogService.save(catalog);
			redirectAttributes.addFlashAttribute(REDIRECT_MSG,
					catalog.getName() + "保存成功");
			return "redirect:/catalog/list";
		} catch (ServiceException se) {
			model.addAttribute(MODEL_MSG, se.getMessage());
			return "/admin/catalog/edit";
		}
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		Catalog catalog = catalogService.find(id);
		if (catalog != null) {
			catalogService.remove(catalog);
			redirectAttributes.addFlashAttribute(REDIRECT_MSG,
					catalog.getName() + "删除成功");
		} else {
			redirectAttributes.addFlashAttribute(REDIRECT_MSG, "未找到要删除的内容");
		}
		return "redirect:/catalog/list";
	}

	@ModelAttribute
	public void preModelAttribute(
			@RequestParam(value = "id", required = false) Long id, Model model) {
		if (id != null) {
			Catalog catalog = catalogService.find(id);
			model.addAttribute("catalog", catalog);
		}
	}

	/**
	 * 获得所有栏目
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "catalogList")
	@ResponseBody
	public Map<String, Object> catalogList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Catalog> listValue = new ArrayList<Catalog>();
		listValue = catalogService.getCatalog();
		resultMap.put("listValue", listValue);
		return resultMap;
	}
}
