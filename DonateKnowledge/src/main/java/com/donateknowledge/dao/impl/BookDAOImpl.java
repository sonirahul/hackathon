package com.donateknowledge.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_CELL_PHONES;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_CELL_PHONES_INDEX;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.configurator.ApplicationConfigurator;
import com.donateknowledge.dao.IProductDAO;
import com.donateknowledge.dto.product.Product;
import com.donateknowledge.dto.product.book.Book;
import com.donateknowledge.utils.DonateKnowledgeUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

@Repository
public class BookDAOImpl implements IProductDAO  {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookDAOImpl.class);

	private final MongoCollection<Document> collection;

	@Autowired(required = true)
	public BookDAOImpl(ApplicationConfigurator appConfig) {

		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_CELL_PHONES));
		String[] indexes = appConfig.getProperty(MONGODB_COLLECTION_CELL_PHONES_INDEX).split(";");
		for (String itr : indexes) {
			collection.createIndex(Document.parse(itr));
		}
	}

	@Override
	public boolean insertProduct(Product book) throws JsonProcessingException {

		try {
			String jsonInString = DonateKnowledgeUtils.javaToJson(book);
			Document dbObject = Document.parse(jsonInString);
			//collection.deleteOne(eq("_id.productName", "iphone6"));
			collection.insertOne(dbObject);
			return true;
		} catch (JsonProcessingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred while converting java to json, java object: {0}\nException: {1}", book, e));
			return false; //throw e;
		} catch (MongoWriteException e) {
			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				LOGGER.error("Details already entered: " + book.getProductId());
				return false;
			}
			LOGGER.error("Exception occurred: " + e.getMessage());
			return false;
			//throw e;
		}
	}

	@Override
	public List<Product> fetchCellPhoneByTextIndex(String searchArr, int skip, int limit, boolean fetchImage) throws Exception {

		List<Product> phoneList = null;
		try {

			Set<Document> cellPhoneList = null;
			if (fetchImage) {
				cellPhoneList = collection.find(Document.parse("{$text:{$search:'" + searchArr + "'}}"))
						.projection(Document.parse("{score: {'$meta': \"textScore\"}}"))
						.sort(Document.parse("{score: {'$meta': \"textScore\"}}"))
						.skip(skip).limit(limit).into(new LinkedHashSet<Document>());
			} else {
				cellPhoneList = collection.find(Document.parse("{$text:{$search:'" + searchArr + "'}}"))
						.projection(Document.parse("{\"productImage\": 0 }"))
						.projection(Document.parse("{score: {'$meta': \"textScore\"}}"))
						.sort(Document.parse("{score: {'$meta': \"textScore\"}}"))
						.skip(skip).limit(limit).into(new LinkedHashSet<Document>());
			}

			phoneList = new ArrayList<>();
			for (Document itr: cellPhoneList) {
				phoneList.add(DonateKnowledgeUtils.jsonToJava(DonateKnowledgeUtils.javaToJson(itr), Book.class));
			}

			

			return phoneList;
		} catch (JsonParseException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (JsonMappingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (JsonProcessingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (IOException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		}

	}

	@Override
	public List<Product> fetchCellPhoneByRegex(String[] searchArr, int skip, int limit, boolean fetchImage) throws Exception {
		List<Product> phoneList = null;
		try {

			Document[] docArr = new Document[searchArr.length];

			int i = 0;
			for (String itr : searchArr) {
				docArr[i] = Document.parse("{\"_id\": {$regex : \"" + itr + "\"} }");
				i = i + 1;
			}

			Set<Document> cellPhoneList = null;
			if (fetchImage) {
				cellPhoneList = collection.find(or(docArr))
						.skip(skip).limit(limit).into(new LinkedHashSet<Document>());
			} else {
				cellPhoneList = collection.find(or(docArr)).projection(Document.parse("{\"productImage\": 0 }"))
						.skip(skip).limit(limit).into(new LinkedHashSet<Document>());
			}

			phoneList = new ArrayList<>();
			for (Document itr: cellPhoneList) {
				phoneList.add(DonateKnowledgeUtils.jsonToJava(DonateKnowledgeUtils.javaToJson(itr), Book.class));
			}

			return phoneList;
		} catch (JsonParseException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (JsonMappingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (JsonProcessingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (IOException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		}
	}

	@Override
	public Book fetchBookById(String str) throws Exception {

		try {
			Document update = Document.parse("{\"$inc\" : {\"totalHitCount\":1}}");
			FindOneAndUpdateOptions updateOptions = new FindOneAndUpdateOptions();
			updateOptions.upsert(false);
			updateOptions.returnDocument(ReturnDocument.AFTER);
			Document book = collection.findOneAndUpdate(eq("_id", str), update, updateOptions );
			if (book != null) {
				return DonateKnowledgeUtils.jsonToJava(DonateKnowledgeUtils.javaToJson(book), Book.class);
			}
			else {
				return null;
			}
		} catch (JsonParseException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (JsonMappingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (JsonProcessingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		} catch (IOException e) {
			LOGGER.error(MessageFormat.format("Exception occurred in fetchCellPhone().\nException: {0}", e));
			throw e;
		}
	}
}
