package com.donateknowledge.dto.user;

import com.donateknowledge.dto.product.EnumInterface;

public enum PhoneType implements EnumInterface {
	MOBILE("mobile"),
	WORK("work"),
	HOME("home");

	private final String value;

	private PhoneType(String value) {
		this.value = value;
	}

	public static PhoneType getPhoneType(String string) {
		for (PhoneType phoneType : PhoneType.values()) {
			if (phoneType.getValue().equals(string)) {
				return phoneType;
			}
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString(){
		return this.getValue();
	}
}
