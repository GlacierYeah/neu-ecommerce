package com.neuedu.mapper;

import com.neuedu.pojo.Zebra;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ZebraMapper {

    @Select("select * " +
            "from zebra_account " +
            "where brand_id = #{brandId}")
    Zebra findByBrandId(Integer brandId);
}
