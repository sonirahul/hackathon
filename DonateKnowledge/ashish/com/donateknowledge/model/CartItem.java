package com.donateknowledge.model;

public class CartItem implements java.io.Serializable {
    private String userName;
    private Integer bookNo;
    private String bookName;
    private String synopsis;
    private Integer cost;
    private Integer qty;

    public CartItem() {
    }

    public CartItem(String userName, Integer bookNo, String bookName, String synopsis, Integer cost, Integer qty) {
        this.userName = userName;
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.synopsis = synopsis;
        this.cost = cost;
        this.qty = qty;
    }

    public Integer getBookNo() {
        return bookNo;
    }
    public void setBookNo(Integer bookNo) {
        this.bookNo = bookNo;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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
}
