package com.donateknowledge.service;

import com.donateknowledge.dao.TransactionDAO;
import com.donateknowledge.model.Transaction;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionDAO transactionDAO;
    
    @Transactional
    @Override
    public void saveTransaction(Transaction transaction) {
        transactionDAO.saveTransaction(transaction);
    }
    
    @Transactional
    @Override
    public List<Transaction> listTransactions() {
        return transactionDAO.listTransactions();
    }

    @Transactional
    @Override
    public List<Transaction> listGroupedTransactions() {
        return transactionDAO.listGroupedTransactions();
    }
    
    @Transactional
    @Override
    public List<Transaction> listTransactionsByUsername(String userName) {
        return transactionDAO.listTransactionsByUsername(userName);
    }

    @Transactional
    @Override
    public List<Transaction> listGroupedTransactionsByUsername(String userName) {
        return transactionDAO.listGroupedTransactionsByUsername(userName);
    }

    @Transactional
    @Override
    public List<Transaction> listTransactionsByDate(Date date) {
        return transactionDAO.listTransactionsByDate(date);
    }

    @Transactional
    @Override
    public List<Transaction> listGroupedTransactionsByDate(Date date) {
        return transactionDAO.listGroupedTransactionsByDate(date);
    }

    @Transactional
    @Override
    public void updateGoogleOrderNo(Integer transactionNo, String orderNo) {
        transactionDAO.updateGoogleOrderNo(transactionNo, orderNo);
    }
}
