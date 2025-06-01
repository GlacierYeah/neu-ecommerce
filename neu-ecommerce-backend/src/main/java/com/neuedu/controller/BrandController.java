package com.neuedu.controller;

import com.neuedu.pojo.*;
import com.neuedu.service.BrandService;
import com.neuedu.service.ProductService;
import com.neuedu.service.WalletService;
import com.neuedu.service.ZebraService;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ZebraService zebraService;

    //品牌商的电子钱包注册功能
    @PostMapping("/walletRegister")
    public Result walletRegister(String account, String payCode) {

        brandService.walletRegister(account, payCode);
        return Result.success();
    }

    //品牌商注册品牌的功能
    @PostMapping("/register")
    public Result register(@RequestBody Brand brand) {
        if(brand.getName() == null || brand.getContactName() == null) {
            return Result.error("缺少必要的参数");
        }
        brandService.register(brand);
        return Result.success();
    }

    //品牌商修改自己的品牌的信息
    @PutMapping("/update")
    public Result update(@RequestBody Brand brand) {
        if(brand.getName() == null || brand.getContactName() == null || brand.getContactPhone() == null ||
                brand.getContactEmail() == null || brand.getAddress() == null) {
            return Result.error("缺少必要的参数");
        }
        brandService.update(brand);
        return Result.success();
    }

    //品牌商添加属于自己的品牌的商品
    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody Product product) {
        if(product.getName() == null) {
            return Result.error("缺少必要的参数");
        }
        String productName = product.getName();
        Product newProduct = productService.findByProductName(productName);
        if(newProduct != null) {
            return Result.error("商品已经存在，无需添加");
        }
        brandService.addProduct(product);
        return Result.success();
    }

    //品牌商修改自己的商品的内容
    @PutMapping("/updateProduct")
    public Result updateProduct(@RequestBody Product product) {
        if(product.getId() == null || product.getName() == null || product.getDescription() == null ||
                product.getRentPrice() == null || product.getPurchasePrice() == null ||
                product.getStockQuantity() == null) {
            return Result.error("缺少必要的参数");
        }
        brandService.updateProduct(product);
        return Result.success();
    }

    //品牌商删除自己的品牌的商品
    @DeleteMapping("/deleteProduct")
    public Result deleteProduct(@RequestBody Product product) {
        if(product.getId() == null) {
            return Result.error("缺少必要的参数");
        }
        brandService.deleteProduct(product);
        return Result.success();
    }

    //品牌商注册自己的品牌的斑马账号
    @PostMapping("/registerZebra")
    public Result registerZebra(@RequestBody Zebra zebra) {
        if(zebra.getAccountName() == null || zebra.getPassword() == null) {
            return Result.error("缺少必要的参数");
        }
        brandService.registerZebra(zebra);
        return Result.success();
    }

    //品牌商查询自己的品牌的所有商品的所有信息
    @GetMapping("/findProductInfo")
    public Result<List<Product>> findProductInfo() {
        List<Product> list = brandService.findProductInfo();
        return Result.success(list);
    }

    //品牌方查询有关自己的品牌的全部订单
    @GetMapping("/findOrders")
    public Result<List<Orders>> findOrders() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer brandId = brandService.findByBrandsId(id);
        List<Orders> list = brandService.findOrders(brandId);
        return Result.success(list);
    }

    //品牌方把订单的状态修改为“已发货”
    @PutMapping("/deliver")
    public Result deliver(@RequestBody Orders orders) {
        brandService.deliver(orders);
        Integer productId = orders.getProductId();
        Integer quantity = orders.getQuantity();
        productService.updateStockQuantity(quantity, productId);
        return Result.success();
    }

    //品牌方给订单添加物流单号
    @PutMapping("/updateOrder")
    public Result updateOrder(@RequestBody Orders orders) {
        brandService.updateOrder(orders);
        return Result.success();
    }

    //品牌方下架自己的商品
    @PutMapping("/undercarriage")
    public Result undercarriage(@RequestBody Product product) {
        if(product.getId() == null) {
            return Result.error("缺少必要的参数");
        }
        brandService.undercarriage(product);
        return Result.success();
    }

    //品牌方上架自己的商品
    @PutMapping("/grounding")
    public Result grounding(@RequestBody Product product) {
        if(product.getId() == null) {
            return Result.error("缺少必要的参数");
        }
        brandService.grounding(product);
        return Result.success();
    }

    //品牌方查找自己的斑马账号
    @GetMapping("/findZebraByBrandId")
    public Result<Zebra> findZebraByBrandId() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer brandId = brandService.findByBrandsId(id);
        Zebra zebra = brandService.findZebraByBrandId(brandId);
        return Result.success(zebra);
    }

    //品牌方修改自己的斑马账号的账号
    @PutMapping("/updateZebraAccount")
    public Result updateZebraAccount(@RequestBody Zebra zebra) {
        brandService.updateZebraAccount(zebra);
        return Result.success();
    }

    //品牌方修改自己的斑马账号的密码
    @PatchMapping("/updateZebraPwd")
    public Result updateZebraPwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer brandsId = (Integer) map.get("id");
        Integer brandId = brandService.findByBrandsId(brandsId);
        Zebra zebra = zebraService.findByBrandId(brandId);
        if(!zebra.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码不正确");
        }
        if(!rePwd.equals(newPwd)) {
            return Result.error("两次输入的新密码不一致");
        }
        brandService.updateZebraPwd(newPwd, brandId);
        return Result.success();
    }

    //品牌方查看自己的品牌的信息
    @GetMapping("/findBrandInfo")
    public Result<Brand> findBrandInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer brandId = brandService.findByBrandsId(id);
        Brand brand = brandService.findBrandInfo(brandId);
        return Result.success(brand);
    }

}

























