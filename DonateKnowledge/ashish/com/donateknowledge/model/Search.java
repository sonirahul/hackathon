package com.donateknowledge.model;

public class Search implements java.io.Serializable {
    private String searchCriteria;

    public Search() {
    }

    public Search(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }
    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
