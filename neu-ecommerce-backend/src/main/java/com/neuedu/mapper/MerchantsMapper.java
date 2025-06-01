package com.neuedu.mapper;

import com.neuedu.pojo.Merchants;
import com.neuedu.pojo.Result;
import com.neuedu.pojo.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MerchantsMapper {

    @Select("select * " +
            "from merchants " +
            "where username = #{username}")
    Merchants findByName(String username);

    @Insert("insert into merchants(username, password, role) " +
            "values (#{username}, #{password}, 2)")
    void addMerchants(String username, String password);

    @Select("select * " +
            "from merchants " +
            "where username = #{username} and password = #{password}")
    Merchants find(String username, String password);

    @Update("update merchants " +
            "set password = #{password} " +
            "where id = #{id}")
    void updatePwd(String md5String, Integer id);

    @Update("update merchants " +
            "set username = #{username}, email = #{email}, phone_number = #{phoneNumber}, avatar = #{avatar} " +
            "where id = #{id}")
    void update(Merchants merchants);

    @Select("select * " +
            "from store " +
            "where merchants_id = #{id}")
    Store findStoreById(Integer id);
}
