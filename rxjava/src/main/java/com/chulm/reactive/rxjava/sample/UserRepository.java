package com.chulm.reactive.rxjava.sample;

import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    public User[] getSampleUsers(){
        User[] array = {
                new User("a","choi","010"),
                new User("b","kim","010"),
                new User("c","park","010"),
                new User("d","lee","010"),
        };
        return array;
    }
}
