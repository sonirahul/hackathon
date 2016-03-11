package com.donateknowledge.dto.product.phone.camera;

import com.donateknowledge.dto.product.EnumInterface;

public enum CameraType implements EnumInterface {
	FRONT_CAMERA("Front Camera"),
	BACK_CAMERA("Back Camera"),
	CAMCORDER("Camcorder");

	private final String value;

	private CameraType(String value) {
		this.value = value;
	}

	public static CameraType getCameraType(String string) {
		for (CameraType cameraType : CameraType.values()) {
			if (cameraType.getValue().equals(string)) {
				return cameraType;
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
