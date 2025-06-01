package com.neuedu.service;

import com.neuedu.pojo.Brands;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Result;

public interface BrandsService {
    Result<Product> findProductInfo();

    Brands findByName(String username);

    void register(String username, String password);

    Brands find(String username, String password);

    void updatePwd(String password);

    void update(Brands brands);
}
