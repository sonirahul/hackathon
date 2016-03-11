package com.donateknowledge.dto.product;

public enum ProductCategory implements EnumInterface {
	MOBILE("Mobile"),
	LANDWIRED("Land Wired"),
	LANDWIRELESS("Land Wireless");

	private final String value;

	private ProductCategory(String value) {
		this.value = value;
	}

	public static ProductCategory getProductCategory(String string) {
		for (ProductCategory category : ProductCategory.values()) {
			if (category.getValue().equals(string)) {
				return category;
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
