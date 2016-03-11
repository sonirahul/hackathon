package com.donateknowledge.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_IMAGES;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.configurator.ApplicationConfigurator;
import com.donateknowledge.dao.IProductImageDAO;
import com.donateknowledge.dto.product.ProductImage;
import com.donateknowledge.utils.CheapestGadgetUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;

@Repository
public class ProductImageDAOImpl implements IProductImageDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageDAOImpl.class);

	private final MongoCollection<Document> collection;

	@Autowired(required = true)
	public ProductImageDAOImpl(ApplicationConfigurator appConfig) {
		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_IMAGES));
	}

	@Override
	public boolean insertProductImage(ProductImage image) throws Exception {
		try {
			String jsonInString = CheapestGadgetUtils.javaToJson(image);
			Document dbObject = Document.parse(jsonInString);
			collection.insertOne(dbObject);
			return true;
		} catch (JsonProcessingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred while converting java to json"
					+ ", java object: {0}\nException: {1}", image, e));
			throw e;
		} catch (MongoWriteException e) {
			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				LOGGER.error("Details already entered: " + image.getProductId());
				return false;
			}
			LOGGER.error("Exception occurred: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public ProductImage fetchProductImage(String productId) throws Exception {

			try {
				List<Document> productImagesList = collection.find(Document.parse("{\"_id\" : \"" + productId + "\"}"))
						.into(new ArrayList<Document>());

				return CheapestGadgetUtils.jsonToJava(
						CheapestGadgetUtils.javaToJson(productImagesList.get(0)), ProductImage.class);
			} catch (JsonParseException e) {
				LOGGER.error("Exception occurred: " + e.getMessage());
				throw e;
			} catch (JsonMappingException e) {
				LOGGER.error("Exception occurred: " + e.getMessage());
				throw e;
			} catch (JsonProcessingException e) {
				LOGGER.error("Exception occurred: " + e.getMessage());
				throw e;
			} catch (IOException e) {
				LOGGER.error("Exception occurred: " + e.getMessage());
				throw e;
			}
	}
}
