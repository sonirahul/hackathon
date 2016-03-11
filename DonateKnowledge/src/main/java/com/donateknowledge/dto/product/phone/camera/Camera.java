package com.donateknowledge.dto.product.phone.camera;

import static com.donateknowledge.utils.CheapestGadgetUtils.getEnumMappedList;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.donateknowledge.utils.CheapestGadgetUtils;

public class Camera {

	private BigDecimal apertureSize;
	private String cameraType;
	private List<String> features;
	private String flashType;
	private BigDecimal focalLengthInMM;
	private BigDecimal megapixel;
	private BigDecimal pixelSize;
	private List<String> resolution;
	private String sensorSize;

	public Camera() {
		super();
	}
	
	public Camera(String apertureSize, String cameraType, String flashType, 
			String focalLengthInMM, String megapixel, 
			String pixelSize, String sensorSize) {
		super();
		this.apertureSize = CheapestGadgetUtils.stringToBigDecimal(apertureSize);
		this.cameraType = cameraType;
		this.flashType = flashType;
		this.focalLengthInMM = CheapestGadgetUtils.stringToBigDecimal(focalLengthInMM);
		this.megapixel = CheapestGadgetUtils.stringToBigDecimal(megapixel);
		this.pixelSize = CheapestGadgetUtils.stringToBigDecimal(pixelSize);
		this.sensorSize = sensorSize;
	}

	public BigDecimal getApertureSize() {
		return apertureSize;
	}
	public void setApertureSize(BigDecimal apertureSize) {
		this.apertureSize = apertureSize;
	}
	public String getCameraType() {
		return cameraType;
	}
	public void setCameraType(String cameraType) {
		CameraType type = CameraType.getCameraType(cameraType);
		if (type != null) {
			this.cameraType = type.getValue();
		}
	}
	public List<String> getFeatures() {
		features = getEnumMappedList(features, asList(CameraFeatures.values()));
		return features;
	}
	public String getFlashType() {
		return flashType;
	}
	public void setFlashType(String flashType) {
		CameraFlashType type = CameraFlashType.getCameraFlashType(flashType);
		if (type != null) {
			this.flashType = type.getValue();
		}
	}
	public BigDecimal getFocalLengthInMM() {
		return focalLengthInMM;
	}
	public void setFocalLengthInMM(BigDecimal focalLengthInMM) {
		this.focalLengthInMM = focalLengthInMM;
	}
	public BigDecimal getMegapixel() {
		return megapixel;
	}
	public void setMegapixel(BigDecimal megapixel) {
		this.megapixel = megapixel;
	}
	public BigDecimal getPixelSize() {
		return pixelSize;
	}
	public void setPixelSize(BigDecimal pixelSize) {
		this.pixelSize = pixelSize;
	}
	public List<String> getResolution() {
		if (resolution == null) {
			resolution = new ArrayList<>();
		}
		return resolution;
	}
	public String getSensorSize() {
		return sensorSize;
	}
	public void setSensorSize(String sensorSize) {
		this.sensorSize = sensorSize;
	}

	@Override
	public String toString() {
		return "Camera [apertureSize=" + apertureSize + ", cameraType=" + cameraType + ", features=" + features
				+ ", flashType=" + flashType + ", focalLengthInMM=" + focalLengthInMM + ", megapixel=" + megapixel
				+ ", pixelSize=" + pixelSize + ", resolution=" + resolution + ", sensorSize=" + sensorSize + "]";
	}
}
