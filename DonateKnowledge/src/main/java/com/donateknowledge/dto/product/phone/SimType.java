package com.donateknowledge.dto.product.phone;

import com.donateknowledge.dto.product.EnumInterface;

public enum SimType implements EnumInterface {
	MINI("Mini Regular"),
	MICRO("Micro"),
	NANO("Nano");

	private final String value;

	private SimType(String value) {
		this.value = value;
	}

	public static SimType getSimType(String string) {
		for (SimType simType : SimType.values()) {
			if (simType.getValue().equals(string)) {
				return simType;
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
