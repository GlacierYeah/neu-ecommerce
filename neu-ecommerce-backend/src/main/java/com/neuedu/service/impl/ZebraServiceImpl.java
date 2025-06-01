package com.neuedu.service.impl;

import com.neuedu.mapper.ZebraMapper;
import com.neuedu.pojo.Zebra;
import com.neuedu.service.ZebraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZebraServiceImpl implements ZebraService {

    @Autowired
    private ZebraMapper zebraMapper;

    @Override
    public Zebra findByBrandId(Integer brandId) {

        return zebraMapper.findByBrandId(brandId);
    }
}
