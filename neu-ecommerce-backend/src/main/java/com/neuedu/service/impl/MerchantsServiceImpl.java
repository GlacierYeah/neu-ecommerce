package com.neuedu.service.impl;

import com.neuedu.mapper.MerchantsMapper;
import com.neuedu.pojo.Merchants;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.Store;
import com.neuedu.service.MerchantsService;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MerchantsServiceImpl implements MerchantsService {

    @Autowired
    private MerchantsMapper merchantsMapper;

    @Override
    public Merchants findByName(String username) {

        return merchantsMapper.findByName(username);
    }

    @Override
    public void register(String username, String password) {

        String md5String = Md5Util.getMD5String(password);
        merchantsMapper.addMerchants(username, md5String);
    }

    @Override
    public Merchants find(String username, String password) {

        return merchantsMapper.find(username, password);
    }

    @Override
    public void updatePwd(String password) {
        String md5String = Md5Util.getMD5String(password);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        merchantsMapper.updatePwd(md5String, id);
    }

    @Override
    public void update(Merchants merchants) {
        merchantsMapper.update(merchants);
    }

    @Override
    public Store findStoreById(Integer id) {

        return merchantsMapper.findStoreById(id);
    }
}
