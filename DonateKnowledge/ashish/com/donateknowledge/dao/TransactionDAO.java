package com.donateknowledge.dao;

import com.donateknowledge.model.Transaction;
import java.util.Date;
import java.util.List;

public interface TransactionDAO {
    public void saveTransaction(Transaction transaction);
    public Integer getNextTransactionNo(); 
    public List<Transaction> listGroupedTransactions();
    public List<Transaction> listTransactions();
    public List<Transaction> listGroupedTransactionsByUsername(String userName);
    public List<Transaction> listTransactionsByUsername(String userName);
    public List<Transaction> listGroupedTransactionsByDate(Date date);
    public List<Transaction> listTransactionsByDate(Date date);
    public void updateGoogleOrderNo(Integer transactionNo, String orderNo);
}
