package com.chulman.cache.caffeine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaffeineCacheController {

    private final static Logger logger = LoggerFactory.getLogger(CaffeineCacheController.class);

    @Autowired
    CacheManager cacheManager;

    @Autowired
    public CaffeineCacheService caffeineCacheService;

    @GetMapping("/caffeine/get")
    public int getNumberWithOutCache() throws Exception{
        long start = System.currentTimeMillis();
        int calculation = caffeineCacheService.getWithoutCache();
        System.err.println("spend time = " + (System.currentTimeMillis() - start));
        return calculation;
    }

    @GetMapping("/caffeine/get/cache")
    public int getNumberWithCache() throws Exception{
        long start = System.currentTimeMillis();
        int calculation = caffeineCacheService.getWithCache(1000000);
        System.err.println("spend time = " + (System.currentTimeMillis() - start));
        CaffeineCache cache = (CaffeineCache)cacheManager.getCache("calculator");
        logger.info("stat => {}", cache.getNativeCache().stats());
        return calculation;
    }

    @GetMapping("/caffeine/delete/cache")
    public int deleteCache() throws Exception{
        int calculation = caffeineCacheService.deleteCache(1000000);
        return calculation;
    }
}
