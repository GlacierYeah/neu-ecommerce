package com.neuedu.service;

import com.neuedu.pojo.Admins;
import com.neuedu.pojo.Brands;
import com.neuedu.pojo.Functions;
import com.neuedu.pojo.Merchants;

import java.util.List;

public interface AdminsService {
    Admins findByName(String username);

    void register(String username, String password);

    Admins find(String username, String password);

    void updatePwd(String password);

    void update(Admins admins);

    List<Brands> findBrandsInfo();

    List<Merchants> findMerchantsInfo();

    void deleteMerchants(Integer merchantsId);

    void deleteBrands(Integer brandsId);

    List<Object> findAllUser();
}
