package com.donateknowledge.dao;

import java.util.List;

import com.donateknowledge.dto.product.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IProductDAO {

	boolean insertCellPhone(Product cellPhone) throws JsonProcessingException;

	Product fetchCellPhoneById(String str) throws Exception;

	List<Product> fetchCellPhoneByRegex(String[] searchArr, int skip, int limit, boolean fetchImage)
			throws Exception;

	List<Product> fetchCellPhoneByTextIndex(String searchArr, int skip, int limit, boolean fetchImage)
			throws Exception;
}
