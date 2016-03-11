package com.donateknowledge.dto.product.phone;

import com.donateknowledge.dto.product.EnumInterface;

public enum Features implements EnumInterface {

	ACCELEROMETER("Accelerometer"),
	BAROMETER("Barometer"),
	COMPASS("Compass"),
	FINGERPRINT("Fingerprint"),
	GYROSCOPE("Gyroscope"),
	HEARTRATE("Heart Rate Monitor"),
	HUMIDITY("Humidity"),
	ION_STRENGTHENED_GLASS("Ion Strengthened Glass"),
	LIGHT_SENSOR("Light Sensor"),
	OLEOPHOBIC_COATING("Oleophobic Coating"),
	PROXIMITY("Proximity Sensor"),
	SCRATCH_RESISTANT_GLASS("Scratch Resistant Glass"),
	TEMPERATURE("Temperature"),
	TOUCH_3D("3D Touch");

	private final String value;

	private Features(String value) {
		this.value = value;
	}

	public static Features getFeatures(String string) {
		for (Features features : Features.values()) {
			if (features.getValue().equals(string)) {
				return features;
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
