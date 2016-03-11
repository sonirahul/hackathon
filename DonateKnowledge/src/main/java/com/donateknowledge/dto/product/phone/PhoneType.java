package com.donateknowledge.dto.product.phone;

import com.donateknowledge.dto.product.EnumInterface;

public enum PhoneType implements EnumInterface {
	SMARTPHONE("smartPhone");

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
