package com.donateknowledge.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_CELL_PHONES;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_CELL_PHONES_INDEX;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.donateknowledge.configurator.ApplicationConfigurator;
import com.donateknowledge.dao.ICellPhoneDAO;
import com.donateknowledge.dto.compare.ICompareProduct;
import com.donateknowledge.dto.product.phone.Phone;
import com.donateknowledge.dto.product.phone.PhoneFinder;
import com.donateknowledge.utils.CheapestGadgetUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

@Repository
public class CellPhoneDAOImpl implements ICellPhoneDAO  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CellPhoneDAOImpl.class);

	private final MongoCollection<Document> collection;

	@Autowired(required = true)
	public CellPhoneDAOImpl(ApplicationConfigurator appConfig) {

		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_CELL_PHONES));
		String[] indexes = appConfig.getProperty(MONGODB_COLLECTION_CELL_PHONES_INDEX).split(";");
		for (String itr : indexes) {
			collection.createIndex(Document.parse(itr));
		}
	}

	@Override
	public boolean insertCellPhone(Phone cellPhone) throws JsonProcessingException {

		try {
			String jsonInString = CheapestGadgetUtils.javaToJson(cellPhone);
			Document dbObject = Document.parse(jsonInString);
			//collection.deleteOne(eq("_id.productName", "iphone6"));
			collection.insertOne(dbObject);
			return true;
		} catch (JsonProcessingException e) {
			LOGGER.error(MessageFormat.format("Exception occurred while converting java to json, java object: {0}\nException: {1}", cellPhone, e));
			return false; //throw e;
		} catch (MongoWriteException e) {
			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				LOGGER.error("Details already entered: " + cellPhone.getProductId());
				return false;
			}
			LOGGER.error("Exception occurred: " + e.getMessage());
			return false;
			//throw e;
		}
	}

	@Override
	public List<Phone> fetchCellPhoneByTextIndex(String searchArr, int skip, int limit, boolean fetchImage, ICompareProduct comparator) throws Exception {

		List<Phone> phoneList = null;
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
				phoneList.add(CheapestGadgetUtils.jsonToJava(CheapestGadgetUtils.javaToJson(itr), Phone.class));
			}

			if (comparator != null && !CollectionUtils.isEmpty(phoneList)) {
				Collections.sort(phoneList, comparator);
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
	public List<Phone> fetchCellPhoneByRegex(String[] searchArr, int skip, int limit, boolean fetchImage, ICompareProduct comparator) throws Exception {
		List<Phone> phoneList = null;
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
				phoneList.add(CheapestGadgetUtils.jsonToJava(CheapestGadgetUtils.javaToJson(itr), Phone.class));
			}

			if (comparator != null && !CollectionUtils.isEmpty(phoneList)) {
				Collections.sort(phoneList, comparator);
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
	public Phone fetchCellPhoneById(String str) throws Exception {

		try {
			Document update = Document.parse("{\"$inc\" : {\"totalHitCount\":1}}");
			FindOneAndUpdateOptions updateOptions = new FindOneAndUpdateOptions();
			updateOptions.upsert(false);
			updateOptions.returnDocument(ReturnDocument.AFTER);
			Document cellPhone = collection.findOneAndUpdate(eq("_id", str), update, updateOptions );
			if (cellPhone != null) {
				return CheapestGadgetUtils.jsonToJava(CheapestGadgetUtils.javaToJson(cellPhone), Phone.class);
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

	@Override
	public PhoneFinder updateCache() throws Exception {
		PhoneFinder phoneFinder = new PhoneFinder();
		FindIterable<Document> phones = collection.find();

		for (Document itr : phones) {
			Phone phone = CheapestGadgetUtils.jsonToJava(CheapestGadgetUtils.javaToJson(itr), Phone.class);
			phoneFinder.getManufacturerList().add(phone.getManufacturer());
			if (phoneFinder.getMinPrice() == null || phone.getMrpPrice().compareTo(phoneFinder.getMinPrice()) == -1) {
				phoneFinder.setMinPrice(phone.getListPrice());
			}
			if (phoneFinder.getMaxPrice() == null || phone.getMrpPrice().compareTo(phoneFinder.getMaxPrice()) == 1) {
				phoneFinder.setMaxPrice(phone.getMrpPrice());
			}
			if (phoneFinder.getMinRam() == null || phone.getModels().get(0).getMemory().getRam().compareTo(phoneFinder.getMinRam()) == -1) {
				phoneFinder.setMinRam(phone.getModels().get(0).getMemory().getRam());
			}
			if (phoneFinder.getMaxRam() == null || phone.getModels().get(0).getMemory().getRam().compareTo(phoneFinder.getMaxRam()) == 1) {
				phoneFinder.setMaxRam(phone.getModels().get(0).getMemory().getRam());
			}
			if (phoneFinder.getMinScreenSize() == null || phone.getModels().get(0).getDisplay().getSize().compareTo(phoneFinder.getMinScreenSize()) == -1) {
				phoneFinder.setMinScreenSize(phone.getModels().get(0).getDisplay().getSize());
			}
			if (phoneFinder.getMaxScreenSize() == null || phone.getModels().get(0).getDisplay().getSize().compareTo(phoneFinder.getMaxScreenSize()) == 1) {
				phoneFinder.setMaxScreenSize(phone.getModels().get(0).getDisplay().getSize());
			}
			phoneFinder.getOsList().add(phone.getModels().get(0).getOs().getOsName());
		}
		return phoneFinder;
	}
}
