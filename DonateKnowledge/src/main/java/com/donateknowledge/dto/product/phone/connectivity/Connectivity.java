package com.donateknowledge.dto.product.phone.connectivity;

import static com.donateknowledge.utils.CheapestGadgetUtils.getEnumMappedList;
import static java.util.Arrays.asList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true, 
value={"bluetoothAvailable", "gpsAvailable", "usbAvailable", "wifiAvailable"})
public class Connectivity {

	private boolean	bluetoothAvailable;
	private String bluetoothVersion;
	private boolean gpsAvailable;
	private List<String> gpsType;
	private List<String> otherFeatures;
	private boolean usbAvailable;
	private List<String> usbType;
	private List<String> wifiType;
	private boolean wifiAvailable;

	public boolean isBluetoothAvailable() {
		if (StringUtils.isNotEmpty(bluetoothVersion)) {
			bluetoothAvailable = true;
		}
		else {
			bluetoothAvailable = false;
		}
		return bluetoothAvailable;
	}
	public String getBluetoothVersion() {
		return bluetoothVersion;
	}
	public void setBluetoothVersion(String bluetoothVersion) {
		ConnectivityType type = ConnectivityType.getConnectivityType(bluetoothVersion);
		if (type != null) {
			this.bluetoothVersion = type.getValue();
		}
	}
	public boolean isGpsAvailable() {
		if (!CollectionUtils.isEmpty(gpsType)) {
			gpsAvailable = true;
		}
		else {
			gpsAvailable = false;
		}
		return gpsAvailable;
	}
	public List<String> getGpsType() {
		gpsType = getEnumMappedList(gpsType, asList(ConnectivityType.values()));
		return gpsType;
	}
	public List<String> getOtherFeatures() {
		otherFeatures = getEnumMappedList(otherFeatures, asList(ConnectivityType.values()));
		return otherFeatures;
	}
	public boolean isUsbAvailable() {
		if (!CollectionUtils.isEmpty(usbType)) {
			usbAvailable = true;
		}
		else {
			usbAvailable = false;
		}
		return usbAvailable;
	}
	public List<String> getUsbType() {
		usbType = getEnumMappedList(usbType, asList(ConnectivityType.values()));
		return usbType;
	}
	public boolean isWifiAvailable() {
		if (!CollectionUtils.isEmpty(wifiType)) {
			wifiAvailable = true;
		}
		else {
			wifiAvailable = false;
		}
		return wifiAvailable;
	}
	public List<String> getWifiType() {
		wifiType = getEnumMappedList(wifiType, asList(ConnectivityType.values()));
		return wifiType;
	}
	@Override
	public String toString() {
		return "Connectivity [bluetoothAvailable=" + isBluetoothAvailable() + ", bluetoothVersion=" + bluetoothVersion
				+ ", gpsAvailable=" + isGpsAvailable() + ", gpsType=" + gpsType + ", otherFeatures=" + otherFeatures
				+ ", usbAvailable=" + isUsbAvailable() + ", usbType=" + usbType + ", wifiType=" + wifiType
				+ ", wifiAvailable=" + isWifiAvailable() + "]";
	}
}
