package com.donateknowledge.dto.product.phone.network;

import static com.donateknowledge.utils.CheapestGadgetUtils.getEnumMappedList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public class Network {

	private boolean edge;
	private boolean gprs;
	private List<String> band2G;
	private List<String> band3G;
	private List<String> band3_5G;
	private List<String> bandLTE;
	private List<String> speed;

	public boolean isEdge() {
		return edge;
	}
	public void setEdge(boolean edge) {
		this.edge = edge;
	}
	public boolean isGprs() {
		return gprs;
	}
	public void setGprs(boolean gprs) {
		this.gprs = gprs;
	}
	public List<String> getBand2G() {
		band2G = getEnumMappedList(band2G, asList(NetworkType.values()));
		return band2G;
	}
	public List<String> getBand3G() {
		band3G = getEnumMappedList(band3G, asList(NetworkType.values()));
		return band3G;
	}
	public List<String> getBand3_5G() {
		band3_5G = getEnumMappedList(band3_5G, asList(NetworkType.values()));
		return band3_5G;
	}
	public List<String> getBandLTE() {
		bandLTE = getEnumMappedList(bandLTE, asList(NetworkType.values()));
		return bandLTE;
	}
	public List<String> getSpeed() {
		if (speed == null) {
			speed = new ArrayList<>();
		}
		return speed;
	}

	@Override
	public String toString() {
		return "Network [edge=" + edge + ", gprs=" + gprs + ", band2G=" + band2G + ", band3G=" + band3G + ", band3_5G="
				+ band3_5G + ", bandLTE=" + bandLTE + ", speed=" + speed + "]";
	}
}
