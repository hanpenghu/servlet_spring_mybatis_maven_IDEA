package com.hanhan.test;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    public String sayHello(String hello) {
        return hello;
    }
}