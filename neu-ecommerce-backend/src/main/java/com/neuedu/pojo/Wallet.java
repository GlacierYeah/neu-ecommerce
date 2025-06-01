package com.neuedu.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet {

    private Integer id;
    private BigDecimal balance;
    private String account;
    private String payCode;
    private Integer storeId;
}
