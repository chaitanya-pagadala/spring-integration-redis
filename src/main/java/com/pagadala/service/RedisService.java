package com.pagadala.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ServiceActivator(inputChannel= "processFileChannel")
    public void test(Optional<String> payload) {

        logger.info("Payload {}", payload);

        Map<String, String> empJohnMap = new HashMap<>();
		empJohnMap.put("name", "Jshn");
		empJohnMap.put("age", "42");
		empJohnMap.put("id", "02");

		redisTemplate.opsForHash().putAll("emp:john", empJohnMap);
        assert true;
    }    
}