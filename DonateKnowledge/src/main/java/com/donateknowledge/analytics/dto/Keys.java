package com.donateknowledge.analytics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Keys {
	
	private String keys;
	private boolean exists;
	
	public Keys(String keys, boolean exists) {
		super();
		this.keys = keys;
		this.exists = exists;
	}
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public boolean isExists() {
		return exists;
	}
	public void setExists(boolean exists) {
		this.exists = exists;
	}
}
