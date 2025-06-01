package com.neuedu.service.impl;

import com.neuedu.mapper.BrandMapper;
import com.neuedu.mapper.StoreMapper;
import com.neuedu.mapper.WalletMapper;
import com.neuedu.pojo.Transaction;
import com.neuedu.pojo.Wallet;
import com.neuedu.service.WalletService;
import com.neuedu.utils.Md5Util;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void brandRecharge(BigDecimal mount) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer brandsId = (Integer) map.get("id");
        Integer brandId = brandMapper.findByBrandsId(brandsId);
        System.out.println(brandId);
        walletMapper.brandRecharge(mount, brandId);
    }

    @Override
    public void storeRecharge(BigDecimal mount) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchantsId = (Integer) map.get("id");
        Integer storeId = brandMapper.findByBrandsId(merchantsId);
        walletMapper.storeRecharge(mount, storeId);
    }

    @Override
    public void updateBrand(BigDecimal mount, Integer brandId) {

        walletMapper.updateBrand(mount, brandId);
    }

    @Override
    public BigDecimal findBalanceByBrandId(Integer brandId) {

        return walletMapper.findBalanceByBrandId(brandId);
    }

    @Override
    public BigDecimal findBalanceByStoreId(Integer storeId) {

        return walletMapper.findBalanceByStoreId(storeId);
    }

    @Override
    public void updateStore(BigDecimal mount, Integer storeId) {

        walletMapper.updateStore(mount, storeId);
    }

    @Override
    public void updateStorePwd(String newPwd, Integer storeId) {

        String md5String = Md5Util.getMD5String(newPwd);
        walletMapper.updateStorePwd(md5String, storeId);
    }

    @Override
    public Wallet findByStoreId(Integer storeId) {

        return walletMapper.findByStoreId(storeId);
    }

    @Override
    public Wallet findByBrandId(Integer brandId) {

        return walletMapper.findByBrandId(brandId);
    }

    @Override
    public void updateBrandPwd(String newPwd, Integer brandId) {

        String md5String = Md5Util.getMD5String(newPwd);
        walletMapper.updateBrandPwd(md5String, brandId);
    }

    @Override
    public Wallet findStoreWallet(Integer id) {

        return walletMapper.findStoreWallet(id);
    }

    @Override
    public Wallet findBrandWallet(Integer id) {

        return walletMapper.findBrandWallet(id);
    }

    @Override
    public String findCodeByStoreId(Integer storeId) {

        return walletMapper.findCodeByStoreId(storeId);

    }

    @Override
    public String findCodeByBrandId(Integer brandId) {

        return walletMapper.findCodeByBrandId(brandId);
    }

    @Override
    public void updateAccount(Integer id, String account) {

        walletMapper.updateAccount(id, account);
    }

    @Override
    public List<Transaction> findTransactionsByTime(Integer walletId, LocalDateTime newBeginTime, LocalDateTime newEndTime) {

        return walletMapper.findTransactionsByTime(walletId, newBeginTime, newEndTime);
    }

}





















