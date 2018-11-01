package com.bishe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bishe.base.security.user.entity.User;
import com.bishe.core.controller.BaseController;
import com.bishe.core.dao.jpa.Page;
import com.bishe.base.security.user.service.UserService;
import com.bishe.business.article.service.ArticleService;
import com.bishe.business.product.service.ProductService;

@Controller
public class MainController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	private ProductService pService;
	@Autowired
	private ArticleService aService;

	// ----------后台--------------------start
	@RequestMapping("/adminLogin")
	public String adminLogin(HttpServletRequest request) {
		return "/admin/login";
	}

	@RequestMapping("/main")
	public String main(User user, Model model, HttpServletRequest request) {
		User cuser = userService.userLogin(user.getLoginId(),
				DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
		if (cuser != null) {
			request.getSession().setAttribute("user_admin", cuser);
			return "/admin/main";
		} else {
			return "/admin/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user_admin");
		return "/admin/login";
	}

	// ----------后台--------------------end

	// ----------前台--------------------start
	@RequestMapping("/index")
	public String homePage(HttpServletRequest request) {
		// 热卖商品10个滚动
		Page rmpage = new Page(1, 10);
		rmpage = pService.getEntityPage(rmpage, "", 1);
		request.setAttribute("rmList", rmpage.getData());

		// 新品上架8个平铺
		Page xppage = new Page(1, 8);
		xppage = pService.getEntityPage(xppage, "", 0);
		request.setAttribute("xpList", xppage.getData());

		// 公告9个
		Page artp = new Page(1, 9);
		artp = aService.getArticlePage(artp);
		request.setAttribute("aList", artp.getData());

		return "/front/index";
	}

	@RequestMapping("/contact")
	public String contact(HttpServletRequest request) {
		return "/front/contact";
	}
	// ----------前台--------------------end
}
