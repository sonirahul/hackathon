package com.donateknowledge.service;

import com.donateknowledge.model.Transaction;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    public void saveTransaction(Transaction transaction);
    public List<Transaction> listTransactions();
    public List<Transaction> listGroupedTransactions();
    public List<Transaction> listTransactionsByUsername(String userName);
    public List<Transaction> listGroupedTransactionsByUsername(String userName);
    public List<Transaction> listTransactionsByDate(Date date);    
    public List<Transaction> listGroupedTransactionsByDate(Date date);
    public void updateGoogleOrderNo(Integer transactionNo, String orderNo);
}
