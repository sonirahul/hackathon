package com.donateknowledge.dao;

import com.donateknowledge.model.Book;
import com.donateknowledge.service.BookService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BookService bookService;

    @Override
    public void saveBook(Book book) {
        sessionFactory.getCurrentSession().merge(book);
    }

    @Override
    public List<Book> listBook() {
        return sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    @Override
    public void removeBook(Integer bookNo) {
        Book book = (Book) sessionFactory.getCurrentSession().load(Book.class, bookNo);
        if (null != book) {
            sessionFactory.getCurrentSession().delete(book);
        }
    }

    @Override
    public Book getBookById(Integer bookNo) {
        return (Book) sessionFactory.getCurrentSession().get(Book.class, bookNo);
    }

    @Override
    public List<Book> listNewReleases() {
        return sessionFactory.getCurrentSession().createQuery("from Book ORDER BY year DESC").setMaxResults(10).list();
    }

    @Override
    public List<Book> listUpdatedBooks() {
        return sessionFactory.getCurrentSession().createQuery("from Book WHERE edition <> 'First' ORDER BY year DESC").setMaxResults(10).list();
    }

    @Override
    public List<Book> listTopTitles() {
        return sessionFactory.getCurrentSession().createQuery("from Book ORDER BY hits DESC").setMaxResults(10).list();
    }

    @Override
    public void updateHits(Integer bookNo) {
        Book book = bookService.getBookById(bookNo);
        book.setHits(book.getHits()+1);
        sessionFactory.getCurrentSession().merge(book);
    }
}
