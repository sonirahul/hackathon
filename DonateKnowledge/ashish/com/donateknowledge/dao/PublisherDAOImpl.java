package com.donateknowledge.dao;

import com.donateknowledge.model.Book;
import com.donateknowledge.model.Publisher;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PublisherDAOImpl implements PublisherDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void savePublisher(Publisher publisher) {
        sessionFactory.getCurrentSession().merge(publisher);
    }

    @Override
    public List<Publisher> listPublisher() {
        return sessionFactory.getCurrentSession().createQuery("from Publisher").list();
    }

    @Override
    public void removePublisher(Integer publisherNo) {
        Publisher publisher = (Publisher) sessionFactory.getCurrentSession().load(Publisher.class, publisherNo);
        if (null != publisher) {
            sessionFactory.getCurrentSession().delete(publisher);
        }
    }

    @Override
    public Publisher getPublisherById(Integer publisherNo) {
        return (Publisher) sessionFactory.getCurrentSession().get(Publisher.class, publisherNo);
    }

    @Override
    public List<Publisher> listOurPublishers() {
        return sessionFactory.getCurrentSession().createQuery("from Publisher").setMaxResults(10).list();
    }

    @Override
    public List<Book> getAllBooksByPublisher(Integer publisherNo) {
        return sessionFactory.getCurrentSession().createQuery("FROM Book WHERE publisherNo = " + publisherNo).list();
    }
}
