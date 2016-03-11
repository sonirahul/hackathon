package com.donateknowledge.analytics.dto;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductHitCount {

	private String productId;
	private Date date;
	private BigInteger hits;

	public ProductHitCount(String productId, Date date, BigInteger hits) {
		super();
		this.productId = productId;
		this.date = date;
		this.hits = hits;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigInteger getHits() {
		return hits;
	}
	public void setHits(BigInteger hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "HitsPerDay [productId=" + productId + ", date=" + date
				+ ", hits=" + hits + "]";
	}
}
