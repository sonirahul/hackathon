package com.donateknowledge;

import com.donateknowledge.service.SearchService;
import com.donateknowledge.service.AuthorService;
import com.donateknowledge.service.BookService;
import com.donateknowledge.service.UserService;
import com.donateknowledge.service.PublisherService;
import com.donateknowledge.service.CategoryService;
import com.donateknowledge.model.Search;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping("/")
    public String startHome() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String showHome(Map<String, Object> map, HttpServletRequest request) {
        map.put("newReleasesList", bookService.listNewReleases());
        map.put("ourAuthorsList", authorService.listOurAuthors());
        map.put("ourPublishersList", publisherService.listOurPublishers());
        map.put("allCategoriesList", categoryService.listCategory());
        map.put("allBooksList", bookService.listBook());
        map.put("popularSearchList", searchService.listPopularSearches());
        map.put("updatedBooksList", bookService.listUpdatedBooks());
        map.put("topTitlesList", bookService.listTopTitles());
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        map.put("search", new Search());
        return "home";
    }
    
    @RequestMapping("/showBookDetails/{bookNo}")
    public String showBookDetails(Map<String, Object> map, @PathVariable("bookNo") int bookNo) {
        map.put("bookList", bookService.getBookById(bookNo));
        bookService.updateHits(bookNo);
        map.put("search", new Search());
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "showBookDetails";
    }
    
    @RequestMapping("/showAuthorDetails/{authorNo}")
    public String showAuthorDetails(Map<String, Object> map, @PathVariable("authorNo") Integer authorNo) {
        map.put("authorList", authorService.getAuthorById(authorNo));
        map.put("bookList", authorService.getAllBooksByAuthor(authorNo));
        map.put("search", new Search());
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "showAuthorDetails";
    }
    
    @RequestMapping("/showPublisherDetails/{publisherNo}")
    public String showPublisherDetails(Map<String, Object> map, @PathVariable("publisherNo") Integer publisherNo) {
        map.put("publisherList", publisherService.getPublisherById(publisherNo));
        map.put("bookList", publisherService.getAllBooksByPublisher(publisherNo));
        map.put("search", new Search());
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "showPublisherDetails";
    }

    @RequestMapping("/showCategoryDetails/{categoryNo}")
    public String showCategoryDetails(Map<String, Object> map, @PathVariable("categoryNo") Integer categoryNo) {
        map.put("categoryList", categoryService.getCategoryById(categoryNo));
        map.put("bookList", categoryService.getAllBooksByCategory(categoryNo));
        map.put("search", new Search());
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "showCategoryDetails";
    }
}
