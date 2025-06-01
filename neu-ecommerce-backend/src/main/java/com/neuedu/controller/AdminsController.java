package com.neuedu.controller;

import com.neuedu.pojo.*;
import com.neuedu.service.AdminsService;
import com.neuedu.utils.CaptureConfig;
import com.neuedu.utils.JwtUtil;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.apache.ibatis.annotations.Delete;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //管理员登录
    @PostMapping("/login")
    public Result login(String username, String password, String verificationCode, String key) {
        if(username == null || password == null || verificationCode == null || key == null) {
            return Result.error("缺少必要的参数");
        }
        String md5String = Md5Util.getMD5String(password);
        Admins admins = adminsService.find(username, md5String);
        if(!verificationCode.toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
            return Result.error("验证码不正确");
        }
        if(admins == null) {
            return Result.error("用户名或密码不正确，请重新输入...");
        }else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", admins.getId());
            claims.put("username", admins.getUsername());
            claims.put("role", admins.getRole());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        }
    }

    //管理员修改自己的密码
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
        Admins admins = adminsService.findByName(username);
        if(!admins.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码不正确");
        }
        if(!rePwd.equals(newPwd)) {
            return Result.error("两次输入的新密码不一致");
        }
        adminsService.updatePwd(newPwd);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    //管理员修改自己的个人信息
    @PutMapping("/update")
    public Result update(@RequestBody Admins admins) {
        System.out.println(admins.getUsername());
        if(admins.getUsername() == null) {
            return Result.error("用户名不可以为空值");
        }
        adminsService.update(admins);
        return Result.success();
    }

    //查看管理员的用户信息
    @GetMapping("/adminsInfo")
    public Result<Admins> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Admins admins = adminsService.findByName((String)map.get("username"));
        return Result.success(admins);
    }

    //查看所有品牌方的人的信息
    @GetMapping("/findBrandsInfo")
    public Result<List<Brands>> findBrandsInfo() {
        List<Brands> list =  adminsService.findBrandsInfo();
        return Result.success(list);
    }

    //查看所有注册网店的人的信息
    @GetMapping("/findMerchantsInfo")
    public Result<List<Merchants>> findMerchantsInfo() {
        List<Merchants> list =  adminsService.findMerchantsInfo();
        return Result.success(list);
    }

    //删除网店的人的信息
    @DeleteMapping("/deleteMerchants")
    public Result deleteMerchants(Integer merchantsId) {
        if(merchantsId == null) {
            return Result.error("缺少必要的参数");
        }
        adminsService.deleteMerchants(merchantsId);
        return Result.success();
    }

    //删除品牌方的人的信息
    @DeleteMapping("/deleteBrands")
    public Result deleteBrands(Integer brandsId) {
        if(brandsId == null) {
            return Result.error("缺少必要的参数");
        }
        adminsService.deleteBrands(brandsId);
        return Result.success();
    }


//    @GetMapping("getMerchantsFunc")
//    public Result<List<Functions>> getMerchantsFunc(List<Functions> list, int role) {
//        List<Functions> newList = adminsService.getMerchantsFunc(list, role);
//        return Result.success(newList);
//    }

    //获取所有用户的信息
    @GetMapping("findAllUser")
    public Result<List<Object>> findAllUser() {
        List<Object> list = adminsService.findAllUser();
        return Result.success(list);
    }

}
