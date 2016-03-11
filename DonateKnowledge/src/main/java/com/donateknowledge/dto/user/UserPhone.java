package com.donateknowledge.dto.user;

import java.math.BigInteger;

public class UserPhone {

	private Integer countryCode = 91;
	private BigInteger phoneNum;
	private String phoneType = PhoneType.MOBILE.toString();

	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	public BigInteger getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(BigInteger phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		PhoneType type = PhoneType.getPhoneType(phoneType);
		if (type != null) {
			this.phoneType = type.getValue();
		}
	}
	@Override
	public String toString() {
		return "Phone [phoneNum=" + phoneNum + ", countryCode=" + countryCode + ", phoneType=" + phoneType + "]";
	}
}
