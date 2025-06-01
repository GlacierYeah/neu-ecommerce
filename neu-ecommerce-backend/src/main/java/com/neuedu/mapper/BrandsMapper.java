package com.neuedu.mapper;

import com.neuedu.pojo.Brands;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BrandsMapper {

    @Select("select * " +
            "from products")
    Result<Product> findProductInfo();

    @Select("select * " +
            "from brands " +
            "where username = #{username}")
    Brands findByName(String username);

    @Insert("insert into brands(username, password, role) " +
            "values (#{username}, #{password}, 3)")
    void addBrands(String username, String password);

    @Select("select * " +
            "from brands " +
            "where username = #{username} and password = #{password}")
    Brands find(String username, String password);

    @Update("update brands " +
            "set password = #{password} " +
            "where id = #{id}")
    void updatePwd(String md5String, Integer id);

    @Update("update brands " +
            "set username = #{username}, email = #{email}, phone_number = #{phoneNumber}, avatar = #{avatar} " +
            "where id = #{id}")
    void update(Brands brands);

}
