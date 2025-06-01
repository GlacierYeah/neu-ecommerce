package com.neuedu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderCode {

    private Integer id;
    private Integer storeId;
    private Integer brandId;
    private Integer productId;
    private Integer quantity;
    private Integer rentTime;
    private BigDecimal rentPrice;
    private BigDecimal purchasePrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private String logisticsNumber;
    private Integer logisticsType;
    private BigDecimal postage;
    private String address;
    private String status;
    private String payCode;
}
