package com.neuedu.controller;

import com.neuedu.pojo.Brands;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Result;
import com.neuedu.service.BrandsService;
import com.neuedu.utils.CaptureConfig;
import com.neuedu.utils.JwtUtil;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:8081")
@Validated
@RestController
@RequestMapping("/brands")
public class BrandsController {

    @Autowired
    private BrandsService brandService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //查询所有的商品
    @GetMapping("/findProductInfo")
    public Result<Product> findProductInfo() {
        return brandService.findProductInfo();
    }

    //品牌方登录，记得把验证码部分的注释解开
    @PostMapping("/login")
    public Result login(String username, String password, String verificationCode, String key) {
        if(username == null || password == null || verificationCode == null || key == null) {
            return Result.error("缺少必要的参数");
        }
        String md5String = Md5Util.getMD5String(password);
        Brands brands = brandService.find(username, md5String);
        if(!verificationCode.toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
            return Result.error("验证码不正确");
        }
        if(brands == null) {
            return Result.error("用户名或密码不正确，请重新输入...");
        }else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", brands.getId());
            claims.put("username", brands.getUsername());
            claims.put("role", brands.getRole());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        }
    }

    //品牌方修改自己的密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        Brands brands = brandService.findByName(username);
        if(!brands.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码不正确");
        }
        if(!rePwd.equals(newPwd)) {
            return Result.error("两次输入的新密码不一致");
        }
        brandService.updatePwd(newPwd);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    //品牌方修改自己的个人信息
    @PutMapping("/update")
    public Result update(@RequestBody Brands brands) {
        brandService.update(brands);
        return Result.success();
    }

    //品牌方搜索自己的信息
    @GetMapping("/brandsInfo")
    public Result<Brands> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Brands brands = brandService.findByName((String)map.get("username"));
        return Result.success(brands);
    }


}
