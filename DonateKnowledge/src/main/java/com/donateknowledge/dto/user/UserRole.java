package com.donateknowledge.dto.user;

import com.donateknowledge.dto.product.EnumInterface;

public enum UserRole implements EnumInterface {
	ADMIN("admin"),
	DBA("dba"),
	USER("user"),
	SYSTEM("system");

	private final String value;

	private UserRole(String value) {
		this.value = value;
	}

	public static UserRole getUserRole(String string) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.getValue().equals(string)) {
				return userRole;
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
