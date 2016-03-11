package com.donateknowledge.dao;

import com.donateknowledge.model.Transaction;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().merge(transaction);
    }

    @Override
    public Integer getNextTransactionNo() {
        return Integer.parseInt(sessionFactory.getCurrentSession().createSQLQuery("SELECT MAX(TransactionNo)+1 AS NextTransactionNo FROM Transactions").list().get(0).toString());
    }

    @Override
    public List<Transaction> listGroupedTransactions() {
        return sessionFactory.getCurrentSession().createQuery("select transactionNo, transactionDate, userName, sum(qty*cost), sum(qty), googleOrderNo from Transaction group by transactionNo").list();
    }

    @Override
    public List<Transaction> listTransactions() {
        return sessionFactory.getCurrentSession().createQuery("from Transaction").list();
    }

    @Override
    public List<Transaction> listGroupedTransactionsByUsername(String userName) {
        return sessionFactory.getCurrentSession().createQuery("select transactionNo, transactionDate, userName, sum(qty*cost), sum(qty), googleOrderNo from Transaction where userName = '" + userName + "' group by transactionNo").list();
    }

    @Override
    public List<Transaction> listTransactionsByUsername(String userName) {
        return sessionFactory.getCurrentSession().createQuery("from Transaction where userName = '" + userName + "'").list();
    }
    
    @Override
    public List<Transaction> listGroupedTransactionsByDate(Date date) {
        return sessionFactory.getCurrentSession().createQuery("select transactionNo, transactionDate, userName, sum(qty*cost), sum(qty), googleOrderNo from Transaction where date(transactionDate) = :date group by transactionNo")
                .setDate("date", date)
                .list();
    }

    @Override
    public List<Transaction> listTransactionsByDate(Date date) {
        return sessionFactory.getCurrentSession().createQuery("from Transaction where date(transactionDate) = :date")
                .setDate("date", date)
                .list();
    }

    @Override
    public void updateGoogleOrderNo(Integer transactionNo, String orderNo) {
        sessionFactory.getCurrentSession().createQuery("update Transaction set googleOrderNo = :orderNo where transactionNo = :transactionNo")
                .setString("orderNo", orderNo)
                .setInteger("transactionNo", transactionNo)
                .executeUpdate();                
    }
}
