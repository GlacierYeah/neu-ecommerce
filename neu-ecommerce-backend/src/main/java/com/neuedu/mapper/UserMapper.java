package com.neuedu.mapper;

import com.neuedu.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from admins where username = #{username}")
    User findByAdminsName(String username);

    @Select("select * from merchants where username = #{username}")
    User findByMerchantsName(String username);

    @Select("select * from brands where username = #{username}")
    User findByBrandsName(String username);

    @Insert("insert into admins(username, password, role) values " +
            "(#{username}, #{password}, 1)")
    void addAdmins(String username, String password);

    @Insert("insert into merchants(username, password, role) values " +
            "(#{username}, #{password}, 2)")
    void addMerchants(String username, String password);

    @Insert("insert into brands(username, password, role) values " +
            "(#{username}, #{password}, 3)")
    void addBrands(String username, String password);
}
