package com.donateknowledge.model;

import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PUBLISHERS")
public class Publisher implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="PUBLISHERNO")
    private Integer publisherNo;
    @Column(name="PUBLISHERNAME")
    @NotEmpty(message="Publisher name is mandatory")
    private String publisherName;
    @Column(name="ADDRESS1")
    private String address1;
    @Column(name="ADDRESS2")
    private String address2;
    @Column(name="CITY")
    private String city;
    @Column(name="STATENO")
    private Integer stateNo;
    @Column(name="PINCODE")
    private String pincode;
    @Column(name="COUNTRYNO")
    private Integer countryNo;
    @Column(name="EMAILADDRESS")
    @NotEmpty(message="Email address is mandatory")
    @Email(message="Invalid email address")
    private String emailAddress;

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStateNo() {
        return stateNo;
    }
    public void setStateNo(Integer stateNo) {
        this.stateNo = stateNo;
    }

    public Integer getCountryNo() {
        return countryNo;
    }
    public void setCountryNo(Integer countryNo) {
        this.countryNo = countryNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPublisherName() {
        return publisherName;
    }
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Integer getPublisherNo() {
        return publisherNo;
    }
    public void setPublisherNo(Integer publisherNo) {
        this.publisherNo = publisherNo;
    }
}
