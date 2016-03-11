package com.donateknowledge;

import com.donateknowledge.model.PopularSearch;
import com.donateknowledge.model.Search;
import com.donateknowledge.service.SearchService;
import com.donateknowledge.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {
    @Autowired
    private UserService userService;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/searchResults/{searchCriteria}")
    public String showSearchResultsByCriteria(Map<String, Object> map, @PathVariable("searchCriteria") String searchCriteria) {
        map.put("searchResultsList", searchService.searchResults(searchCriteria));
        Search search = new Search(searchCriteria);
        map.put("search", search);
        PopularSearch ps = new PopularSearch();
        ps.setSearchValue(searchCriteria);
        searchService.savePopularSearch(ps);
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "searchResults";
    }

    @RequestMapping("/searchResults")
    public String showSearchResults(Map<String, Object> map) {
        map.put("searchResultsList", searchService.searchAllResults());
        Search search = new Search();
        map.put("search", search);
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            map.put("userDetails", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        return "searchResults";
    }
}
