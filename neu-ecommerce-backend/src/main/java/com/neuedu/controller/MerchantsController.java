package com.neuedu.controller;

import com.neuedu.pojo.Merchants;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.Store;
import com.neuedu.service.MerchantsService;
import com.neuedu.service.ProductService;
import com.neuedu.service.StoreService;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:8081")
@Validated
@RestController
@RequestMapping("/merchants")
public class MerchantsController {

    @Autowired
    private MerchantsService merchantsService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    //商家登录
    @PostMapping("/login")
    public Result login(String username, String password, String verificationCode, String key) {
        if(username == null || password == null || verificationCode == null || key == null) {
            return Result.error("缺少必要的参数");
        }
        String md5String = Md5Util.getMD5String(password);
        Merchants merchants = merchantsService.find(username, md5String);
        if(!verificationCode.toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
            return Result.error("验证码不正确");
        }
        if(merchants == null) {
            return Result.error("用户名或密码不正确，请重新输入...");
        }else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", merchants.getId());
            claims.put("username", merchants.getUsername());
            claims.put("role", merchants.getRole());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        }
    }

    //商家修改自己的密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        //校验参数,validation不能满足需求
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        Merchants merchants = merchantsService.findByName(username);
        if(!merchants.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码不正确");
        }
        if(!rePwd.equals(newPwd)) {
            return Result.error("两次输入的新密码不一致");
        }
        merchantsService.updatePwd(newPwd);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    //商家修改自己的个人信息
    @PutMapping("/update")
    public Result update(@RequestBody @Validated Merchants merchants) {
        merchantsService.update(merchants);
        return Result.success();
    }

    //商家查看自己的个人信息
    @GetMapping("/merchantsInfo")
    public Result<Merchants> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Merchants merchants = merchantsService.findByName((String)map.get("username"));
        return Result.success(merchants);
    }

    //商家查询网店
    @GetMapping("/findStoreById")
    public Result<Store> findStoreById() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Store store = merchantsService.findStoreById(id);
        return Result.success(store);
    }


}
