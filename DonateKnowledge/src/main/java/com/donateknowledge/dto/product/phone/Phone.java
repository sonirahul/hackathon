package com.donateknowledge.dto.product.phone;

import java.util.ArrayList;
import java.util.List;

import com.donateknowledge.dto.product.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Phone extends Product {

	private List<PhoneModel> models;

	public List<PhoneModel> getModels() {
		if (models == null) {
			models = new ArrayList<>();
		}
		return models;
	}
	@Override
	public String toString() {
		return "Phone [models=" + models + ", toString()=" + super.toString() + "]";
	}
}
