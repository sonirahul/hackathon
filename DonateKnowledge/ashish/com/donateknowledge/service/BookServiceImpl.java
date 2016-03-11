package com.donateknowledge.service;

import com.donateknowledge.dao.BookDAO;
import com.donateknowledge.model.Book;
import com.donateknowledge.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    @Value("${emailFrom}")
    String emailFrom;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MailService mailService;

    @Autowired
    private BookDAO bookDAO;
    
    @Transactional
    @Override
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Transactional
    @Override
    public List<Book> listBook() {
        return bookDAO.listBook();
    }

    @Transactional
    @Override
    public void removeBook(Integer bookNo) {
        bookDAO.removeBook(bookNo);
    }

    @Transactional
    @Override
    public Book getBookById(Integer bookNo) {
        return bookDAO.getBookById(bookNo);
    }

    @Transactional
    @Override
    public List<Book> listNewReleases() {
        return bookDAO.listNewReleases();
    }

    @Transactional
    @Override
    public List<Book> listUpdatedBooks() {
        return bookDAO.listUpdatedBooks();
    }

    @Transactional
    @Override
    public List<Book> listTopTitles() {
        return bookDAO.listTopTitles();
    }
    
    @Transactional
    @Override
    public void updateHits(Integer bookNo) {
        bookDAO.updateHits(bookNo);
    }

    @Override
    public void notifyCustomersByMail(Book book) {
        List<User> users = userService.listUser();
        for (User user : users) {
            String emailMessage = "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' "
            + "style='font-family: Verdana, Arial, Helvetica, sans-serif; "
            + "font-size: 12pt; color:#5a5a5a;'><tr><td align='left'>"
            + "<p>Dear " + user.getFirstName() + ",</p></td></tr><tr>"
            + "<td align='left'>" 
            + "<p>ISBN: " + book.getIsbn() + "<br /><br />"
            + "Edition: " + book.getEdition() + "<br /><br />"
            + "Synopsis: " + book.getSynopsis() + "<br /><br />"
            + "Topics covered: " + book.getTopicsCovered() + "<br /><br /></p>"
            + "<p>Thank you for using  this site.<br /></p><br/><br/><p>"
            + "Regards,<br />Sharanam & Vaishali's Online Bookshop<br /></p><p><br />"
            + "<br />THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY. </p></td></tr></table>";
            if(user.getAuthority().equals("CUSTOMER") && user.isNewRelease()) {
                if(book.getBookNo() == null) {
                    String emailSubject = "Sharanam & Vaishali's Online Bookshop: " + book.getBookName() + " has been added.";
                    mailService.sendMail(emailFrom, user.getEmailAddress(), emailSubject, emailMessage);
                }
            }
            if(user.getAuthority().equals("CUSTOMER") && user.isBookUpdates()) {
                if(book.getBookNo()!=null) {
                    String emailSubject = "Sharanam & Vaishali's Online Bookshop: " + book.getBookName() + " has been updated.";
                    mailService.sendMail(emailFrom, user.getEmailAddress(), emailSubject, emailMessage);
                }
            }
        }           
    }
}
