package com.donateknowledge.model;

import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="USERS")
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="USERNO")
    private Integer userNo;
    @Column(name="USERNAME")
    @NotEmpty(message="Username is mandatory")
    private String userName;
    @Column(name="PASSWORD")
    @NotEmpty(message="Password is mandatory")
    private String password;
    @Column(name="EMAILADDRESS")
    @NotEmpty(message="Email address is mandatory")
    @Email(message="Invlaid email address")
    private String emailAddress;
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
    @Column(name="DOB")
    private String dob;
    @Column(name="NEWRELEASE")
    private boolean newRelease;
    @Column(name="BOOKUPDATES")
    private boolean bookUpdates;
    @Column(name="ENABLED")
    private boolean enabled;
    @Column(name="AUTHORITY")
    private String authority;

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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserNo() {
        return userNo;
    }
    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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

    public boolean isBookUpdates() {
        return bookUpdates;
    }
    public void setBookUpdates(boolean bookUpdates) {
        this.bookUpdates = bookUpdates;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCountryNo() {
        return countryNo;
    }
    public void setCountryNo(Integer countryNo) {
        this.countryNo = countryNo;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isNewRelease() {
        return newRelease;
    }
    public void setNewRelease(boolean newRelease) {
        this.newRelease = newRelease;
    }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getStateNo() {
        return stateNo;
    }
    public void setStateNo(Integer stateNo) {
        this.stateNo = stateNo;
    }
}
