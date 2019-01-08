package com.chulm.reactive.rxjava.sample;

import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSearchService {

    @Autowired
    UserRepository repository;

    //Observable은 데이터를 끊임없이 가져오고 데이터를 필요로 하는 곳에 바로 보내주는 객체
    public Observable<User> getAllSampleUsers() {
        return Observable.fromArray(repository.getSampleUsers());
    }
}
