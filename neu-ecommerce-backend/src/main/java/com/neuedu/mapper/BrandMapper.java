package com.neuedu.mapper;

import com.neuedu.pojo.Brand;
import com.neuedu.pojo.Orders;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.Zebra;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BrandMapper {

    @Insert("insert into brand(name, logo, contact_name, contact_phone, contact_email, address, registration_datetime, brands_id) " +
            "values (#{brand.name}, #{brand.logo}, #{brand.contactName}, #{brand.contactPhone}, #{brand.contactEmail}, #{brand.address}, now(), #{id})")
    void addBrand(Brand brand, Integer id);

    @Update("update brand " +
            "set name = #{brand.name}, logo = #{brand.logo}, contact_name = #{brand.contactName}, " +
            "contact_phone = #{brand.contactPhone}, contact_email = #{brand.contactEmail}, address = #{brand.address} " +
            "where brands_id = #{id}")
    void update(Brand brand, Integer id);

    @Insert("insert into products(name, description, product_pic, rent_price, purchase_price, stock_quantity, brand_id) " +
            "values (#{product.name}, #{product.description}, #{product.productPic}, #{product.rentPrice}, " +
            "#{product.purchasePrice}, #{product.stockQuantity}, #{id})")
    void addProduct(Product product, Integer id);

    @Update("update products " +
            "set name = #{name}, description = #{description}, product_pic = #{productPic}, " +
            "rent_price = #{rentPrice}, purchase_price = #{purchasePrice}, stock_quantity = #{stockQuantity} " +
            "where id = #{id}")
    void updateProduct(Product product);

    @Insert("insert into zebra_account(account_name, password, email, phone_number, registration_datetime, brand_id) " +
            "values (#{zebra.accountName}, #{zebra.password}, #{zebra.email}, #{zebra.phoneNumber}, now(), #{id})")
    void registerZebra(Zebra zebra, Integer id);

    @Select("select id " +
            "from brand " +
            "where brands_id = #{brandsId}")
    Integer findByBrandsId(Integer brandsId);

    @Insert("insert into wallets(account, pay_code, brand_id) " +
            "values (#{account}, #{password}, #{id})")
    void walletRegister(String account, String password, Integer id);

    @Delete("delete from products " +
            "where id = #{id}")
    void deleteProduct(Product product);

    @Select("select * " +
            "from products")
    List<Product> findProductInfo();

    @Select("select * " +
            "from orders " +
            "where brand_id = #{brandId}")
    List<Orders> findOrders(Integer brandId);

    @Update("update orders " +
            "set status = \"已发货\" " +
            "where id = #{orderId}")
    void deliver(Orders orders, Integer orderId);

    @Update("update orders " +
            "set logistics_number = #{logisticsNumber} " +
            "where id = #{id}")
    void updateOrder(Orders orders);

    @Update("update products " +
            "set status = \"已下架\" " +
            "where id = #{productId}")
    void undercarriage(Integer productId);

    @Update("update products " +
            "set status = \"上架中\" " +
            "where id = #{productId}")
    void grounding(Integer productId);

    @Select("select * " +
            "from zebra_account " +
            "where brand_id = #{brandId}")
    Zebra findZebraByBrandId(Integer brandId);

    @Update("update zebra_account " +
            "set password = #{md5String} " +
            "where brand_id = #{brandId}")
    void updateZebraPwd(String md5String, Integer brandId);

    @Update("update orders " +
            "set total_price = #{total} " +
            "where id = #{id}")
    void updatePurchaseOrder(BigDecimal total, Integer id);

    @Update("update orders " +
            "set total_price = #{total} " +
            "where id = #{id}")
    void updateRentOrder(BigDecimal total, Integer id);

    @Select("select * " +
            "from brand " +
            "where id = #{brandId}")
    Brand findBrandInfo(Integer brandId);

    @Update("update zebra_account " +
            "set account_name = #{accountName}, email = #{email}, phone_number = #{phoneNumber} " +
            "where id = #{id}")
    void updateZebraAccount(Zebra zebra);
}
