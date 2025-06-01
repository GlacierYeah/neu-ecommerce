package com.neuedu.service;

import com.neuedu.pojo.User;

public interface UserService {

    User findByUserName(String username, Integer role);

    void register(String username, String password, Integer role);

}
