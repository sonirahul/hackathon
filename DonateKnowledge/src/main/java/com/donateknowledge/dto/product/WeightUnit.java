package com.donateknowledge.dto.product;

public enum WeightUnit implements EnumInterface {
	GRAM("gm"),
	MILLIGRAM("mg"),
	OUNCE("oz"),
	POUND("lb"),
	KG("kg");

	private final String value;

	private WeightUnit(String value) {
		this.value = value;
	}

	public static WeightUnit getWeightUnit(String string) {
		for (WeightUnit unit : WeightUnit.values()) {
			if (unit.getValue().equals(string)) {
				return unit;
			}
		}
		return null;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString(){
		return this.getValue();
	}
}
