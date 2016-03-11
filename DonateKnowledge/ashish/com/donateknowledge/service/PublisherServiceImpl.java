package com.donateknowledge.service;

import com.donateknowledge.dao.PublisherDAO;
import com.donateknowledge.model.Book;
import com.donateknowledge.model.Publisher;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherDAO publisherDAO;
    
    @Transactional
    @Override
    public void savePublisher(Publisher publisher) {
        publisherDAO.savePublisher(publisher);
    }

    @Transactional
    @Override
    public List<Publisher> listPublisher() {
        return publisherDAO.listPublisher();
    }

    @Transactional
    @Override
    public void removePublisher(Integer publisherNo) {
        publisherDAO.removePublisher(publisherNo);
    }

    @Transactional
    @Override
    public Publisher getPublisherById(Integer publisherNo) {
        return publisherDAO.getPublisherById(publisherNo);
    }

    @Transactional
    @Override
    public List<Publisher> listOurPublishers() {
        return publisherDAO.listOurPublishers();
    }
    
    @Transactional
    @Override
    public List<Book> getAllBooksByPublisher(Integer publisherNo) {
        return publisherDAO.getAllBooksByPublisher(publisherNo);
    }
}
