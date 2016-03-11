package com.donateknowledge.dao;

import com.donateknowledge.model.Author;
import com.donateknowledge.model.Book;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveAuthor(Author author) {
        sessionFactory.getCurrentSession().merge(author);
    }

    @Override
    public List<Author> listAuthor() {
        return sessionFactory.getCurrentSession().createQuery("from Author").list();
    }

    @Override
    public void removeAuthor(Integer authorNo) {
        Author author = (Author) sessionFactory.getCurrentSession().load(Author.class, authorNo);
        if (null != author) {
            sessionFactory.getCurrentSession().delete(author);
        }
    }

    @Override
    public Author getAuthorById(Integer authorNo) {
        return (Author) sessionFactory.getCurrentSession().get(Author.class, authorNo);
    }

    @Override
    public List<Author> listOurAuthors() {
        return sessionFactory.getCurrentSession().createQuery("from Author").setMaxResults(10).list();
    }

    @Override
    public List<Book> getAllBooksByAuthor(Integer authorNo) {
        return sessionFactory.getCurrentSession().createQuery("FROM Book WHERE author1No = " + authorNo + " OR author2No = " + authorNo + " OR author3No = " + authorNo + " OR author4No = " + authorNo).list();
    }
}
