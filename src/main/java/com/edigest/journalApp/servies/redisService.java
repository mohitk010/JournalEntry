package com.edigest.journalApp.servies;

import com.edigest.journalApp.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class redisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String city, Class<T> entityClass){
        try{
            Object o = redisTemplate.opsForValue().get(city);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception " +e);
            return null;
        }
    }

    public void set(String City, Object o, long ttl){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(City, jsonValue, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception " +e);
        }
    }
}
