package com.donateknowledge;

import com.donateknowledge.service.SearchService;
import com.donateknowledge.service.AuthorService;
import com.donateknowledge.service.BookService;
import com.donateknowledge.service.PublisherService;
import com.donateknowledge.service.CategoryService;
import com.donateknowledge.model.Search;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {
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

    @RequestMapping(value="/admin/adminLogin", method = RequestMethod.GET)
    public String showAdminLogin(ModelMap model) {
        return "adminLogin";
    }

    @RequestMapping(value="/admin/adminLoginFailed", method = RequestMethod.GET)
    public String showAdminLoginError(ModelMap model) {
        model.addAttribute("error", "true");
        return "adminLogin";
    }

    @RequestMapping(value="/admin/adminLogout", method = RequestMethod.GET)
    public String showAdminLoginAfterLogout(ModelMap model) {
        return "adminLogin";
    }

    @RequestMapping(value="/adminAccessDenied")
    public String showAccessDeniedPage(ModelMap model) {
        return "adminAccessDenied";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLogin(ModelMap model) {
        return "home";
    }

    @RequestMapping(value="/loginFailed", method = RequestMethod.GET)
    public String showLoginError(ModelMap model, Map<String, Object> map) {
        map.put("newReleasesList", bookService.listNewReleases());
        map.put("ourAuthorsList", authorService.listOurAuthors());
        map.put("ourPublishersList", publisherService.listOurPublishers());
        map.put("allCategoriesList", categoryService.listCategory());
        map.put("allBooksList", bookService.listBook());
        map.put("popularSearchList", searchService.listPopularSearches());
        map.put("updatedBooksList", bookService.listUpdatedBooks());
        map.put("topTitlesList", bookService.listTopTitles());
        map.put("search", new Search());
        model.addAttribute("error", "true");
        return "home";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String showHomeAfterLogout(ModelMap model, Map<String, Object> map) {
        map.put("newReleasesList", bookService.listNewReleases());
        map.put("ourAuthorsList", authorService.listOurAuthors());
        map.put("ourPublishersList", publisherService.listOurPublishers());
        map.put("allCategoriesList", categoryService.listCategory());
        map.put("allBooksList", bookService.listBook());
        map.put("popularSearchList", searchService.listPopularSearches());
        map.put("updatedBooksList", bookService.listUpdatedBooks());
        map.put("topTitlesList", bookService.listTopTitles());
        map.put("search", new Search());
        return "home";
    }
}
