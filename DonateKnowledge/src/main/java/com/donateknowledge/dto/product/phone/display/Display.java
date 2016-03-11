package com.donateknowledge.dto.product.phone.display;

import static com.donateknowledge.utils.CheapestGadgetUtils.getEnumMappedList;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.List;

import com.donateknowledge.dto.product.phone.Features;
import com.donateknowledge.utils.CheapestGadgetUtils;

public class Display {

	private BigDecimal colors;
	private List<String> features;
	private boolean multiTouch;
	private String pixelDensity;
	private String resolution;
	private BigDecimal screenBodyRatio;
	private BigDecimal size;
	private String technology;

	public Display() {
		super();
	}

	public Display(String colors, boolean multiTouch, String pixelDensity, String resolution,
			String screenBodyRatio, String size, String technology) {
		super();
		this.colors = CheapestGadgetUtils.stringToBigDecimal(colors);
		this.multiTouch = multiTouch;
		this.pixelDensity = pixelDensity;
		this.resolution = resolution;
		this.screenBodyRatio = CheapestGadgetUtils.stringToBigDecimal(screenBodyRatio);
		this.size = CheapestGadgetUtils.stringToBigDecimal(size);
		this.technology = technology;
	}
	public BigDecimal getColors() {
		return colors;
	}
	public void setColors(BigDecimal colors) {
		this.colors = colors;
	}
	public boolean isMultiTouch() {
		return multiTouch;
	}
	public void setMultiTouch(boolean multiTouch) {
		this.multiTouch = multiTouch;
	}
	public String getPixelDensity() {
		return pixelDensity;
	}
	public void setPixelDensity(String pixelDensity) {
		this.pixelDensity = pixelDensity;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public BigDecimal getScreenBodyRatio() {
		return screenBodyRatio;
	}
	public void setScreenBodyRatio(BigDecimal screenBodyRatio) {
		this.screenBodyRatio = screenBodyRatio;
	}
	public BigDecimal getSize() {
		return size;
	}
	public void setSize(BigDecimal size) {
		this.size = size;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		DisplayType type = DisplayType.getDisplayType(technology);
		if (type != null) {
			this.technology = type.getValue();
		}
	}
	public List<String> getFeatures() {
		features = getEnumMappedList(features, asList(Features.values()));
		return features;
	}

	@Override
	public String toString() {
		return "Display [colors=" + colors + ", features=" + features + ", multiTouch=" + multiTouch + ", pixelDensity="
				+ pixelDensity + ", resolution=" + resolution + ", screenBodyRatio="
				+ screenBodyRatio + ", size=" + size + ", technology=" + technology + "]";
	}
}
