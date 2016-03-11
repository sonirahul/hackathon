package com.donateknowledge.dto.product;

import static com.donateknowledge.utils.CheapestGadgetUtils.getDateTimeToday;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.donateknowledge.dto.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true, 
value={"discountAmount", "discountPercentage"})
public class Product {

	private String available;
	private ProductBody body;
	private BigDecimal discountAmount;
	private BigDecimal discountPercentage;
	private String insertedBy;
	private Date insertedDate;
	private BigDecimal listPrice;
	private String manufacturer;
	private String modifiedBy;
	private Date modifiedDate;
	private BigDecimal mrpPrice;
	private BigInteger piecesInStock;
	private BigInteger piecesSold;
	private String productCategory;
	@JsonProperty("_id")
	private String productId;
	private String productImage;
	private String productName;
	private Date releasedDate;
	private BigInteger totalHitCount = BigInteger.ZERO;

	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		Availability availablity = Availability.getAvailability(available);
		if (availablity != null) {
			this.available = availablity.getValue();
		}
	}
	public ProductBody getBody() {
		return body;
	}
	public void setBody(ProductBody body) {
		this.body = body;
	}
	public BigDecimal getDiscountAmount() {
		if (mrpPrice != null && listPrice != null) {
			discountAmount = mrpPrice.subtract(listPrice);
		}
		else {
			discountAmount = BigDecimal.ZERO;
		}
		return discountAmount;
	}
	public BigDecimal getDiscountPercentage() {
		if ((getDiscountAmount().compareTo(BigDecimal.ZERO) ==1) 
				&& mrpPrice != null && (mrpPrice.compareTo(BigDecimal.ZERO) == 1)) {
			discountPercentage = getDiscountAmount().multiply(new BigDecimal(100)).divide(mrpPrice, 2, RoundingMode.FLOOR);
		}
		else {
			discountPercentage = BigDecimal.ZERO;
		}
		return discountPercentage;
	}
	public String getInsertedBy() {
		if (StringUtils.isEmpty(insertedBy)) {
			insertedBy = UserRole.SYSTEM.getValue();
			setModifiedBy(insertedBy);
		}
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedDate() {
		if (insertedDate == null) {
			insertedDate = getDateTimeToday();
			//TODO : 
			setModifiedDate(insertedDate);
		}
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	public BigDecimal getListPrice() {
		return listPrice;
	}
	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	public BigInteger getPiecesSold() {
		return piecesSold;
	}
	public void setPiecesSold(BigInteger piecesSold) {
		this.piecesSold = piecesSold;
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
		if (StringUtils.isNotEmpty(this.manufacturer) 
				&& StringUtils.isNotEmpty(this.productName)) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.manufacturer).append(" ").append(this.productName);
			productId = sb.toString();
		}
		return productId;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}
	public BigInteger getTotalHitCount() {
		return totalHitCount;
	}
	public void setTotalHitCount(BigInteger totalHitCount) {
		this.totalHitCount = totalHitCount;
	}

	@Override
	public String toString() {
		return "Product [available=" + available + ", body=" + body + ", insertedBy=" + insertedBy
				+ ", insertedDate=" + insertedDate + ", listPrice=" + listPrice + ", manufacturer="
				+ manufacturer + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", mrpPrice=" + mrpPrice + ", piecesInStock=" + piecesInStock + ", piecesSold=" + piecesSold
				+ ", productCategory=" + productCategory + ", productId=" + productId + ", productName=" + productName
				+ ", releasedDate=" + releasedDate + ", totalHitCount=" + totalHitCount + ", discountAmount="
				+ getDiscountAmount() + ", discountPercentage=" + getDiscountPercentage() + "]";
	}
}
