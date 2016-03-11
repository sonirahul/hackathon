package com.donateknowledge.dto.product.phone.battery;

import com.donateknowledge.dto.product.EnumInterface;

public enum BatteryType implements EnumInterface {
	Li_Po("Li-Po");

	private final String value;

	private BatteryType(String value) {
		this.value = value;
	}

	public static BatteryType getBatteryType(String string) {
		for (BatteryType type : BatteryType.values()) {
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
