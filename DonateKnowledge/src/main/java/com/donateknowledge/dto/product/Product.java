package com.donateknowledge.dto.product;

import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateTimeToday;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	private String insertedBy;
	private Date insertedDate;
	private BigDecimal mrpPrice;
	private BigInteger piecesInStock;
	private String productCategory;
	@JsonProperty("_id")
	private String productId; //ISBN in case of books
	private String productImage;
	
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedDate() {
		if (insertedDate == null) {
			insertedDate = getDateTimeToday();
		}
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	public BigDecimal getMrpPrice() {
		return mrpPrice;
	}
	public void setMrpPrice(BigDecimal mrpPrice) {
		this.mrpPrice = mrpPrice;
	}
	public BigInteger getPiecesInStock() {
		return piecesInStock;
	}
	public void setPiecesInStock(BigInteger piecesInStock) {
		this.piecesInStock = piecesInStock;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		ProductCategory category = ProductCategory.getProductCategory(productCategory);
		if (category != null) {
			this.productCategory = category.getValue();
		}
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
