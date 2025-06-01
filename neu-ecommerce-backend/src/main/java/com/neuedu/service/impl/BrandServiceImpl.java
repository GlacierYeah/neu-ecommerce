package com.neuedu.service.impl;

import com.neuedu.mapper.BrandMapper;
import com.neuedu.pojo.Brand;
import com.neuedu.pojo.Orders;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Zebra;
import com.neuedu.service.BrandService;
import com.neuedu.utils.CalculateTotalPrice;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void register(Brand brand) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        brandMapper.addBrand(brand, id);
    }

    @Override
    public void update(Brand brand) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        brandMapper.update(brand, id);
    }

    @Override
    public void addProduct(Product product) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        brandMapper.addProduct(product, id);
    }

    @Override
    public void updateProduct(Product product) {
        brandMapper.updateProduct(product);
    }

    @Override
    public void registerZebra(Zebra zebra) {
        String md5String = Md5Util.getMD5String(zebra.getPassword());
        zebra.setPassword(md5String);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer newId = brandMapper.findByBrandsId(id);
        brandMapper.registerZebra(zebra, newId);
    }

    @Override
    public void walletRegister(String account, String payCode) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String md5String = Md5Util.getMD5String(payCode);
        Integer newId = brandMapper.findByBrandsId(id);
        brandMapper.walletRegister(account, md5String, newId);
    }

    @Override
    public void deleteProduct(Product product) {
        brandMapper.deleteProduct(product);
    }

    @Override
    public List<Product> findProductInfo() {

        return brandMapper.findProductInfo();
    }

    @Override
    public Integer findByBrandsId(Integer brandsId) {
        return brandMapper.findByBrandsId(brandsId);
    }

    @Override
    public List<Orders> findOrders(Integer brandId) {

        return brandMapper.findOrders(brandId);
    }

    @Override
    public void deliver(Orders orders) {
        Integer orderId = orders.getId();
        brandMapper.deliver(orders, orderId);
    }

    @Override
    public void updateOrder(Orders orders) {
        brandMapper.updateOrder(orders);
    }

    @Override
    public void undercarriage(Product product) {
        Integer productId = product.getId();
        brandMapper.undercarriage(productId);
    }

    @Override
    public void grounding(Product product) {
        Integer productId = product.getId();
        brandMapper.grounding(productId);
    }

    @Override
    public Zebra findZebraByBrandId(Integer brandId) {

        return brandMapper.findZebraByBrandId(brandId);
    }

    @Override
    public void updateZebraPwd(String newPwd, Integer brandId) {

        String md5String = Md5Util.getMD5String(newPwd);
        brandMapper.updateZebraPwd(md5String, brandId);
    }

    @Override
    public void updatePurchaseOrder(BigDecimal postage, BigDecimal money, Integer id) {

        BigDecimal total = postage.add(money);
        brandMapper.updatePurchaseOrder(total, id);
    }

    @Override
    public void updateRentOrder(BigDecimal postage, BigDecimal money, Integer id) {

        BigDecimal total = postage.add(money);
        brandMapper.updateRentOrder(total, id);
    }

    @Override
    public Brand findBrandInfo(Integer brandId) {

        return brandMapper.findBrandInfo(brandId);
    }

    @Override
    public void updateZebraAccount(Zebra zebra) {

        brandMapper.updateZebraAccount(zebra);
    }

}
