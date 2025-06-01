package com.neuedu.service.impl;

import com.neuedu.mapper.AdminsMapper;
import com.neuedu.pojo.*;
import com.neuedu.service.AdminsService;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminsServiceImpl implements AdminsService {

    @Autowired
    private AdminsMapper adminsMapper;

    @Override
    public Admins findByName(String username) {

        return adminsMapper.findByName(username);
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        adminsMapper.addAdmins(username, md5String);
    }

    @Override
    public Admins find(String username, String password) {
        return adminsMapper.find(username, password);
    }

    @Override
    public void updatePwd(String password) {
        String md5String = Md5Util.getMD5String(password);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        adminsMapper.updatePwd(md5String, id);
    }

    @Override
    public void update(Admins admins) {
        adminsMapper.update(admins);
    }

    @Override
    public List<Brands> findBrandsInfo() {

        return adminsMapper.findBrandsInfo();
    }

    @Override
    public List<Merchants> findMerchantsInfo() {

        return adminsMapper.findMerchantsInfo();
    }

    @Override
    public void deleteMerchants(Integer merchantsId) {

        adminsMapper.deleteMerchants(merchantsId);
    }

    @Override
    public void deleteBrands(Integer brandsId) {

        adminsMapper.deleteBrands(brandsId);
    }

    @Override
    public List<Object> findAllUser() {

        List<Brands> list1 = adminsMapper.findBrandsInfo();
        List<Merchants> list2 = adminsMapper.findMerchantsInfo();
        List<Object> list = new ArrayList<>();
        for(Brands brands : list1) {
            list.add(brands);
        }
        for(Merchants merchants : list2) {
            list.add(merchants);
        }
        return list;

    }
}
