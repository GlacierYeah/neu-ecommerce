package com.neuedu.service.impl;

import com.neuedu.mapper.FunctionsMapper;
import com.neuedu.pojo.Functions;
import com.neuedu.pojo.Result;
import com.neuedu.service.FunctionsService;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FunctionsServiceImpl implements FunctionsService {

    @Autowired
    private FunctionsMapper functionsMapper;

    @Override
    public List<Functions> show() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer role = (Integer) map.get("role");
        if(role == 1) {
            return functionsMapper.showAdmins();
        }else if(role == 2) {
            return functionsMapper.showMerchants();
        }else {
            return functionsMapper.showBrands();
        }
    }
}
