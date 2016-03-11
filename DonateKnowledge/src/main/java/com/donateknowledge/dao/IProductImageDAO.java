package com.donateknowledge.dao;

import com.donateknowledge.dto.product.ProductImage;

public interface IProductImageDAO {

	boolean insertProductImage(ProductImage image) throws Exception;

	ProductImage fetchProductImage(String productId) throws Exception;
}
