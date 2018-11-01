package com.bishe.base.security.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.bishe.base.security.user.entity.User;
import com.bishe.core.Constants;
import com.bishe.core.ServiceException;
import com.bishe.core.service.BaseGenericsService;

@Service
public class UserService extends BaseGenericsService<User> {

	/**
	 * 保存用户，新增用户会进行密码加密操作，用户默认状态为可用
	 */
	@Transactional
	public void save(User user) {
		if (exists(user, "loginId")) {
			throw new ServiceException("用户登录账号{0}已经存在", user.getLoginId());
		}
		if (user.getId() == null) {
			user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
			user.setUsable(Constants.YES);
			user.setCreateTime(new Date());
		}
		super.save(user);
	}

	public User userLogin(String loginId, String pwd) {
		String hql = "from User u where u.loginId = :loginId and u.pwd = :pwd and u.usable = :usable";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("loginId", loginId);
		args.put("pwd", pwd);
		args.put("usable", Constants.YES);
		return find(hql, args);
	}

	@Transactional
	public void initAdminUser() {
		User user = getAdmin();
		if (user == null) {
			user = new User();
			user.setLoginId("admin");
			user.setName("管理员");
			user.setPwd("manager");
			this.save(user);
		}
	}

	/**
	 * 暂无管理员用户
	 * 
	 * @return
	 */
	public User getAdmin() {
		String jql = "from User u where u.loginId like :loginId";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("loginId", MatchMode.ANYWHERE.toMatchString("admin"));
		return find(jql, args);
	}

	@Transactional
	public void updatePws(User u, String oldPwd, String newPwd) {
		u = find(u.getId());
		if (u == null) {
			return;
		}
		if (DigestUtils.md5DigestAsHex(oldPwd.getBytes()).equals(u.getPwd())) {
			u.setPwd(DigestUtils.md5DigestAsHex(newPwd.getBytes()));
			save(u);
		} else {
			throw new ServiceException("原密码错误");
		}
	}

	public User findByUsername(String loginId) {
		String hql = "from User where loginId = :loginId";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginId", loginId);
		List<User> list = super.query(hql, paramMap);
		return (list.size() > 0) ? list.get(0) : null;
	}
}
