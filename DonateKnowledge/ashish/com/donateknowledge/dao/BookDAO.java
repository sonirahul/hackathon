package com.donateknowledge.dao;

import com.donateknowledge.model.Book;
import java.util.List;

public interface BookDAO {
    public void saveBook(Book book);
    public List<Book> listBook();
    public void removeBook(Integer bookNo);
    public Book getBookById(Integer bookNo);
    public List<Book> listNewReleases();
    public List<Book> listUpdatedBooks();
    public List<Book> listTopTitles();
    public void updateHits(Integer bookNo);
}
