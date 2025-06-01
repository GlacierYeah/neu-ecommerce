package com.neuedu.pojo;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private Integer role;
    private Integer funcId;
    private String phoneNumber;
}
