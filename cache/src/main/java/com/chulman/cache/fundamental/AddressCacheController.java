package com.chulman.cache.fundamental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressCacheController {

    @Autowired
    private AddressCacheService cacheService;

    @GetMapping("/get/address/{name}")
    public String getAddress(@PathVariable String name){
        Address address = cacheService.getAddress(name);
        // 같은 객체 인지 체크
        System.err.println(address.hashCode());
        return address.getName();
    }

    @GetMapping("/delete/address/{name}")
    public String deleteAddress(@PathVariable String name){
        return cacheService.deleteAddress(name);
    }

    @GetMapping("/put/address/{name}")
    public String putAddress(@PathVariable String name){
        Address address = cacheService.putAddress(name);
        System.err.println(address.hashCode());
        return address.getName();
    }
}
