package com.cavemen.dbscw.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
@Configuration
public class Config {

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
//    JedisConnectionFactory jedisConFactory
//        = new JedisConnectionFactory();
//    jedisConFactory.setHostName("localhost");
//    jedisConFactory.setPort(6379);
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

}
