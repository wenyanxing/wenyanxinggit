package com.bishe.redis.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.bishe.redis.entity.Member;
import com.bishe.redis.entity.RedisGeneratorDao;

public class MemberService extends RedisGeneratorDao<String, Member> {

	/**
	 * 添加对象
	 */
	public boolean add(Member member) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(member.getId());
				byte[] name = serializer.serialize(member.getNickname());
				return connection.setNX(key, name);
			}
		});
		return result;
	}

	public Member get(String keyId) {
		Member result = redisTemplate.execute(new RedisCallback<Member>() {
			public Member doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				String nickname = serializer.deserialize(value);
				return new Member(keyId, nickname);
			}
		});
		return result;
	}
}
