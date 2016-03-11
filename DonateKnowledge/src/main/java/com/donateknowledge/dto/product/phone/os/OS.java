package com.donateknowledge.dto.product.phone.os;

public class OS {

	private String osName;
	private String osVersion;
	private String osVersionUpgradeableTo;

	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getOsVersionUpgradeableTo() {
		return osVersionUpgradeableTo;
	}
	public void setOsVersionUpgradeableTo(String osVersionUpgradeableTo) {
		this.osVersionUpgradeableTo = osVersionUpgradeableTo;
	}
	@Override
	public String toString() {
		return "OS [os=" + osName + ", osVersion=" + osVersion + ", osVersionUpgradeableTo=" + osVersionUpgradeableTo + "]";
	}
}