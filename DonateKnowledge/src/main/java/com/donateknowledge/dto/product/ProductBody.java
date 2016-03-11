package com.donateknowledge.dto.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.donateknowledge.utils.CheapestGadgetUtils;

public class ProductBody {

	private List<String> colors;
	private String dimensionUnit;
	private BigDecimal height;
	// Thickness
	private BigDecimal length;
	private List<String> materials;
	private BigDecimal weight;
	private String weightUnit;
	private BigDecimal width;

	public ProductBody() {
		super();
	}

	public ProductBody(String dimensionUnit, String height, String length,
			String weight, String weightUnit, String width) {
		super();
		this.dimensionUnit = dimensionUnit;
		this.height = CheapestGadgetUtils.stringToBigDecimal(height);
		this.length = CheapestGadgetUtils.stringToBigDecimal(length);
		this.weight = CheapestGadgetUtils.stringToBigDecimal(weight);
		this.weightUnit = weightUnit;
		this.width = CheapestGadgetUtils.stringToBigDecimal(width);
	}

	public List<String> getColors() {
		if(colors == null) {
			colors = new ArrayList<>();
		}
		return colors;
	}
	public String getDimensionUnit() {
		return dimensionUnit;
	}
	public void setDimensionUnit(String dimensionUnit) {
		DimensionUnit unit = DimensionUnit.getDimensionUnit(dimensionUnit);
		if (unit != null) {
			this.dimensionUnit = unit.getValue();
		}
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public List<String> getMaterials() {
		if(materials == null) {
			materials = new ArrayList<>();
		}
		return materials;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		WeightUnit unit = WeightUnit.getWeightUnit(weightUnit);
		if (unit != null) {
			this.weightUnit = unit.getValue();
		}
	}
	public BigDecimal getWidth() {
		return width;
	}
	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "ProductBody [colors=" + colors + ", dimensionUnit=" + dimensionUnit + ", height=" + height + ", length="
				+ length + ", materials=" + materials + ", weight=" + weight + ", weightUnit=" + weightUnit + ", width="
				+ width + "]";
	}
}
