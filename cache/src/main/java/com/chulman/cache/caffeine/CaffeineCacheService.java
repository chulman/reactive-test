package com.chulman.cache.caffeine;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@CacheConfig(cacheNames = {"calculator"})
@Service
public class CaffeineCacheService {

    @Cacheable(cacheNames = "calculator")
    public int getWithCache(int count) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return IntStream.range(0, count).sum();
    }

    public int getWithoutCache() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return IntStream.range(0, 1000000).sum();
    }

    @CacheEvict(cacheNames = "calculator")
    public int deleteCache(int count) {
        return count;
    }

}
