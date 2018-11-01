package com.bishe.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 **/
@Component
public class RedisUtil {

	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	private static RedisUtil redisUtil;
	
	@Resource(name = "genericRedisTemplate")
	private RedisTemplate<String, String> genericRedisTemplate;
	
	@PostConstruct
	public void init() {
		redisUtil = this;
		redisUtil.genericRedisTemplate = this.genericRedisTemplate;
	}

	/**
	 * 缓存value操作
	 *
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public static boolean save(String key, String value, long time) {
		try {
			ValueOperations<String, String> valueOps = redisUtil.genericRedisTemplate.opsForValue();
			valueOps.set(key, value);
			if (time > 0) {
				redisUtil.genericRedisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			logger.debug("缓存成功, value[" + value + "]");
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
		}
		return false;
	}

	/**
	 * 缓存value操作
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean save(String key, String value) {
		return save(key, value, -1);
	}

	/**
	 * 判断缓存是否存在
	 *
	 * @param key
	 * @return
	 */
	public static boolean hasKey(String key) {
		try {
			return redisUtil.genericRedisTemplate.hasKey(key);
		} catch (Throwable t) {
			logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
		}
		return false;
	}

	/**
	 * 获取缓存
	 *
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		try {
			ValueOperations<String, String> valueOps = redisUtil.genericRedisTemplate.opsForValue();
			String value = valueOps.get(key);
			logger.debug("获取缓存成功key[" + key + "], value[" + value + "])");
			return value;
		} catch (Throwable t) {
			logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
		}
		return null;
	}

	/**
	 * 移除缓存
	 *
	 * @param key
	 * @return
	 */
	public static boolean remove(String key) {
		try {
			redisUtil.genericRedisTemplate.delete(key);
			logger.debug("获取缓存成功key[" + key + "]");
			return true;
		} catch (Throwable t) {
			logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
		}
		return false;
	}

	/**
	 * 缓存set操作
	 *
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public static boolean saveSet(String key, String value, long time) {
		try {
			SetOperations<String, String> valueOps = redisUtil.genericRedisTemplate.opsForSet();
			valueOps.add(key, value);
			if (time > 0) {
				redisUtil.genericRedisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			logger.debug("缓存[" + key + "]成功, value[" + value + "]");
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
		}
		return false;
	}

	/**
	 * 缓存set
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean saveSet(String key, String value) {
		return saveSet(key, value, -1);
	}

	/**
	 * 缓存set
	 *
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public static boolean saveSet(String key, Set<String> value, long time) {
		try {
			SetOperations<String, String> setOps = redisUtil.genericRedisTemplate.opsForSet();
			setOps.add(key, value.toArray(new String[value.size()]));
			if (time > 0) {
				redisUtil.genericRedisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			logger.debug("缓存[" + key + "]成功, value[" + value + "]");
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
		}
		return false;
	}

	/**
	 * 缓存set
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean saveSet(String key, Set<String> value) {
		return saveSet(key, value, -1);
	}

	/**
	 * 获取缓存set数据
	 *
	 * @param key
	 * @return
	 */
	public static Set<String> getSet(String key) {
		try {
			SetOperations<String, String> setOps = redisUtil.genericRedisTemplate.opsForSet();
			Set<String> set = setOps.members(key);
			logger.debug("获取set缓存成功key[" + key + ", value[" + set + "]");
			return set;
		} catch (Throwable t) {
			logger.error("获取set缓存失败key[" + key + ", error[" + t + "]");
		}
		return null;
	}

	/**
	 * list缓存
	 *
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public static boolean saveList(String key, String value, long time) {
		try {
			ListOperations<String, String> listOps = redisUtil.genericRedisTemplate.opsForList();
			listOps.rightPush(key, value);
			if (time > 0) {
				redisUtil.genericRedisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			logger.debug("缓存[" + key + "]成功, value[" + value + "]");
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
		}
		return false;
	}

	/**
	 * 缓存list
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean saveList(String key, String value) {
		return saveList(key, value, -1);
	}

	/**
	 * 缓存list
	 *
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public static boolean saveList(String key, List<String> value, long time) {
		try {
			ListOperations<String, String> listOps = redisUtil.genericRedisTemplate.opsForList();
			listOps.rightPushAll(key, value);
			if (time > 0) {
				redisUtil.genericRedisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			logger.debug("缓存[" + key + "]成功, value[" + value + "]");
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
		}
		return false;
	}

	/**
	 * 缓存list
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean saveList(String key, List<String> value) {
		return saveList(key, value, -1);
	}

	/**
	 * 获取list缓存
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getList(String key, long start, long end) {
		try {
			ListOperations<String, String> listOps = redisUtil.genericRedisTemplate.opsForList();
			List<String> list = listOps.range(key, start, end);
			logger.debug("获取list缓存成功key[" + key + ", value[" + list + "]");
			return list;
		} catch (Throwable t) {
			logger.error("获取list缓存失败key[" + key + ", error[" + t + "]");
		}
		return null;
	}

	/**
	 * 获取总条数, 可用于分页
	 *
	 * @param key
	 * @return
	 */
	public static long getListSize(String key) {
		try {
			ListOperations<String, String> listOps = redisUtil.genericRedisTemplate.opsForList();
			return listOps.size(key);
		} catch (Throwable t) {
			logger.error("获取list长度失败key[" + key + "], error[" + t + "]");
		}
		return 0;
	}

	/**
	 * 获取总条数, 可用于分页
	 *
	 * @param listOps
	 * @param key
	 * @return
	 */
	public static long getListSize(ListOperations<String, String> listOps, String key) {
		try {
			return listOps.size(key);
		} catch (Throwable t) {
			logger.error("获取list长度失败key[" + key + "], error[" + t + "]");
		}
		return 0;
	}

	/**
	 * 移除list缓存
	 *
	 * @param key
	 * @return
	 */
	public static boolean removeOneOfList(String key) {
		try {
			ListOperations<String, String> listOps = redisUtil.genericRedisTemplate.opsForList();
			listOps.rightPop(key);
			return true;
		} catch (Throwable t) {
			logger.error("移除list缓存失败key[" + key + ", error[" + t + "]");
		}
		return false;
	}
}
