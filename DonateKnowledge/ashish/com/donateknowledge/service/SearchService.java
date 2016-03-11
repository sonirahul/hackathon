package com.donateknowledge.service;

import com.donateknowledge.model.PopularSearch;
import java.util.List;

public interface SearchService {
    public List searchResults(String searchCriteria);
    public List searchAllResults();
    public List listPopularSearches();
    public void savePopularSearch(PopularSearch popularSeacrh);
}
