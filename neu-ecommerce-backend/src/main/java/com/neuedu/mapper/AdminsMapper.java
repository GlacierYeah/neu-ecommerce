package com.neuedu.mapper;

import com.neuedu.pojo.Admins;
import com.neuedu.pojo.Brands;
import com.neuedu.pojo.Merchants;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminsMapper {

    @Select("select * " +
            "from admins " +
            "where username = #{username}")
    Admins findByName(String username);

    @Insert("insert into admins(username, password, role) " +
            "values (#{username}, #{password}, 1)")
    void addAdmins(String username, String password);

    @Select("select * " +
            "from admins " +
            "where username = #{username} and password = #{password}")
    Admins find(String username, String password);

    @Update("update admins " +
            "set password = #{password} " +
            "where id = #{id}")
    void updatePwd(String password, Integer id);


    @Update("update admins " +
            "set username = #{username}, email = #{email}, phone_number = #{phoneNumber}, avatar = #{avatarUrl} " +
            "where id = #{id}")
    void update(Admins admins);

    @Select("select * " +
            "from brands")
    List<Brands> findBrandsInfo();

    @Select("select * " +
            "from merchants")
    List<Merchants> findMerchantsInfo();

    @Delete("delete from merchants " +
            "where id = #{merchantsId}")
    void deleteMerchants(Integer merchantsId);

    @Delete("delete from brands " +
            "where id = #{brandsId}")
    void deleteBrands(Integer brandsId);
}
