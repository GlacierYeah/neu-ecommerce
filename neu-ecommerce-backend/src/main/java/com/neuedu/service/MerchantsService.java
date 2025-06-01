package com.neuedu.service;

import com.neuedu.pojo.Merchants;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.Store;

public interface MerchantsService {
    Merchants findByName(String username);

    void register(String username, String password);

    Merchants find(String username, String password);

    void updatePwd(String password);

    void update(Merchants merchants);

    Store findStoreById(Integer id);
}
