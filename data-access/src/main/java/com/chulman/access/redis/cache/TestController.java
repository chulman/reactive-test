package com.chulman.access.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    public RedisCacheService redisCacheService;

    @RequestMapping(value = "/cache/get/{key}", method = RequestMethod.GET)
    public boolean cacheSet(@PathVariable String key) {
        return redisCacheService.getValue(key);
    }
}
