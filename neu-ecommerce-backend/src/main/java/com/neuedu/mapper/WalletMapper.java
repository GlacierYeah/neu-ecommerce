package com.neuedu.mapper;

import com.neuedu.pojo.Transaction;
import com.neuedu.pojo.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface WalletMapper {

    @Update("update wallets " +
            "set balance = balance + #{mount} " +
            "where brand_id = #{brandId}")
    void brandRecharge(BigDecimal mount, Integer brandId);

    @Update("update wallets " +
            "set balance = balance + #{mount} " +
            "where store_id = #{storeId}")
    void storeRecharge(BigDecimal mount, Integer storeId);

    @Update("update wallets " +
            "set balance = balance - #{mount} " +
            "where brand_id = #{brandId}")
    void updateBrand(BigDecimal mount, Integer brandId);

    @Select("select balance " +
            "from wallets " +
            "where  brand_id = #{brandId}")
    BigDecimal findBalanceByBrandId(Integer brandId);

    @Select("select balance " +
            "from wallets " +
            "where  store_id = #{storeId}")
    BigDecimal findBalanceByStoreId(Integer storeId);

    @Update("update wallets " +
            "set balance = balance - #{mount} " +
            "where store_id = #{storeId}")
    void updateStore(BigDecimal mount, Integer storeId);

    @Update("update wallets " +
            "set pay_code = #{newPwd} " +
            "where store_id = #{storeId}")
    void updateStorePwd(String newPwd, Integer storeId);

    @Select("select * " +
            "from wallets " +
            "where store_id = #{storeId}")
    Wallet findByStoreId(Integer storeId);

    @Select("select * " +
            "from wallets " +
            "where brand_id = #{brandId}")
    Wallet findByBrandId(Integer brandId);

    @Update("update wallets " +
            "set pay_code = #{newPwd} " +
            "where brand_id = #{brandId}")
    void updateBrandPwd(String md5String, Integer brandId);

    @Select("select * " +
            "from wallets " +
            "where store_id = #{id}")
    Wallet findStoreWallet(Integer id);

    @Select("select * " +
            "from wallets " +
            "where brand_id = #{id}")
    Wallet findBrandWallet(Integer id);

    @Select("select pay_code " +
            "from wallets " +
            "where store_id = #{storeId}")
    String findCodeByStoreId(Integer storeId);

    @Select("select pay_code " +
            "from wallets " +
            "where brand_id = #{brandId}")
    String findCodeByBrandId(Integer brandId);

    @Update("update wallets " +
            "set account = #{account} " +
            "where id = #{id}")
    void updateAccount(Integer id, String account);

    @Select("select * " +
            "from wallet_transaction " +
            "where wallet_id = #{walletId} and transaction_time between #{newBeginTime} and #{newEndTime}")
    List<Transaction> findTransactionsByTime(Integer walletId, LocalDateTime newBeginTime, LocalDateTime newEndTime);
}
