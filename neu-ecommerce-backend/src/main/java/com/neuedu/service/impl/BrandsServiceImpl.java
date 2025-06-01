package com.neuedu.service.impl;

import com.neuedu.mapper.BrandsMapper;
import com.neuedu.pojo.Brands;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Result;
import com.neuedu.service.BrandsService;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BrandsServiceImpl implements BrandsService {

    @Autowired
    private BrandsMapper brandMapper;

    @Override
    public Result<Product> findProductInfo() {
        return brandMapper.findProductInfo();
    }

    @Override
    public Brands findByName(String username) {

        return brandMapper.findByName(username);
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        brandMapper.addBrands(username, md5String);
    }

    @Override
    public Brands find(String username, String password) {
        return brandMapper.find(username, password);
    }

    @Override
    public void updatePwd(String password) {
        String md5String = Md5Util.getMD5String(password);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        brandMapper.updatePwd(md5String, id);
    }

    @Override
    public void update(Brands brands) {
        brandMapper.update(brands);
    }
}
