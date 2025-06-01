package com.neuedu.service.impl;

import com.neuedu.mapper.ProductMapper;
import com.neuedu.pojo.Product;
import com.neuedu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer findQuantityById(Integer productId) {

        return productMapper.findQuantityById(productId);
    }

    @Override
    public void updateStockQuantity(Integer quantity, Integer productId) {

        productMapper.updateStockQuantity(quantity, productId);
    }


    @Override
    public Product findByProductName(String productName) {

        return productMapper.findByProductName(productName);
    }

    @Override
    public Integer findBrandIdByProductId(Integer productId) {

        return productMapper.findBrandIdByProductId(productId);
    }

    @Override
    public BigDecimal findRentPriceById(Integer productId) {

        return productMapper.findRentPriceById(productId);
    }

    @Override
    public BigDecimal findPurchasePriceById(Integer productId) {

        return productMapper.findPurchasePriceById(productId);
    }

    @Override
    public Product findByProductId(Integer productId) {

        return productMapper.findByProductId(productId);
    }

    @Override
    public List<Product> findListByProductId(Set<Integer> set) {
        List<Product> list = new ArrayList<>();
        for(Integer index : set) {
            list.add(productMapper.findListByProductId(index));
        }
        return list;
    }


}
