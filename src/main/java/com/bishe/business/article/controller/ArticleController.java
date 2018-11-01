package com.bishe.business.article.controller;

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

import com.bishe.core.Constants;
import com.bishe.core.ServiceException;
import com.bishe.core.controller.BaseController;
import com.bishe.core.dao.jpa.Page;
import com.bishe.business.article.entity.Article;
import com.bishe.business.article.service.ArticleService;

/**
 * 文章Controller
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("article", new Article());
		return "/admin/article/edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("article", articleService.find(id));
		return "/admin/article/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("article") Article article, Model model,
			HttpServletRequest request) {
		try {
			articleService.save(article);
			return "redirect:/article/list";
		} catch (ServiceException se) {
			model.addAttribute(MODEL_MSG, se.getMessage());
			return "/admin/article/edit";
		}
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		Article article = articleService.find(id);
		if (article != null) {
			articleService.remove(article);
			redirectAttributes.addFlashAttribute(REDIRECT_MSG,
					article.getTitle() + "删除成功");
		} else {
			redirectAttributes.addFlashAttribute(REDIRECT_MSG, "未找到要删除的内容");
		}
		return "redirect:/article/list";
	}

	@RequestMapping(value = "list")
	public String list(Model model, ServletRequest request) {
		Page page = new Page(getPageNo(request), Constants.DEF_PAGE_SIZE);
		page = articleService.getArticlePage(page);
		model.addAttribute("page", page);
		return "/admin/article/list";
	}

	@ModelAttribute
	public void preModelAttribute(
			@RequestParam(value = "id", required = false) Long id, Model model) {
		if (id != null) {
			Article article = articleService.find(id);
			model.addAttribute("article", article);
		}
	}

	// -----------------------------前台--------------------------------------
	// 动态资讯页面
	@RequestMapping("/newspage")
	public String newspage(HttpServletRequest request) {
		Page page = new Page(getPageNo(request), Constants.DEF_PAGE_SIZE);
		page = articleService.getArticlePage(page);
		request.setAttribute("page", page);
		return "/front/news/newspage";
	}

	// 资讯详情
	@RequestMapping("/newsdetail")
	public String newsdetail(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Article article = articleService.find(id);
		request.setAttribute("article", article);
		return "/front/news/newsdetail";
	}
}
