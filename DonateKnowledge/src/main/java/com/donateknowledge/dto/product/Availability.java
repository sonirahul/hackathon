package com.donateknowledge.dto.product;

public enum Availability implements EnumInterface {
	AVAILABLE("Available"),
	COMMING_SOON("Comming Soon"),
	DISCONTINUED("Discontinued"),
	CANCELLED("Cancelled"),
	RUMORED("Rumored");

	private final String value;

	private Availability(String value) {
		this.value = value;
	}

	public static Availability getAvailability(String string) {
		for (Availability availability : Availability.values()) {
			if (availability.getValue().equals(string)) {
				return availability;
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
