package com.indi.cacheserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.indi.cacheserver.model.RedisUser;
import com.indi.cacheserver.model.TimelineTweet;

@Configuration
@Component
public class ServerConfiguration {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("127.0.0.1");
	    configuration.setPort(6379);
		return new JedisConnectionFactory(configuration);
	}
	
	@Bean
	RedisTemplate<String, RedisUser> redisTemplate() {
		RedisTemplate<String, RedisUser> redisTemp = new RedisTemplate<>();
		redisTemp.setConnectionFactory(jedisConnectionFactory());
		return redisTemp;
	}
	
	@Bean
	RedisTemplate<String, TimelineTweet> redisTweetTemplate() {
		RedisTemplate<String, TimelineTweet> redisTemp = new RedisTemplate<String, TimelineTweet>();
		redisTemp.setConnectionFactory(jedisConnectionFactory());
		return redisTemp;
	}
	
	@Bean
	RedisTemplate<Integer, Integer> redisIntListTemplate() {
		RedisTemplate<Integer, Integer> redisTemp = new RedisTemplate<Integer, Integer>();
		redisTemp.setConnectionFactory(jedisConnectionFactory());
		return redisTemp;
	}

}
