package com.donateknowledge.service;

import com.donateknowledge.model.Book;
import com.donateknowledge.model.Publisher;
import java.util.List;

public interface PublisherService {
    public void savePublisher(Publisher publisher);
    public List<Publisher> listPublisher();
    public void removePublisher(Integer publisherNo);
    public Publisher getPublisherById(Integer publisherNo);
    public List<Publisher> listOurPublishers();
    public List<Book> getAllBooksByPublisher(Integer publisherNo);
}
