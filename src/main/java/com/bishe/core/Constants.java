package com.bishe.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.bishe.core.utils.ResBundleUtil;

public class Constants {

	/**
	 * 资源文件.
	 */
	public static final String KEY = "application";
	/** 管理员登陆后 Session Key */
	public static String USER_IN_SESSION = "user_in_session";

	/**
	 * 资源绑定对象
	 */
	public static final ResourceBundle RES = ResourceBundle.getBundle(KEY);

	/**
	 * 字符串表示的true，本项目中涉及true的调用皆使用此变量
	 */
	public static final String YES = "Y";

	/**
	 * 字符串表示的false，本项目中涉及false的调用皆使用此变量
	 */
	public static final String NO = "N";

	/**
	 * true or false map<br>
	 * 本项目中涉及true or false的调用皆使用此变量
	 */
	public static final Map<String, String> TF_MAP = new LinkedHashMap<String, String>();

	static {
		TF_MAP.put(YES, "是");
		TF_MAP.put(NO, "否");
	}

	public static final String DESC = "desc";

	public static final String ASC = "asc";

	/**
	 * 缺省的分页容量
	 */
	public static final int DEF_PAGE_SIZE = ResBundleUtil.getInt(RES,
			"pagesize");

	public static final String EQ = "EQ";
	public static final String LIKE = "LIKE";
	public static final String GT = "GT";
	public static final String LT = "LT";
	public static final String GTE = "GTE";
	public static final String LTE = "LTE";

	public static Map<String, String> OPT_MAPS = null;

	static {
		OPT_MAPS = new LinkedHashMap<String, String>();
		OPT_MAPS.put(EQ, " = ");
		OPT_MAPS.put(LIKE, " like ");
		OPT_MAPS.put(GT, " > ");
		OPT_MAPS.put(LT, " < ");
		OPT_MAPS.put(GTE, " >= ");
		OPT_MAPS.put(LTE, " <= ");
	}

	// -------------------session权限相关（start）-----------------------//
	/**
	 * 当前登录用户在request作用于或session中的key
	 */
	public static final String CURRENT_USER = "current_user";

	/**
	 * 当前登陆用户在request作用于或session中的key
	 */
	public static final String CURRENT_MEMBER = "current_member";

	/**
	 * 当前登录用户的所有菜单在session中的key
	 */
	public static final String USER_MENUS = "user_menus";
	
	/**
	 * 会话强制退出标识
	 */
	public static final String SESSION_FORCE_LOGOUT_KEY = "session_force_logout";
	// -------------------session权限相关（end）-----------------------//
}
