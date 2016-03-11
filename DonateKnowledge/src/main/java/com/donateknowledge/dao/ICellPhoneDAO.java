package com.donateknowledge.dao;

import java.util.List;

import com.donateknowledge.dto.compare.ICompareProduct;
import com.donateknowledge.dto.product.phone.Phone;
import com.donateknowledge.dto.product.phone.PhoneFinder;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICellPhoneDAO {

	boolean insertCellPhone(Phone cellPhone) throws JsonProcessingException;

	Phone fetchCellPhoneById(String str) throws Exception;

	List<Phone> fetchCellPhoneByRegex(String[] searchArr, int skip, int limit, boolean fetchImage, ICompareProduct comparator)
			throws Exception;

	List<Phone> fetchCellPhoneByTextIndex(String searchArr, int skip, int limit, boolean fetchImage, ICompareProduct comparator)
			throws Exception;

	PhoneFinder updateCache() throws Exception;
}
