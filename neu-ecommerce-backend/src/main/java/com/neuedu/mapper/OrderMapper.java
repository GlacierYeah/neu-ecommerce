package com.neuedu.mapper;

import com.neuedu.pojo.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * " +
            "from orders " +
            "where status = #{status}")
    List<Orders> findOrdersByStatus(String status);

    @Delete("delete from orders " +
            "where id = #{orderId}")
    void deleteOrder(Integer orderId);

    @Update("update orders " +
            "set status = '待发货' " +
            "where id = #{orderId}")
    void updateStatusById(Integer orderId);

    @Select("select * " +
            "from orders " +
            "where product_id = #{productId} and status = '未支付' and store_id = #{newStoreId}")
    Orders findOrdersByProductId(Integer productId, Integer newStoreId);

    @Select("select * " +
            "from orders " +
            "where store_id = #{storeId} and status = '已送达'")
    List<Orders> findByStoreIdStatus(Integer storeId);
}
