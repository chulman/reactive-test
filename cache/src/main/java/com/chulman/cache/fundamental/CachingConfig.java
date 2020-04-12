package com.chulman.cache.fundamental;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 참고
 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/cache/annotation/CachingConfigurer.html
 */
@Configuration
//declaratively enabled by simply annotation
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("addresses");
    }
}