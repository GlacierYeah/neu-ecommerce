package com.neuedu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Brand {

    private Integer id;
    private String name;
    private String logo;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDatetime;
    private Integer brandsId;
}
