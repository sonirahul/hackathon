package com.donateknowledge.model;

import javax.persistence.*;

@Entity
@Table(name="POPULARSEARCHES")
public class PopularSearch implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="SEARCHNO")
    private Integer searchNo;
    @Column(name="VALUE")
    private String searchValue;

    public Integer getSearchNo() {
        return searchNo;
    }
    public void setSearchNo(Integer searchNo) {
        this.searchNo = searchNo;
    }

    public String getSearchValue() {
        return searchValue;
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
