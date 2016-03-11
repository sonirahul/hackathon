package com.donateknowledge.dto.product;

public enum DimensionUnit implements EnumInterface {
	MM("mm"),
	CM("cm"),
	METRE("m"),
	INCH("in"),
	FOOT("ft");

	private final String value;

	private DimensionUnit(String value) {
		this.value = value;
	}

	public static DimensionUnit getDimensionUnit(String string) {
		for (DimensionUnit unit : DimensionUnit.values()) {
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
