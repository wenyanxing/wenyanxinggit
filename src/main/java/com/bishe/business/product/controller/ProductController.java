package com.bishe.business.product.controller;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bishe.business.catalog.entity.Catalog;
import com.bishe.business.catalog.service.CatalogService;
import com.bishe.business.product.entity.Product;
import com.bishe.business.product.service.ProductService;
import com.bishe.core.Constants;
import com.bishe.core.ServiceException;
import com.bishe.core.controller.BaseController;
import com.bishe.core.dao.jpa.Page;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService pService;
	@Autowired
	private CatalogService cService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		List<Catalog> cList = cService.getAll();
		model.addAttribute("cList", cList);
		model.addAttribute("product", new Product());
		return "/admin/product/edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		List<Catalog> cList = cService.getAll();
		model.addAttribute("cList", cList);
		model.addAttribute("product", pService.find(id));
		return "/admin/product/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") Product product, Model model,
			HttpServletRequest request) {
		Long catid = Long.parseLong(request.getParameter("catid"));
		Catalog catalog = cService.find(catid);
		try {
			if (StringUtils.isNotBlank(product.getRecover())) {
				product.setRecover("http://localhost:8080"
						+ product.getRecover());
			} else {
				product.setRecover(request.getParameter("co2"));
			}
			product.setCatalog(catalog);
			pService.save(product);
			return "redirect:/product/list";
		} catch (ServiceException se) {
			model.addAttribute(MODEL_MSG, se.getMessage());
			return "/admin/product/edit";
		}
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		Product product = pService.find(id);
		if (product != null) {
			pService.remove(product);
		}
		return "redirect:/product/list";
	}

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request) {
		List<Catalog> cList = cService.findAll();
		request.setAttribute("cList", cList);
		Page page = buildPage(request, Constants.DESC, "catalog");
		page = pService.pageQuery(page, buildFilters(request));
		model.addAttribute("page", page);
		return "/admin/product/list";
	}

	// 更新促销状态
	@RequestMapping(value = "updateState/{id}/{state}", method = RequestMethod.GET)
	public String updateState(@PathVariable("id") Long id,
			@PathVariable("state") Integer state, HttpServletRequest request) {
		Product product = pService.find(id);
		product.setState(state);
		pService.save(product);
		return "redirect:/product/list";
	}

	@ModelAttribute
	public void preModelAttribute(
			@RequestParam(value = "id", required = false) Long id, Model model) {
		if (id != null) {
			Product product = pService.find(id);
			model.addAttribute("product", product);
		}
	}

	// --------------------------前台-----------------------------
	// 全部商品页面
	@RequestMapping("/index")
	public String productpage(HttpServletRequest request) {
		String cid = request.getParameter("cid");
		List<Catalog> cList = cService.findAll();
		request.setAttribute("cList", cList);
		Page page = new Page(getPageNo(request), 12);
		page = pService.getEntityPage(page, cid, 9);
		request.setAttribute("ppList", page.getData());
		return "/front/products";
	}

	// 商品详情页
	@RequestMapping("/productdetail")
	public String productdetail(HttpServletRequest request) {
		List<Catalog> cList = cService.findAll();
		request.setAttribute("cList", cList);
		Long id = Long.parseLong(request.getParameter("id"));
		List<Product> pList1 = pService.getByState(1, "");// 热卖
		request.setAttribute("pList1", pList1);
		request.setAttribute("product", pService.find(id));
		return "/front/productdetails";
	}

}
