package com.neuedu.mapper;

import com.neuedu.pojo.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TransactionMapper {

    @Insert("insert into wallet_transaction(wallet_id, trade_type, amount, transaction_time) " +
            "values(#{walletId}, #{type}, #{totalPrice}, now())")
    void addTransaction(Integer walletId, String type, BigDecimal totalPrice);

    @Select("select * " +
            "from wallet_transaction " +
            "where wallet_id = #{id}")
    List<Transaction> findByWalletId(Integer id);
}
