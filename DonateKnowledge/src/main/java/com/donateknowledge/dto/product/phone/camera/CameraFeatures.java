package com.donateknowledge.dto.product.phone.camera;

import com.donateknowledge.dto.product.EnumInterface;

public enum CameraFeatures implements EnumInterface {

	AUTO_FOCUS("Autofocus"),
	BACK_ILLUMINATED_SENSOR("Back-illuminated sensor (BSI)"), 
	FACE_DETECTION("Face detection"),
	GEO_TAGGING("Geo tagging"),
	PANORAMA("panorama"),
	SAPPHIRE_CRYSTAL_LENS_COVER("Sapphire crystal lens cover"),
	SELF_TIMER("Self-timer"), 
	TOUCH_FOCUS("Touch to focus");
	

	private final String value;

	private CameraFeatures(String value) {
		this.value = value;
	}

	public static CameraFeatures getCameraFeatures(String string) {
		for (CameraFeatures features : CameraFeatures.values()) {
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
