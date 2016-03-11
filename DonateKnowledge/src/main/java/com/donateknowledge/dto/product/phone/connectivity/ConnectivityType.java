package com.donateknowledge.dto.product.phone.connectivity;

import com.donateknowledge.dto.product.EnumInterface;

public enum ConnectivityType implements EnumInterface {

	AIR_DROP("Air Drop"),
	BLUETOOTH_V2_0("bluetooth v2.0"),
	BLUETOOTH_V2_1("bluetooth v2.1"),
	BLUETOOTH_V3_0("bluetooth v3.0"),
	BLUETOOTH_V4_0("bluetooth v4.0"),
	BLUETOOTH_V4_1("bluetooth v4.1"),
	BLUETOOTH_V4_2("bluetooth v4.2"),
	COMPUTER_SYNC("Computer Sync"),
	GPS_A("A-Gps"),
	GPS_CELL_ID("Cell Id"),
	GPS_GLONASS("Glonass"),
	GPS_WIFI_POSITIONING("Wifi Positioning"),
	NFC("Nfc"),
	OTA_SYNC("Ota Sync"),
	TETHERING("tethering"),
	UMA("Wifi calling"),
	USB_1_0("usb 1.0"),
	USB_2_0("usb 2.0"),
	USB_3_0("usb 3.0"),
	USB_3_1("usb 3.1"),
	WIFI_802_11A("802.11a"),
	WIFI_802_11B("802.11b"),
	WIFI_802_11G("802.11g"),
	WIFI_802_11N("802.11n"),
	WIFI_AC("802.11ac"),
	WIFI_DIRECT("wifi direct"),
	WIFI_HOTSPOT("wifi Hot Spot");


	private final String value;

	private ConnectivityType(String value) {
		this.value = value;
	}

	public static ConnectivityType getConnectivityType(String string) {
		for (ConnectivityType type : ConnectivityType.values()) {
			if (type.getValue().equals(string)) {
				return type;
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
