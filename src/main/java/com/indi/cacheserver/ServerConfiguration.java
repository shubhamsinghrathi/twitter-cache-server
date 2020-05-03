package com.indi.cacheserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.indi.cacheserver.model.User;

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
	RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemp = new RedisTemplate<>();
		redisTemp.setConnectionFactory(jedisConnectionFactory());
		return redisTemp;
	}

}
