package com.neuedu.controller;

import com.neuedu.pojo.Result;
import com.neuedu.pojo.Transaction;
import com.neuedu.pojo.Wallet;
import com.neuedu.service.*;
import com.neuedu.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private BrandService brandService;

    //查询自己的钱包的交易记录
    @GetMapping("/findTransaction")
    public Result<List<Transaction>> findTransaction() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer role = (Integer) map.get("role");
        if(role == 2) {
            Integer storeId = storeService.findByMerchantsId(id);
            Wallet wallet = walletService.findByStoreId(storeId);
            List<Transaction> list = transactionService.findByWalletId(wallet.getId());
            return Result.success(list);
        }else {
            Integer brandId = brandService.findByBrandsId(id);
            Wallet wallet = walletService.findByBrandId(brandId);
            List<Transaction> list = transactionService.findByWalletId(wallet.getId());
            return Result.success(list);
        }
    }

    //根据时间查询订单
    @GetMapping("/findTransactionsByTime")
    public Result<List<Transaction>> findTransactionsByTime(String beginTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime newBeginTime = LocalDateTime.parse(beginTime, formatter);
        LocalDateTime newEndTime = LocalDateTime.parse(endTime, formatter);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Integer role = (Integer) map.get("role");
        if(role == 2) {
            Integer storeId = storeService.findByMerchantsId(id);
            Wallet wallet = walletService.findByStoreId(storeId);
            Integer walletId = wallet.getId();
            List<Transaction> list = walletService.findTransactionsByTime(walletId, newBeginTime, newEndTime);
            return Result.success(list);
        }else {
            Integer brandId = brandService.findByBrandsId(id);
            Wallet wallet = walletService.findByBrandId(brandId);
            Integer walletId = wallet.getId();
            List<Transaction> list = walletService.findTransactionsByTime(walletId, newBeginTime, newEndTime);
            return Result.success(list);
        }
    }
}
