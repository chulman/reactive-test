package com.chulman.cache.fundamental;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddressCacheService {

    Map<String, Address> map = new HashMap<>();

//    caches=[addresses] | key='#name' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='#name.length() < 4' | unless='']
    @Cacheable(value = "addresses", key= "#name", condition = "#name.length() < 4")
    public Address getAddress(String name){
        System.err.println("called method =>" + "getAddress(" + name + ")");
        Address address = new Address(name);
        map.put(name, address);
        return address;
    }

    @CacheEvict(value = "addresses", allEntries = true)
    public String deleteAddress(String name){
        System.err.println("called method =>" + "deleteAddress(" + name + ")");
        if(map.containsKey(name)){
            map.remove(name);
        }
        return name;
    }

    //메소드 실행에 영향을 주지 않음. 즉 메소드는 항상 실행한다.
    @CachePut(value = "addresses", key= "#name", condition = "#name.length() < 4")
    public Address putAddress(String name){
        System.err.println("called method =>" + "putAddress(" + name + ")");
        Address address = new Address(name);
        map.put(name, address);
        return address;
    }
}


class Address {
    String name;

    public Address(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                '}';
    }
}