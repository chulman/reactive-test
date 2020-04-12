package com.chulman.cache.redis.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class RedisCacheService {

    public static Map<String, String> map = null;

    @PostConstruct
    public void init(){
        map = new HashMap<>();
        map.put("test1", "value1");
        map.put("test2", "value2");
    }


//    https://yonguri.tistory.com/82

//    @Cacheble은 캐시가 있으면 캐시의 정보를 가져오고, 없으면 등록한다.
//    @CacehPut은 무조건 캐시에 저장한다.
//    @CacheEvict는 캐시를 삭제한다.

    /**
     * 1. value 지정된 값은 파라미터의 프리픽스가 된다.
     * 2. Cache TTL은 Configuration에 지정된 값으로 설정된다.
     */
    @Cacheable(value = "test::value")
//    @CachePut
    public boolean getValue(String key){
        System.err.println("----------");
        System.out.println("key: " + key );
        System.out.println("value: " + map.get(key) );
        System.err.println("----------");
        return map.containsKey(key);
    }
}
