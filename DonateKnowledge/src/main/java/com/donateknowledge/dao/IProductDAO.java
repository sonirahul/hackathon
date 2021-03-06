package com.donateknowledge.dao;

import java.util.List;

import com.donateknowledge.dto.product.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IProductDAO {

	boolean insertProduct(Product cellPhone) throws JsonProcessingException;

	Product fetchBookById(String str) throws Exception;

	List<Product> fetchAllBooks() throws Exception;
	
	List<Product> fetchAllBooksByInsertId(String str) throws Exception;

	List<Product> fetchCellPhoneByRegex(String[] searchArr, int skip, int limit, boolean fetchImage)
			throws Exception;

	List<Product> fetchCellPhoneByTextIndex(String searchArr, int skip, int limit, boolean fetchImage)
			throws Exception;
	
	Product markSold(String isbn) throws Exception;

	Product tempBlockBook(String email, String isbn) throws Exception;
}
