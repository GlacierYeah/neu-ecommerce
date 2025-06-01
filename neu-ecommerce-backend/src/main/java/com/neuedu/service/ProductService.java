package com.neuedu.service;

import com.neuedu.pojo.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductService {


    Integer findQuantityById(Integer productId);

    void updateStockQuantity(Integer quantity, Integer productId);

    Product findByProductName(String productName);

    Integer findBrandIdByProductId(Integer productId);

    BigDecimal findRentPriceById(Integer productId);

    BigDecimal findPurchasePriceById(Integer productId);

    Product findByProductId(Integer productId);

    List<Product> findListByProductId(Set<Integer> set);
}
