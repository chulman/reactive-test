package com.chulman.cache.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@ComponentScan(basePackages = "com.chulman.cache.caffeine")
public class CaffeineCacheConfig {
    private final static Logger logger = LoggerFactory.getLogger(CaffeineCacheController.class);
    // java config with refresh cache
//    public CacheManager cacheManager() {
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager("calculator");
//        cacheManager.setCaffeine(caffeineCacheBuilder());
//        return cacheManager;
//    }
//
//    Caffeine<Object, Object> caffeineCacheBuilder() {
//        return Caffeine.newBuilder()
//                .initialCapacity(100)
//                .maximumSize(500)
//                .refreshAfterWrite(10, TimeUnit.SECONDS)
//                .weakKeys()
//                .removalListener(new CacheRemovalListener())
//                .recordStats();
//    }
//
//    class CacheRemovalListener implements RemovalListener<Object, Object> {
//        @Override
//        public void onRemoval(Object key, Object value, RemovalCause cause) {
//            System.out.format("Removal listener called with key [%s], cause[%s], evicted [%s] %n",
//                    key , cause.toString(), cause.wasEvicted());
//        }
//    }

    @Bean
    public CacheLoader<Object, Object> cacheLoader() {

        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {

            @Override
            public Object load(Object key) throws Exception {
                logger.info("load key => {}", key);
                return key;
            }

            // Rewriting this method returns the oldValue value back to refresh the cache
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                logger.info("reload key => {} value => {}", key, oldValue);
                return oldValue;
            }
        };

        return cacheLoader;
    }
}
