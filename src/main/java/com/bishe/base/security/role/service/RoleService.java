package com.bishe.base.security.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.bishe.base.security.role.entity.Role;
import com.bishe.core.service.BaseGenericsService;
import com.bishe.core.utils.StringUtil;

/**
 * 角色业务层
 */
@Service
public class RoleService extends BaseGenericsService<Role> {

	/**
	 * 删除角色
	 * 
	 * @param role
	 */
	@Override
	public void remove(Role role) {
		// 删除角色菜单表中对应数据
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleId", role.getId());
		// 删除角色
		super.remove(role);
	}

	/**
	 * 根据ids获得roles
	 */
	public List<Role> findByIds(Long... ids) {
		Map<String, Object> paramMap = new HashMap<>();
		String hql = "from Role where id in (" + StringUtil.getSplitComma(ids)
				+ ")";
		return super.query(hql, paramMap);
	}

	/**
	 * 查询给定的角色名称是否存在
	 * 
	 * @param roleName
	 * @param id
	 * @return
	 */
	public boolean exists(String roleName, Long id) {
		Map<String, Object> args = new HashMap<String, Object>();
		String jql = "from Role role where role.name = :name and role.id != :id";
		args.put("name", roleName);
		args.put("id", id);
		Role role = super.find(jql, args);
		return role == null ? false : true;
	}
}
