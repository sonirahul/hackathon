package com.donateknowledge;

import com.donateknowledge.service.SearchService;
import com.donateknowledge.service.AuthorService;
import com.donateknowledge.service.BookService;
import com.donateknowledge.service.UserService;
import com.donateknowledge.service.PublisherService;
import com.donateknowledge.service.CategoryService;
import com.donateknowledge.service.CartService;
import com.donateknowledge.model.CartItem;
import com.donateknowledge.model.Search;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
    @Value("${googleMerchantID}")
    String googleMerchantID;

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

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;
    
    @RequestMapping("/showCart/{userName}")
    public String showCart(Map<String, Object> map, @PathVariable("userName") String userName, HttpSession session) {
        map.put("cartList", (List<CartItem>) session.getAttribute(userName));
        map.put("search", new Search());
        map.put("googleMerchantID", googleMerchantID);
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "showCart";
    }

    @RequestMapping("/addToCart/{userName}/{bookNo}")
    public String addToCart(Map<String, Object> map, @PathVariable("bookNo") Integer bookNo, @PathVariable("userName") String userName, HttpSession session) {
        cartService.addBookToCart(bookNo, userName, session);
        map.put("newReleasesList", bookService.listNewReleases());
        map.put("ourAuthorsList", authorService.listOurAuthors());
        map.put("ourPublishersList", publisherService.listOurPublishers());
        map.put("allCategoriesList", categoryService.listCategory());
        map.put("allBooksList", bookService.listBook());
        map.put("popularSearchList", searchService.listPopularSearches());
        map.put("updatedBooksList", bookService.listUpdatedBooks());
        map.put("topTitlesList", bookService.listTopTitles());
        map.put("search", new Search());
        return "redirect:/home";
    }

    @RequestMapping("/removeFromCart/{userName}/{bookNo}")
    public String removeFromCart(Map<String, Object> map, @PathVariable("bookNo") Integer bookNo, @PathVariable("userName") String userName, HttpSession session) {
        cartService.removeBookFromCart(bookNo, userName, session);
        map.put("search", new Search());
        return "redirect:/showCart/{userName}";
    }

    @RequestMapping("/saveCart/{userName}")
    public String saveCart(Map<String, Object> map, @PathVariable("userName") String userName, HttpSession session) {
        cartService.saveCart(userName, session);
        return "redirect:/checkOut/{userName}";
    }
    
    @RequestMapping("/checkOut/{userName}")
    public String checkOut(Map<String, Object> map, @PathVariable("userName") String userName, HttpSession session) {
        map.put("cartList", (List<CartItem>) session.getAttribute(userName));        
        map.put("googleMerchantID", googleMerchantID);
        session.removeAttribute(userName);
        return "checkOut";
    }
}
