package com.donateknowledge.dto.compare.impl;

import com.donateknowledge.dto.compare.ICompareProduct;
import com.donateknowledge.dto.product.phone.Phone;

public class CompareProductByPopularity implements ICompareProduct {

	@Override
	public int compare(Phone o1, Phone o2) {
		return o2.getTotalHitCount().compareTo(o1.getTotalHitCount());
	}
}
