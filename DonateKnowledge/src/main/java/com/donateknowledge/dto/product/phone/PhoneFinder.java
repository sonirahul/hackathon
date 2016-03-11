package com.donateknowledge.dto.product.phone;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class PhoneFinder {

	Set<String> manufacturerList;
	BigDecimal minPrice;
	BigDecimal maxPrice;
	BigDecimal minRam;
	BigDecimal maxRam;
	BigDecimal minResolution;
	BigDecimal maxResolution;
	BigDecimal minScreenSize;
	BigDecimal maxScreenSize;
	Set<String> osList;

	public Set<String> getManufacturerList() {
		if (manufacturerList == null) {
			manufacturerList = new LinkedHashSet<>();
		}
		return manufacturerList;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public BigDecimal getMinRam() {
		return minRam;
	}
	public void setMinRam(BigDecimal minRam) {
		this.minRam = minRam;
	}
	public BigDecimal getMaxRam() {
		return maxRam;
	}
	public void setMaxRam(BigDecimal maxRam) {
		this.maxRam = maxRam;
	}
	public BigDecimal getMinResolution() {
		return minResolution;
	}
	public void setMinResolution(BigDecimal minResolution) {
		this.minResolution = minResolution;
	}
	public BigDecimal getMaxResolution() {
		return maxResolution;
	}
	public void setMaxResolution(BigDecimal maxResolution) {
		this.maxResolution = maxResolution;
	}
	public BigDecimal getMinScreenSize() {
		return minScreenSize;
	}
	public void setMinScreenSize(BigDecimal minScreenSize) {
		this.minScreenSize = minScreenSize;
	}
	public BigDecimal getMaxScreenSize() {
		return maxScreenSize;
	}
	public void setMaxScreenSize(BigDecimal maxScreenSize) {
		this.maxScreenSize = maxScreenSize;
	}
	public Set<String> getOsList() {
		if (osList == null) {
			osList = new LinkedHashSet<>();
		}
		return osList;
	}

	@Override
	public String toString() {
		return "PhoneFinder [manufacturerList=" + manufacturerList + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", minRam=" + minRam + ", maxRam=" + maxRam + ", minResolution=" + minResolution + ", maxResolution="
				+ maxResolution + ", minScreenSize=" + minScreenSize + ", maxScreenSize=" + maxScreenSize + ", os=" + osList
				+ "]";
	}
}
