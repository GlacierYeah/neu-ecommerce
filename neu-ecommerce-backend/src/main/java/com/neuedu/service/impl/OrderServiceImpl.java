package com.neuedu.service.impl;

import com.neuedu.mapper.OrderMapper;
import com.neuedu.pojo.Orders;
import com.neuedu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Orders> findOrdersByStatus(String status) {

        return orderMapper.findOrdersByStatus(status);
    }

    @Override
    public void deleteOrder(Integer orderId) {

        orderMapper.deleteOrder(orderId);
    }

    @Override
    public void updateStatusById(Integer orderId) {

        orderMapper.updateStatusById(orderId);
    }

    @Override
    public Orders findOrdersByProductId(Integer productId, Integer newStoreId) {

        return orderMapper.findOrdersByProductId(productId, newStoreId);
    }

    @Override
    public List<Orders> findByStoreIdStatus(Integer storeId) {

        return orderMapper.findByStoreIdStatus(storeId);
    }
}
