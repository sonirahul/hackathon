package com.donateknowledge.service;

import javax.servlet.http.HttpSession;

public interface CartService {
    public void addBookToCart(Integer bookNo, String userName, HttpSession session);
    public void saveCart(String userName, HttpSession session);
    public void removeBookFromCart(Integer bookNo, String userName, HttpSession session);
}
