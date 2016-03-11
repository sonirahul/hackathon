package com.donateknowledge.service;

import com.donateknowledge.dao.AuthorDAO;
import com.donateknowledge.model.Author;
import com.donateknowledge.model.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDAO authorDAO;
    
    @Transactional
    @Override
    public void saveAuthor(Author author) {
        authorDAO.saveAuthor(author);
    }

    @Transactional
    @Override
    public List<Author> listAuthor() {
        return authorDAO.listAuthor();
    }

    @Transactional
    @Override
    public void removeAuthor(Integer authorNo) {
        authorDAO.removeAuthor(authorNo);
    }

    @Transactional
    @Override
    public Author getAuthorById(Integer authorNo) {
        return authorDAO.getAuthorById(authorNo);
    }

    @Transactional
    @Override
    public List<Author> listOurAuthors() {
        return authorDAO.listOurAuthors();
    }
    
    @Transactional
    @Override
    public List<Book> getAllBooksByAuthor(Integer authorNo) {
        return authorDAO.getAllBooksByAuthor(authorNo);
    }
}
