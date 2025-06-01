package com.neuedu.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Integer id;
    private String name;
    private Integer brandId;
    private String description;
    private String productPic;
    private BigDecimal rentPrice;
    private BigDecimal purchasePrice;
    private Integer stockQuantity;
    private String status;

}
