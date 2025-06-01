package com.neuedu.service.impl;

import com.neuedu.mapper.StoreMapper;
import com.neuedu.pojo.*;
import com.neuedu.service.StoreService;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public void register(Store store) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        storeMapper.addStore(store, id);
    }

    @Override
    public Integer findByMerchantsId(Integer merchantsId) {
        return storeMapper.findByMerchantsId(merchantsId);
    }

    @Override
    public Wallet findByStoreId(Integer storeId) {

        return storeMapper.findByStoreId(storeId);
    }

    @Override
    public void update(Store store) {

        storeMapper.update(store);
    }

    @Override
    public void payOrder(Integer orderId) {
        storeMapper.payOrder(orderId);
    }

    @Override
    public void cancelOrder(Orders orders) {
        Integer orderId = orders.getId();
        storeMapper.cancelOrder(orderId);
    }

    @Override
    public void affirm(Orders orders) {
        Integer orderId = orders.getId();
        storeMapper.affirm(orderId);
    }

    @Override
    public List<Product> findAllProducts() {

        return storeMapper.findAllProduct();
    }

    @Override
    public void addCar(Help help, Integer storeId, String type) {

        storeMapper.addCar(help, storeId, type);
    }

    @Override
    public void deleteCar(Integer carId) {
        storeMapper.deleteCar(carId);
    }

    @Override
    public List<Car> findCarProduct(Integer storeId) {

        return storeMapper.findCarProduct(storeId);
    }

    @Override
    public BigDecimal updateCarStatus(Car car) {
        Integer id = car.getId();
        Integer status = car.getStatus();
        storeMapper.updateCarStatus(status, id);
        return null;
    }

    @Override
    public List<Car> findStatusCar() {

        return storeMapper.findStatusCar();
    }

    @Override
    public void addPurchaseOrder(Orders orders) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchantsId = (Integer) map.get("id");
        Integer storeId = storeMapper.findByMerchantsId(merchantsId);
        storeMapper.addPurchaseOrder(orders, storeId);
    }

    @Override
    public void addRentOrder(Orders orders) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchantsId = (Integer) map.get("id");
        Integer storeId = storeMapper.findByMerchantsId(merchantsId);
        storeMapper.addRentOrder(orders, storeId);
    }

    @Override
    public void walletRegister(String account, String password) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String md5String = Md5Util.getMD5String(password);
        Integer newId = storeMapper.findByMerchantsId(id);
        storeMapper.walletRegister(account, md5String, newId);
    }

    @Override
    public void updatePurchaseToTal(Orders orders) {

        BigDecimal res1 = orders.getPostage();
        BigDecimal res3 = orders.getPurchasePrice();
        BigDecimal sum = res1.add(res3);
        storeMapper.updateOrderToTal(orders, sum);
    }

    @Override
    public void updateRentToTal(Orders orders) {

        BigDecimal res1 = orders.getPostage();
        BigDecimal res2 = orders.getRentPrice();
        BigDecimal sum = res1.add(res2);
        storeMapper.updateOrderToTal(orders, sum);
    }

    @Override
    public List<Orders> findOrders(Integer storeId) {

        return storeMapper.findOrders(storeId);
    }

    @Override
    public void clearCars(Integer storeId) {

        storeMapper.clearCars(storeId);
    }

    @Override
    public void updateCarRentTime(Integer id, Integer rentTime) {

        storeMapper.updateCarRentTime(id, rentTime);
    }

    @Override
    public void updateCarQuantity(Integer id, Integer quantity) {

        storeMapper.updateCarQuantity(id, quantity);
    }

    @Override
    public void updateCarTotalPrice(Integer id, BigDecimal totalPrice) {

        storeMapper.updateCarTotalPrice(id, totalPrice);
    }

    @Override
    public void updateCar(Integer id, Integer rentTime, Integer quantity) {

        storeMapper.updateCar(id, rentTime, quantity);
    }


}
