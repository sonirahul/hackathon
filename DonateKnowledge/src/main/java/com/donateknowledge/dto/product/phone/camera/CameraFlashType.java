package com.donateknowledge.dto.product.phone.camera;

import com.donateknowledge.dto.product.EnumInterface;

public enum CameraFlashType implements EnumInterface {
	DUAL_LED("Dual Led"),
	XENON("Xenon"),
	LED("LED");

	private final String value;

	private CameraFlashType(String value) {
		this.value = value;
	}

	public static CameraFlashType getCameraFlashType(String string) {
		for (CameraFlashType cameraFlashType : CameraFlashType.values()) {
			if (cameraFlashType.getValue().equals(string)) {
				return cameraFlashType;
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
