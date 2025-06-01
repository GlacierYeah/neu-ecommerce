package com.neuedu.service;

import com.neuedu.pojo.Transaction;
import com.neuedu.pojo.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface WalletService {


    void brandRecharge(BigDecimal mount);

    void storeRecharge(BigDecimal mount);

    void updateBrand(BigDecimal mount, Integer brandId);

    BigDecimal findBalanceByBrandId(Integer brandId);

    BigDecimal findBalanceByStoreId(Integer storeId);

    void updateStore(BigDecimal mount, Integer storeId);

    void updateStorePwd(String newPwd, Integer storeId);

    Wallet findByStoreId(Integer storeId);

    Wallet findByBrandId(Integer brandId);

    void updateBrandPwd(String newPwd, Integer brandId);

    Wallet findStoreWallet(Integer id);

    Wallet findBrandWallet(Integer id);

    String findCodeByStoreId(Integer storeId);

    String findCodeByBrandId(Integer brandId);

    void updateAccount(Integer id, String account);

    List<Transaction> findTransactionsByTime(Integer walletId, LocalDateTime newBeginTime, LocalDateTime newEndTime);
}
