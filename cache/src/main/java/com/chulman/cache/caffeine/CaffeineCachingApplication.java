package com.chulman.cache.caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;

@EnableCaching
@Profile("caffeine")
@SpringBootApplication(exclude = { RedisAutoConfiguration.class })
public class CaffeineCachingApplication implements CommandLineRunner {

    @Autowired
    private CacheManager cacheManager;

    public static void main(String[] args) {
        SpringApplication.run(CaffeineCachingApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        System.err.println("cacheManager.getCacheNames(): " + cacheManager.getCacheNames());
    }
}
