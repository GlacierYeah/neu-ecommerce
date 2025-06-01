package com.neuedu.mapper;

import com.neuedu.pojo.*;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StoreMapper {

    @Insert("insert into store(name, logo, contact_name, contact_phone, contact_email, address, registration_datetime, merchants_id) " +
            "values(#{store.name}, #{store.logo}, #{store.contactName}, #{store.contactPhone}, #{store.contactEmail}, #{store.address}, now(), #{id})")
    void addStore(Store store, Integer id);

    @Select("select id " +
            "from store " +
            "where merchants_id = #{merchantsId}")
    Integer findByMerchantsId(Integer merchantsId);

    @Select("select * " +
            "from wallets " +
            "where store_id = #{storeId}")
    Wallet findByStoreId(Integer storeId);

    @Update("update store " +
            "set name = #{name}, contact_name = #{contactName}, contact_phone = #{contactPhone}, contact_email = #{contactEmail}, " +
            "address = #{address}, logo = #{logo} " +
            "where id = #{id}")
    void update(Store store);

    @Insert("insert into orders(store_id, product_id, brand_id, rent_time, quantity, " +
            "order_date, logistics_type, address) " +
            "values (#{storeId}, #{orders.productId}, #{orders.brandId}, #{orders.rentTime}, #{orders.quantity}, " +
            "now(), #{orders.logisticsType}, #{orders.address})")
    void addOrder(Orders orders, Integer storeId);

    @Update("update orders " +
            "set status = \"待发货\" " +
            "where id = #{orderId}")
    void payOrder(Integer orderId);

    @Update("update orders " +
            "set status = \"已取消\" " +
            "where id = #{orderId}")
    void cancelOrder(Integer orderId);

    @Update("update orders " +
            "set status = \"已送达\" " +
            "where id = #{orderId}")
    void affirm(Integer orderId);

    @Select("select * " +
            "from products " +
            "where status = '已上架'")
    List<Product> findAllProduct();

    @Update("update orders " +
            "set total_price = #{totalPrice} " +
            "where id = #{orderId}")
    void addTotalPrice(BigDecimal totalPrice, Integer orderId);

    @Update("update products " +
            "set type = #{type} " +
            "where id = #{productId}")
    void updateProductType(String type, Integer productId);

    @Insert("insert into shopping_cart(store_id, product_id, quantity, rent_price, purchase_price, added_time, type) " +
            "values(#{storeId}, #{help.id}, #{help.quantity}, #{help.rentPrice}, " +
            "#{help.purchasePrice}, now(), #{type})")
    void addCar(Help help, Integer storeId, String type);

    @Delete("delete from shopping_cart " +
            "where id = #{carId}")
    void deleteCar(Integer carId);

    @Select("select * " +
            "from shopping_cart " +
            "where store_id = #{storeId}")
    List<Car> findCarProduct(Integer storeId);

    @Update("update shopping_cart " +
            "set status = ((#{status} + 1) % 2)" +
            "where id = #{id}")
    void updateCarStatus(Integer status, Integer id);

    @Select("select * " +
            "from shopping_cart " +
            "where status = 1")
    List<Car> findStatusCar();

    @Insert("insert into orders(store_id, product_id, brand_id, quantity, purchase_price, order_date, logistics_type, address, postage) " +
            "values(#{storeId}, #{orders.productId}, #{orders.brandId}, #{orders.quantity}, #{orders.purchasePrice}, " +
            "now(), #{orders.logisticsType}, #{orders.address}, #{orders.postage})")
    void addPurchaseOrder(Orders orders, Integer storeId);

    @Insert("insert into orders(store_id, product_id, brand_id, rent_time, quantity, rent_price, order_date, logistics_type, address, postage) " +
            "values(#{storeId}, #{orders.productId}, #{orders.brandId}, #{orders.rentTime}, #{orders.quantity}, #{orders.rentPrice}, " +
            "now(), #{orders.logisticsType}, #{orders.address}, #{orders.postage})")
    void addRentOrder(Orders orders, Integer storeId);

    @Insert("insert into wallets(account, pay_code, store_id) " +
            "values (#{account}, #{password}, #{id})")
    void walletRegister(String account, String password, Integer id);

    @Update("update orders " +
            "set total_price = #{sum} " +
            "where id = #{orders.id}")
    void updateOrderToTal(Orders orders, BigDecimal sum);

    @Select("select * " +
            "from orders " +
            "where store_id = #{storeId}")
    List<Orders> findOrders(Integer storeId);

    @Delete("delete from shopping_cart " +
            "where store_id = #{storeId}")
    void clearCars(Integer storeId);

    @Update("update shopping_cart " +
            "set rent_time = #{rentTime} " +
            "where id = #{id}")
    void updateCarRentTime(Integer id, Integer rentTime);

    @Update("update shopping_cart " +
            "set quantity = #{quantity} " +
            "where id = #{id}")
    void updateCarQuantity(Integer id, Integer quantity);

    @Update("update shopping_cart " +
            "set total_price = #{totalPrice} " +
            "where id = #{id}")
    void updateCarTotalPrice(Integer id, BigDecimal totalPrice);

    @Update("update shopping_cart " +
            "set quantity = #{quantity}, rent_time = #{rentTime} " +
            "where id = #{id}")
    void updateCar(Integer id, Integer rentTime, Integer quantity);
}
