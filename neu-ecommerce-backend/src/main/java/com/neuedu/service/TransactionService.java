package com.neuedu.service;

import com.neuedu.pojo.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    void addTransaction(Integer walletId, String type, BigDecimal totalPrice);

    List<Transaction> findByWalletId(Integer id);
}
