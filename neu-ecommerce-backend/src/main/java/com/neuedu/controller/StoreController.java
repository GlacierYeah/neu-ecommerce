package com.neuedu.controller;

import com.neuedu.pojo.*;
import com.neuedu.service.*;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.neuedu.config.CarConfig.CAR_MAP;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OrderService orderService;

    //商家注册自己的网店
    @PostMapping("/register")
    public Result register(@RequestBody Store store) {
        if(store.getContactName() == null || store.getName() == null) {
            return Result.error("缺少必要的参数");
        }
        storeService.register(store);
        return Result.success();
    }

    //网店注册自己的钱包
    @PostMapping("/walletRegister")
    public Result walletRegister(String account, String payCode) {

        storeService.walletRegister(account, payCode);
        return Result.success();
    }

    //商家修改自己的网店的信息
    @PutMapping("/update")
    public Result update(@RequestBody Store store) {
        storeService.update(store);
        return Result.success();
    }

    //商家添加订单
    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody Orders orders) {
        Integer productId = orders.getProductId();
        Integer brandId = productService.findBrandIdByProductId(productId);
        Integer stockQuantity = productService.findQuantityById(productId);
        orders.setBrandId(brandId);
        int res = stockQuantity.compareTo(orders.getQuantity());
        if(res < 0) {
            return Result.error("库存中的商品不够，提醒商家补货哦...");
        }
        BigDecimal rentPrice = productService.findRentPriceById(productId);
        BigDecimal purchasePrice = productService.findPurchasePriceById(productId);
        if(orders.getRentTime() == null) {
            orders.setPurchasePrice(purchasePrice.multiply(BigDecimal.valueOf(orders.getQuantity())));
            storeService.addPurchaseOrder(orders);
            storeService.updatePurchaseToTal(orders);
        }else {
            orders.setRentPrice(rentPrice.multiply(BigDecimal.valueOf(orders.getQuantity())).multiply(BigDecimal.valueOf(orders.getRentTime())));
            storeService.addRentOrder(orders);
            storeService.updateRentToTal(orders);
        }
        return Result.success();
    }

    //商家支付订单
    //注意：注意：注意：重要的事情说三遍，下边的话很重要
    /*
        这里的参数中的orders对象是包括了要支付的订单的全部信息，还包括了要支付的密码
        都以json的形式放在一起传递
     */
    @PutMapping("/payOrder")
    public Result payOrder(@RequestBody OrderCode orders) {
        BigDecimal myMoney = walletService.findBalanceByStoreId(orders.getStoreId());
        BigDecimal totalPrice = orders.getTotalPrice();
        int res = myMoney.compareTo(totalPrice);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(id);
        Wallet wallet = storeService.findByStoreId(storeId);
        Integer walletId = wallet.getId();
        String code = orders.getPayCode();
        String md5String = Md5Util.getMD5String(code);
        String payCode = walletService.findCodeByStoreId(storeId);
        if(!md5String.equals(payCode)) {
            return Result.error("支付订单的密码错误");
        }
        if(res < 0) {
            return Result.error("钱包里的钱不够支付订单哦，快去充值吧！！！");
        }else {
            walletService.updateStore(totalPrice, storeId);
        }
        storeService.payOrder(orders.getId());
        BigDecimal rent = orders.getRentPrice();
        String type;
        if(rent == null) {
            type = "买";
        }else {
            type = "租";
        }
        transactionService.addTransaction(walletId, type, totalPrice);
        return Result.success();
    }

    //商家支付总的订单
    @PutMapping("/payAllOrder")
    public Result payAllOrder(BigDecimal allTotalPrice, String payCode) {
        String md5String = Md5Util.getMD5String(payCode);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(id);
        Wallet wallet = storeService.findByStoreId(storeId);
        System.out.println(wallet);
        System.out.println(Md5Util.getMD5String("123456"));
        if(!wallet.getPayCode().equals(md5String)) {
            return Result.error("支付密码错误");
        }
        BigDecimal balance = wallet.getBalance();
        int res = balance.compareTo(allTotalPrice);
        if(res < 0) {
            return Result.error("钱包里的钱不够支付订单哦，快去充值吧！！！");
        }else {
            walletService.updateStore(allTotalPrice, storeId);
            System.out.println("1");
            System.out.println(CAR_MAP);
            for (Map.Entry<Integer, Car> entry : CAR_MAP.entrySet()) {
                System.out.println("1");
                Car car = entry.getValue();
                System.out.println(car);
                Integer productId = car.getProductId();
                Integer newStoreId = car.getStoreId();
                Orders orders = orderService.findOrdersByProductId(productId, newStoreId);
                Integer orderId = orders.getId();
                orderService.updateStatusById(orderId);
            }
        }
        return Result.success();
    }

    //商家在订单未进行支付的时候取消订单
    @PutMapping("/cancelOrder")
    public Result cancelOrder(@RequestBody Orders orders) {
        storeService.cancelOrder(orders);
        return Result.success();
    }

    //商家确认收货
    @PutMapping("/affirm")
    public Result affirm(@RequestBody Orders orders) {
        storeService.affirm(orders);
        BigDecimal totalPrice = orders.getTotalPrice();
        walletService.brandRecharge(totalPrice);
        Integer walletId = orders.getBrandId();
        String type;
        if(orders.getRentTime() == null) type = "买";
        else type = "借";
        transactionService.addTransaction(walletId, type, totalPrice);
        return Result.success();
    }

    //商家查询所有的商品
    @GetMapping("/findAllProducts")
    public Result<List<Product>> findAllProducts() {
        List<Product> list = storeService.findAllProducts();
        return Result.success(list);
    }

    //把商品添加到购物车当中
    //注意：注意：注意：重要的事情说三遍，下边的话很重要
    /*
        这里的参数中的help对象是包括了要添加到购物车中的那个商品的全部信息，还包括了要借还是买的类型，还包括了借还是买数量
        都以json的形式放在一起传递
     */
    @PostMapping("/addCar")
    public Result addCar(@RequestBody Help help) {
        Integer productId = help.getId();
        Integer stockQuantity = productService.findQuantityById(productId);
        int res = stockQuantity.compareTo(help.getQuantity());
        if(res < 0) {
            return Result.error("库存中的商品不够，提醒商家补货哦...");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchants = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(merchants);
        storeService.addCar(help, storeId, help.getType());
        return Result.success();
    }

    //把商品从购物车中删除
    @DeleteMapping("/deleteCar")
    public Result deleteCar(@RequestBody Product product) {
        Integer productId = product.getId();
        storeService.deleteCar(productId);
        return Result.success();
    }

    //修改在购物车中的状态
    @PutMapping("/updateCarStatus")
    public Result updateCarStatus(@RequestBody Car car) {
        storeService.updateCarStatus(car);
        return Result.success();
    }

    //查询购物车中的商品
    @GetMapping("/findCarProduct")
    public Result<List<Car>> findCarProduct() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchants = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(merchants);
        List<Car> list = storeService.findCarProduct(storeId);
        for(Car car : list) {
            Integer productId = car.getProductId();
            Product product = productService.findByProductId(productId);
            String productName = product.getName();
            String productPic = product.getProductPic();
            BigDecimal purchasePrice = product.getPurchasePrice();
            BigDecimal rentPrice = product.getRentPrice();
            car.setProductName(productName);
            car.setPurchasePrice(purchasePrice);
            car.setRentPrice(rentPrice);
            car.setPicturePic(productPic);
        }
        return Result.success(list);
    }

    //商品从购物车中产生订单
    @PostMapping("/generateOrder")
    public Result generateOrder(String address, Integer logisticsType, BigDecimal postage) {
        List<Car> list = storeService.findStatusCar();
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchants = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(merchants);
        int step = 1;
        for(Car car : list) {
            System.out.println(CAR_MAP);
            CAR_MAP.put(step, car);
            Orders orders = new Orders();
            Integer productId = car.getProductId();
            System.out.println(productId);
            Integer brandId = productService.findBrandIdByProductId(productId);
            Integer rentTime = car.getRentTime();
            Integer quantity = car.getQuantity();
            String type = car.getType();
            orders.setStoreId(storeId);
            orders.setProductId(productId);
            orders.setBrandId(brandId);
            orders.setQuantity(quantity);
            orders.setLogisticsType(logisticsType);
            orders.setAddress(address);
            orders.setPostage(postage);
            if(type.equals("借")) {
                orders.setRentTime(rentTime);
                orders.setRentPrice(car.getTotalPrice());
                storeService.addRentOrder(orders);
            }else {
                orders.setPurchasePrice(car.getTotalPrice());
                storeService.addPurchaseOrder(orders);
            }
            storeService.deleteCar(car.getId());
            step++;
        }
        return Result.success();
    }

    //网店查询自己的订单
    @GetMapping("/findOrders")
    public Result<List<Orders>> findOrders() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchants = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(merchants);
        List<Orders> list = storeService.findOrders(storeId);
        return Result.success(list);
    }

    //清空购物车
    @DeleteMapping("/clearCars")
    public Result clearCars() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchants = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(merchants);
        storeService.clearCars(storeId);
        return Result.success();
    }

    //修改购物车中的租界时间和数量
    @PutMapping("/updateCar")
    public Result updateCar(@RequestBody Car car) {
        Integer id = car.getId();
        Integer rentTime = car.getRentTime();
        Integer quantity = car.getQuantity();
        storeService.updateCar(id, rentTime, quantity);
        return Result.success();
    }

    //获取并赋值购物车中的一条信息的总价
    @PutMapping("/updateCarTotalPrice")
    public Result updateCarTotalPrice(@RequestBody Car car) {
        Integer id = car.getId();
        BigDecimal totalPrice = car.getTotalPrice();
        storeService.updateCarTotalPrice(id, totalPrice);
        return Result.success();
    }

    //查询商品，只能查询到已经送达的商品的信息
    @GetMapping("/findProduct")
    public Result<List<Product>> findProduct() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchants = (Integer) map.get("id");
        Integer storeId = storeService.findByMerchantsId(merchants);
        List<Orders> list = orderService.findByStoreIdStatus(storeId);
        Set<Integer> set = new HashSet<>();
        for(Orders orders : list) {
            Integer productId = orders.getProductId();
            set.add(productId);
        }
        List<Product> list1 = productService.findListByProductId(set);
        return Result.success(list1);
    }
}














