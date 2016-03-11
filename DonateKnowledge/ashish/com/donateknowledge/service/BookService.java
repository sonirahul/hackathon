package com.donateknowledge.service;

import com.donateknowledge.model.Book;
import java.util.List;

public interface BookService {
    public void saveBook(Book book);
    public List<Book> listBook();
    public void removeBook(Integer bookNo);
    public Book getBookById(Integer bookNo);
    public List<Book> listNewReleases();
    public List<Book> listUpdatedBooks();
    public List<Book> listTopTitles();
    public void updateHits(Integer bookNo);
    public void notifyCustomersByMail(Book book);
}
