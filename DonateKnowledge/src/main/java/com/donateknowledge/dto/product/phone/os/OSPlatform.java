package com.donateknowledge.dto.product.phone.os;

import com.donateknowledge.dto.product.EnumInterface;

public enum OSPlatform implements EnumInterface {
	IOS("ios"),
	ANDROID("android"),
	OTHER("other");

	private final String value;

	private OSPlatform(String value) {
		this.value = value;
	}

	public static OSPlatform getOSPlatform(String string) {
		for (OSPlatform os : OSPlatform.values()) {
			if (os.getValue().equals(string)) {
				return os;
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
