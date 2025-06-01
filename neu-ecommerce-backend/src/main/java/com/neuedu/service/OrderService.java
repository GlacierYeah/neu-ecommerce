package com.neuedu.service;

import com.neuedu.pojo.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> findOrdersByStatus(String status);

    void deleteOrder(Integer orderId);

    void updateStatusById(Integer orderId);

    Orders findOrdersByProductId(Integer productId, Integer newStoreId);

    List<Orders> findByStoreIdStatus(Integer storeId);
}
