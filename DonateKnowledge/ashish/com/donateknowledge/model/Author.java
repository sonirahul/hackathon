package com.donateknowledge.model;

import java.sql.Blob;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="AUTHORS")
public class Author implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="AUTHORNO")
    private Integer authorNo;
    @Column(name="FIRSTNAME")
    @NotEmpty(message="First name is mandatory")
    private String firstName;
    @Column(name="LASTNAME")
    @NotEmpty(message="Last name is mandatory")
    private String lastName;
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
    @Column(name="DEGREE")
    private String degree;
    @Column(name="EMAILADDRESS")
    @NotEmpty(message="Email address is mandatory")
    @Email(message="Invalid email address")
    private String emailAddress;
    @Column(name="PHOTOGRAPH")
    @Lob
    private Blob photograph;
    @Column(name="SPECIALITY")
    private String speciality;
    @Column(name="DOB")
    private String dob;

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

    public Integer getAuthorNo() {
        return authorNo;
    }
    public void setAuthorNo(Integer authorNo) {
        this.authorNo = authorNo;
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

    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Blob getPhotograph() {
        return photograph;
    }
    public void setPhotograph(Blob photograph) {
        this.photograph = photograph;
    }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
