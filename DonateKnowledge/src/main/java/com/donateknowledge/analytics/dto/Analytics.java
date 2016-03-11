package com.donateknowledge.analytics.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Analytics {

	@JsonProperty("_id")
	private Date date;
	private List<Keys> keyWords;
	private List<User> user;

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Keys> getKeyWords() {
		if (keyWords == null){
			keyWords = new ArrayList<>();
		}
		return keyWords;
	}
	public List<User> getUser() {
		if (user == null) {
			user = new ArrayList<>();
		}
		return user;
	}

	@Override
	public String toString() {
		return "Analytics [date=" + date + ", keyWords=" + keyWords + ", user=" + user + "]";
	}
}
