package com.bishe.core.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bishe.core.Constants;
/**
 * 分页对象.包含数据及分页信息.
 * 
 * @author Sam
 */

@SuppressWarnings({ "serial", "rawtypes" })
public final class Page implements Serializable {

	/** 第一页 */
	public static final int FIRST_PAGE_INDEX = 1;

	/** 空Page对象 */
	public static final Page EMPTY_PAGE = new Page();

	/** 当前页第一条数据的位置,从0开始 */
	private int startIndex = 0;

	/** 每页的记录数 */
	private int pageSize = Constants.DEF_PAGE_SIZE;

	/** 当前页中存放的记录 */
	private List data;

	/** 总记录数 */
	private int rows = 0;

	private int pageNumber = 1;

	private int totalpage;

	private int startPage;
	private int endPage;

	private String direction = Constants.DESC;

	private List<String> sortProperties = null;

	/**
	 * 构造方法，只构造空页
	 */
	public Page() {
		data = Collections.emptyList();
	}

	/**
	 * 构造方法，通常用于执行分页查询前构建分页信息。常见的用法：<br>
	 * 
	 * @param startIndex
	 *            起始行索引，从0开始。
	 * @param pageSize
	 *            本页容量。
	 */
	public Page(int pageNumber, int pageSize) {
		this.startIndex = start(pageNumber, pageSize);
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}

	/**
	 * 取数据库中包含的总记录数
	 */
	public int getRows() {
		return this.rows;
	}

	/**
	 * 取总页数
	 */
	public int getPages() {
		if (rows % pageSize == 0) {
			return rows / pageSize;
		} else {
			return rows / pageSize + 1;
		}
	}

	/**
	 * 取每页数据容量
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页码,如果查询结果为空(Empty List),则总是返回1
	 * 
	 * @return 第一页是1，第二页是2...
	 */
	public int getPageNo() {
		return (startIndex / pageSize) + 1;
	}

	/**
	 * 是否有下一页
	 */
	public boolean getHasNextPage() {
		return this.getPageNo() < this.getPages();
	}

	/**
	 * 是否有上一页
	 */
	public boolean getHasPreviousPage() {
		return (this.getPageNo() > 1);
	}

	/**
	 * 获取任一页第一条数据的位置,startIndex从0开始
	 */
	public static int start(int pageNumber, int pageSize) {
		return (pageNumber - 1) * pageSize;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStartIndex() {
		if (startIndex < 0) {
			return 0;
		}
		return startIndex;
	}

	/**
	 * 添加排序属性
	 * 
	 * @param dir
	 *            排序方式，ASC/DESC
	 * @param sorts
	 *            排序的属性字段，以','分割
	 */
	public void addOrders(String dir, String sorts) {
		this.direction = dir;
		if (StringUtils.isNoneBlank(sorts)) {
			if (sortProperties == null) {
				sortProperties = new ArrayList<String>();
			}
			for (String sort : sorts.split(",")) {
				sortProperties.add(sort);
			}
		}
	}

	public String getOrderStr() {
		if (StringUtils.isBlank(direction)) {
			direction = Constants.DESC;
		}
		if (sortProperties != null && sortProperties.size() > 0) {
			StringBuilder order = new StringBuilder("order by ");
			for (String sort : sortProperties) {
				order.append(sort).append(" ").append(direction).append(", ");
			}
			order.delete(order.length() - 2, order.length());
			return order.toString();
		} else {
			return "";
		}
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
		// 设置显示的页数
		if (totalpage <= 9) {
			this.setStartPage(1);
			this.setEndPage(totalpage);
		} else {
			startPage = pageNumber - 4;
			endPage = pageNumber + 4;
			if (startPage < 1) {
				startPage = 1;
				endPage = 9;
			}
			if (endPage > totalpage) {
				endPage = totalpage;
				startPage = totalpage - 8;
			}
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
