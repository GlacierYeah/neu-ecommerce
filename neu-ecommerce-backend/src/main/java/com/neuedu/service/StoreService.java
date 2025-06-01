package com.neuedu.service;

import com.neuedu.pojo.*;

import java.math.BigDecimal;
import java.util.List;

public interface StoreService {

    void register(Store store);

    Integer findByMerchantsId(Integer merchantsId);

    Wallet findByStoreId(Integer storeId);

    void update(Store store);

    void payOrder(Integer orderId);

    void cancelOrder(Orders orders);

    void affirm(Orders orders);

    List<Product> findAllProducts();

    void addCar(Help help, Integer storeId, String type);

    void deleteCar(Integer productId);

    List<Car> findCarProduct(Integer storeId);

    BigDecimal updateCarStatus(Car car);

    List<Car> findStatusCar();

    void addPurchaseOrder(Orders orders);

    void addRentOrder(Orders orders);

    void walletRegister(String account, String password);

    void updatePurchaseToTal(Orders orders);

    void updateRentToTal(Orders orders);

    List<Orders> findOrders(Integer storeId);

    void clearCars(Integer storeId);

    void updateCarRentTime(Integer id, Integer rentTime);

    void updateCarQuantity(Integer id, Integer quantity);

    void updateCarTotalPrice(Integer id, BigDecimal totalPrice);

    void updateCar(Integer id, Integer rentTime, Integer quantity);
}
