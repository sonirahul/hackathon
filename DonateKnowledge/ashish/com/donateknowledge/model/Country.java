package com.donateknowledge.model;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="COUNTRIES")
public class Country implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="COUNTRYNO")
    private Integer countryNo;
    @Column(name="COUNTRY")
    @NotEmpty(message="Country name is mandatory")
    private String country;

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCountryNo() {
        return countryNo;
    }
    public void setCountryNo(Integer countryNo) {
        this.countryNo = countryNo;
    }
}
