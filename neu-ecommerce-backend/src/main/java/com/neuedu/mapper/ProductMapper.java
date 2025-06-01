package com.neuedu.mapper;

import com.neuedu.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select stock_quantity " +
            "from products " +
            "where id = #{productId}")
    Integer findQuantityById(Integer productId);

    @Update("update products " +
            "set stock_quantity = stock_quantity - #{quantity} " +
            "where id = #{productId}")
    void updateStockQuantity(Integer quantity, Integer productId);

    @Select("select * " +
            "from products " +
            "where name = #{productName}")
    Product findByProductName(String productName);

    @Select("select brand_id " +
            "from products " +
            "where id = #{productId}")
    Integer findBrandIdByProductId(Integer productId);

    @Select("select rent_price " +
            "from products " +
            "where id = #{productId}")
    BigDecimal findRentPriceById(Integer productId);

    @Select("select purchase_price " +
            "from products " +
            "where id = #{productId}")
    BigDecimal findPurchasePriceById(Integer productId);

    @Select("select * " +
            "from products " +
            "where id = #{productId}")
    Product findByProductId(Integer productId);

    @Select("select * " +
            "from products " +
            "where id = #{index}")
    Product findListByProductId(Integer index);
}
