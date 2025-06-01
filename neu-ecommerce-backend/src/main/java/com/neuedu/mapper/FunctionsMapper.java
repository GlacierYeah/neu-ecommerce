package com.neuedu.mapper;

import com.neuedu.pojo.Functions;
import com.neuedu.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FunctionsMapper {

    @Select("select * " +
            "from functions " +
            "where role = 1 or role = 5 or role = 6 or role = 0")
    List<Functions> showAdmins();

    @Select("select * " +
            "from functions " +
            "where role = 2 or role = 4 or role = 5 or role = 0")
    List<Functions> showMerchants();

    @Select("select * " +
            "from functions " +
            "where role = 3 or role = 4 or role = 6 or role = 0")
    List<Functions> showBrands();
}
