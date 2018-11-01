package com.bishe.core.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bishe.core.dao.jpa.BaseJPADao;
import com.bishe.core.dao.jpa.Page;
import com.bishe.core.dao.jpa.search.SearchFilter;
import com.bishe.core.entity.BaseEntity;
import com.bishe.core.utils.GenericsUtil;

public class BaseGenericsService<T extends BaseEntity> implements
		BaseService<T> {

	protected Class<T> entityClass;

	@Autowired
	protected BaseJPADao baseDao;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public List<T> findAll() {
		return query("from " + getEntityClass().getSimpleName(),
				new HashMap<String, Object>());
	}

	/**
	 * @see Manager#find(java.io.Serializable)
	 */
	@Override
	public T find(Serializable id) {
		return baseDao.find(getEntityClass(), id);
	}

	/**
	 * 保存数据，本方法声明事务，在使用时应注意<br>
	 * 注意事项：在调用本方法保存数据时应确保外层方法没有声明事务，否则无法保证事务完整性。
	 */
	@Override
	@Transactional
	public void save(T entity) {
		baseDao.save(entity);
	}

	/**
	 * 删除数据，本方法声明事务，在使用时应注意<br>
	 * 注意事项：在调用本方法保存数据时应确保外层方法没有声明事务，否则无法保证事务完整性。
	 */
	@Override
	@Transactional
	public void remove(T entity) {
		baseDao.remove(entity);
	}

	/**
	 * 获取全部
	 * 
	 * @return
	 */
	public List<T> getAll() {
		String jql = "from " + getEntityClass().getSimpleName();
		return query(jql, null);
	}

	/**
	 * @see Manager#query(String, Object...)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> query(String jql, Map<String, Object> args) {
		return (List<T>) baseDao.query(jql, args);
	}

	@Override
	public int executeUpdate(String queryString, Map<String, Object> values) {
		return baseDao.executeUpdate(queryString, values);
	}

	/**
	 * @see Manager#find(Serializable)
	 */
	@Override
	public T find(String jql, Map<String, Object> args) {
		List<T> result = query(jql, args);
		return (result.isEmpty()) ? null : result.get(0);
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (entityClass == null) {
			entityClass = GenericsUtil.getGenericClass(getClass());
		}
		return entityClass;
	}

	@Override
	public Page pageQuery(Page page, String jql, Map<String, Object> args) {
		return baseDao.pageQuery(page, jql, args);
	}

	public Page pageQuery(Page page, List<SearchFilter> filters) {
		page.setTotalpage(page.getPages());
		StringBuilder query = new StringBuilder(128);
		query.append("from ").append(getEntityClass().getSimpleName())
				.append(" where ");
		int whereInt = query.length();
		Map<String, Object> args = new HashMap<String, Object>();
		for (SearchFilter filter : filters) {
			query.append(filter.getSearchStr()).append(" and ");
			args.put(filter.paramName, filter.value);
		}
		if (query.length() == whereInt) {// 相等代表无where条件
			query.delete(query.length() - 6, query.length());// 去掉[where ]
		} else {
			query.delete(query.length() - 4, query.length());// 去掉[and ]
		}
		query.append(page.getOrderStr());
		return pageQuery(page, query.toString(), args);
	}

	
	
	// 自定义 分页
	public Page pageQuery(Page page, String sql, Object[] arrs) {
		page.setRows(conditionCount(sql, arrs));
		page.setTotalpage(page.getPages());
		int startIndex = page.getStartIndex();
		int endIndex = page.getStartIndex() + page.getPageSize();
		sql = "select * from (" + sql + ") tempTable LIMIT " + startIndex + "," + endIndex;
		RowMapper<T> rowMapper = new BeanPropertyRowMapper<>(getEntityClass());
		List<T> xxxxList = jdbcTemplate.query(sql, arrs, rowMapper);
		page.setData(xxxxList);
		return page;
	}

	private int conditionCount(String sql, Object[] arrs) {
		RowMapper<T> rowMapper = new BeanPropertyRowMapper<>(getEntityClass());
		List<T> xxxxList = jdbcTemplate.query(sql, arrs, rowMapper);
		return xxxxList.size();
	}
	
	
	
	
	
	/**
	 * 查询是否有重复值
	 * 
	 * @param obj
	 * @param names
	 * @return
	 */
	public boolean exists(T t, String... names) {
		return baseDao.exists(t, names);
	}

	/**
	 * 清理jpa缓存
	 */
	public void clear() {
		baseDao.getEm().clear();
	}

	public BaseJPADao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseJPADao baseDao) {
		this.baseDao = baseDao;
	}

}
