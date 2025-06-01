package com.neuedu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Car {

    private Integer id;
    private Integer storeId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String type;
    private Integer status;
    private Integer rentTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addedTime;
    private String productName;
    private BigDecimal rentPrice;
    private BigDecimal purchasePrice;
    private String picturePic;
}
