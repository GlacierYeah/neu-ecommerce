package com.neuedu.service.impl;

import com.neuedu.mapper.TransactionMapper;
import com.neuedu.pojo.Transaction;
import com.neuedu.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public void addTransaction(Integer walletId, String type, BigDecimal totalPrice) {
        transactionMapper.addTransaction(walletId, type, totalPrice);
    }

    @Override
    public List<Transaction> findByWalletId(Integer id) {

        return transactionMapper.findByWalletId(id);
    }
}
