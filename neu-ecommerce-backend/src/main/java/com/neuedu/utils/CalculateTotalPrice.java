package com.neuedu.utils;

import com.neuedu.pojo.Orders;

import java.math.BigDecimal;

public class CalculateTotalPrice {

    public static BigDecimal calculate(Orders orders) {
        BigDecimal res1 = orders.getPostage();
        BigDecimal res2, res3;
        if(orders.getRentPrice() != null) {
            res2 = orders.getRentPrice();
        }else {
            res2 = BigDecimal.ZERO;
        }
        if(orders.getPurchasePrice() != null) {
            res3 = orders.getPurchasePrice();
        }else {
            res3 = BigDecimal.ZERO;
        }
        BigDecimal sum = res1.add(res2).add(res3);
        return sum;
    }
}
