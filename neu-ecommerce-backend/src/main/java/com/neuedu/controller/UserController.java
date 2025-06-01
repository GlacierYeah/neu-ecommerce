package com.neuedu.controller;

import com.neuedu.pojo.Result;
import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.utils.CaptureConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户的注册
    @PostMapping("/register")
    public Result register(String username, String password, String verificationCode, String key, Integer role) {
        User user = userService.findByUserName(username, role);
        if(user == null) {
            if(!verificationCode.toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
                return Result.error("验证码不正确");
            }
            userService.register(username, password, role);
            return Result.success();
        }else {
            return Result.error("用户名已被占用");
        }
    }

}
