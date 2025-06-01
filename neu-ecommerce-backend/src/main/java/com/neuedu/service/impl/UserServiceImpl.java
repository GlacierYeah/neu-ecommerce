package com.neuedu.service.impl;

import com.neuedu.mapper.UserMapper;
import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username, Integer role) {
        if(role == 1) {
            return userMapper.findByAdminsName(username);
        }else if(role == 2) {
            return userMapper.findByMerchantsName(username);
        }else {
            return userMapper.findByBrandsName(username);
        }
    }

    @Override
    public void register(String username, String password, Integer role) {

        String md5String = Md5Util.getMD5String(password);
        if(role == 1) {
            userMapper.addAdmins(username, md5String);
        }else if(role == 2) {
            userMapper.addMerchants(username, md5String);
        }else {
            userMapper.addBrands(username, md5String);
        }
    }


}
