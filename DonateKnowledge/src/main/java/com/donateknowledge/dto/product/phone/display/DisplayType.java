package com.donateknowledge.dto.product.phone.display;

import com.donateknowledge.dto.product.EnumInterface;

public enum DisplayType implements EnumInterface {

	IPS_LCD("IPS LCD");

	private final String value;

	private DisplayType(String value) {
		this.value = value;
	}

	public static DisplayType getDisplayType(String string) {
		for (DisplayType type : DisplayType.values()) {
			if (type.getValue().equals(string)) {
				return type;
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
