package com.bishe.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.bishe.core.Constants;
import com.bishe.core.controller.support.DateConvert;
import com.bishe.core.dao.jpa.Page;
import com.bishe.core.dao.jpa.search.SearchFilter;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected static String MODEL_MSG = "model_msg";
	
	protected static String ERROR_MSG = "error_msg";
	
	protected static String REDIRECT_MSG = "redirect_msg";

	/**
	 * 查询参数前缀：Search_
	 */
	private static String PRE_FIX = "Search_";
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  
	                              ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateConvert());  
	} 
	
	/**
	 * 构建包含排序信息的page对象
	 * @param request
	 * @return
	 */
	protected Page buildPage(ServletRequest request, String direction, String sortProperties){
		Page page = new Page(getPageNo(request), getPageSize(request));
		page.addOrders(direction, sortProperties);
		return page;
	}
	
	protected Page buildPage(ServletRequest request, int custPageSize, String direction, String sortProperties){
		Page page = new Page(getPageNo(request), getPageSize(request, custPageSize));
		page.addOrders(direction, sortProperties);
		return page;
	}

	protected int getPageNo(ServletRequest request) {
		String pageNumber = request.getParameter("pageNumber");
		if (StringUtils.isNumeric(pageNumber)) {
			return Integer.parseInt(pageNumber);
		} else {
			return 1;
		}
	}
	
	protected int getPageSize(ServletRequest request) {
		return getPageSize(request, 0);
	}
	
	protected int getPageSize(ServletRequest request, int custPageSize) {
		String pageSize = request.getParameter("pageSize");
		if (pageSize != null && StringUtils.isNumeric(pageSize)) {
			return Integer.parseInt(pageSize);
		} else {
			if (custPageSize > 0){
				return custPageSize;
			}else{
				return Constants.DEF_PAGE_SIZE;
			}
		}
	}

	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	protected Map<String, Object> getSearchParameters(ServletRequest request) {

		Validate.notNull(request, "Request must not be null");

		Map<String, Object> params = new TreeMap<String, Object>();
		// 获得request中所有的参数名称
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if ("".equals(PRE_FIX) || paramName.startsWith(PRE_FIX)) {
				String unPrefixed = paramName.substring(PRE_FIX.length());
				String values = request.getParameter(paramName);
				if (StringUtils.isNotBlank(values)) {
					params.put(unPrefixed, values);
				}
			}
		}
		return params;
	}
	
	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	protected List<SearchFilter> buildFilters(ServletRequest request) {
		Map<String, Object> searchParams = getSearchParameters(request);
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		for (Entry<String, Object> entry : searchParams.entrySet()) {// 遍历参数集合

			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value) || !isEffectiveFileName(key)) {// 过滤掉空值和非法filedName
				continue;
			}

			// 拆分fieldName与operator
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			String operator = names[0];

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.add(filter);
		}

		return filters;
	}
	
	/**
	 * 验证查询属性字符合法，过滤特殊字符
	 * 
	 * @param str
	 * @return
	 */
	private boolean isEffectiveFileName(String str) {
		if (StringUtils.isNotBlank(str)) {
			String regex = "[\\w.]+";
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(str);
			return match.matches();
		}
		return false;
	}
	
	/**
	 * 返回成功信息
	 * 
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> success(String ...msg) {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("status", true);
		message.put(MODEL_MSG, msg);
		return message;
	}

	/**
	 * 返回失败信息
	 * 
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> error(String msg) {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put(MODEL_MSG, msg);
		message.put("status", false);
		return message;
	}

}
