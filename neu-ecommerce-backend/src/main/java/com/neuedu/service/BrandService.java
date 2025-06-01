package com.neuedu.service;

import com.neuedu.pojo.Brand;
import com.neuedu.pojo.Orders;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Zebra;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public interface BrandService {

    void register(Brand brand);

    void update(Brand brand);

    void addProduct(Product product);

    void updateProduct(Product product);

    void registerZebra(Zebra zebra);

    void walletRegister(String account, String password);

    void deleteProduct(Product product);

    List<Product> findProductInfo();

    Integer findByBrandsId(Integer brandsId);

    List<Orders> findOrders(Integer brandId);

    void deliver(Orders orders);

    void updateOrder(Orders orders);

    void undercarriage(Product product);

    void grounding(Product product);

    Zebra findZebraByBrandId(Integer brandId);

    void updateZebraPwd(String newPwd, Integer brandId);

    void updatePurchaseOrder(BigDecimal postage, BigDecimal money, Integer id);

    void updateRentOrder(BigDecimal postage, BigDecimal money, Integer orderId);

    Brand findBrandInfo(Integer brandId);

    void updateZebraAccount(Zebra zebra);
}
