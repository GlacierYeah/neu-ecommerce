package com.neuedu.controller;

import com.neuedu.pojo.Orders;
import com.neuedu.pojo.Result;
import com.neuedu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //根据订单的状态查询订单
    @GetMapping("/findOrdersByStatus")
    public Result<List<Orders>> findOrdersByStatus(String status) {

        List<Orders> list = orderService.findOrdersByStatus(status);
        return Result.success(list);
    }

    //删除订单
    @DeleteMapping("/deleteOrder")
    public Result deleteOrder(@RequestBody Orders orders) {

        Integer orderId = orders.getId();
        orderService.deleteOrder(orderId);
        return Result.success();
    }
}
