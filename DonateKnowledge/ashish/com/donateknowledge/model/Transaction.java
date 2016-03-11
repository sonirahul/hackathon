package com.donateknowledge.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="TRANSACTIONS")
public class Transaction implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="TRANSACTIONDETAILNO")
    private Integer transactionDetailNo;
    @Column(name="TRANSACTIONNO")
    private Integer transactionNo;
    @Column(name="GOOGLEORDERNO")
    private String googleOrderNo;
    @Column(name="TRANSACTIONDATE")
    private Date transactionDate;
    @Column(name="USERNAME")
    private String userName;
    @Column(name="BOOKNAME")
    private String bookName;
    @Column(name="COST")
    private Integer cost;
    @Column(name="QTY")
    private Integer qty;

    public String getGoogleOrderNo() {
        return googleOrderNo;
    }
    public void setGoogleOrderNo(String googleOrderNo) {
        this.googleOrderNo = googleOrderNo;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTransactionDetailNo() {
        return transactionDetailNo;
    }
    public void setTransactionDetailNo(Integer transactionDetailNo) {
        this.transactionDetailNo = transactionDetailNo;
    }

    public Integer getTransactionNo() {
        return transactionNo;
    }
    public void setTransactionNo(Integer transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
