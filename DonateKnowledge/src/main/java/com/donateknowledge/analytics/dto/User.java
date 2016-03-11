package com.donateknowledge.analytics.dto;

import java.math.BigInteger;
import java.util.Date;

import com.donateknowledge.utils.DonateKnowledgeUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class User {

	private Date date;
	private BigInteger existingUserCounts = BigInteger.ZERO;
	private BigInteger newUserLogin = BigInteger.ZERO;

	public User(Date date) {
		super();
		this.date = date;
	}

	public void resetUser() {
		this.date = DonateKnowledgeUtils.getDateTimeToday();
		this.existingUserCounts = BigInteger.ZERO;
		this.newUserLogin = BigInteger.ZERO;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigInteger getExistingUserCounts() {
		return existingUserCounts;
	}
	public void setExistingUserCounts(BigInteger existingUserCounts) {
		this.existingUserCounts = existingUserCounts;
	}
	public void addExistingUserCounts(BigInteger existingUserCounts) {
		this.existingUserCounts = this.existingUserCounts.add(existingUserCounts);
	}
	public BigInteger getNewUserLogin() {
		return newUserLogin;
	}
	public void setNewUserLogin(BigInteger newUserLogin) {
		this.newUserLogin = newUserLogin;
	}
	public void addNewUserLogin(BigInteger newUserLogin) {
		this.newUserLogin = this.newUserLogin.add(newUserLogin);
	}
}
