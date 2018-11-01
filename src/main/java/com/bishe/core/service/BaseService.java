package com.bishe.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bishe.core.dao.jpa.Page;

public interface BaseService<T> {
	/**
	 * 返回全部
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 根据ID返回一个指定泛型的对象。
	 * 
	 * @param id
	 * @return
	 */
	T find(Serializable id);

	/**
	 * 保存或更新一个指定泛型T对象
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除一个指定泛型T的对象
	 * 
	 * @param entity
	 */
	void remove(T entity);

	/**
	 * 执行JQL查询，绑定一个符号为“?”的参数进行查询。返回{@link List} T 集合。
	 * 
	 * @param jql
	 * @param values
	 * @return
	 */
	List<T> query(String jql, Map<String, Object> args);

	/**
	 * 绑定参数，并执行JQL查询，返回一个单个的持久化实例。如果根据参数查询得到了多个结果， 则返回{@link List}
	 * 中的第一个元素，如果没有得到任何结果，返回null。
	 * 
	 * @param jql
	 * @param values
	 * @return
	 */
	T find(String jql, Map<String, Object> args);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param jql
	 * @param args
	 * @return
	 */
	public Page pageQuery(Page page, String jql, Map<String, Object> args);

	public int executeUpdate(String queryString, Map<String, Object> values);
}
