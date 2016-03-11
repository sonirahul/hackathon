package com.donateknowledge.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductImage {

	private List<String> otherImages;
	@JsonProperty("_id")
	private String productId;

	public List<String> getOtherImages() {
		if (otherImages == null) {
			otherImages = new ArrayList<>();
		}
		return otherImages;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}
