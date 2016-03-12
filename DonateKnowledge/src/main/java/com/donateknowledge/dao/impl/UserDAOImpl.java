package com.donateknowledge.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.ENCRYPTION_SALT;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_USERS;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_USERS_INDEX;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_FIELD_ID;
import static com.donateknowledge.utils.DonateKnowledgeUtils.generateCustomErrorMessage;
import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateTimeToday;
import static com.donateknowledge.utils.DonateKnowledgeUtils.javaToJson;
import static com.donateknowledge.utils.DonateKnowledgeUtils.jsonToJava;
import static com.donateknowledge.utils.DonateKnowledgeUtils.makePasswordHash;
import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.math.BigInteger;
import java.text.MessageFormat;

import org.bson.Document;
import org.bson.json.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.configurator.ApplicationConfigurator;
import com.donateknowledge.dao.IUserDAO;
import com.donateknowledge.dto.user.User;
import com.donateknowledge.utils.DonateKnowledgeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.ReturnDocument;

@Repository
public class UserDAOImpl implements IUserDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

	private final MongoCollection<Document> collection;
	@Autowired ApplicationConfigurator appConfig;

	@Autowired(required = true)
	public UserDAOImpl(ApplicationConfigurator appConfig) {
		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_USERS));
		IndexOptions indexOptions = new IndexOptions();
		indexOptions.unique(true);
		indexOptions.sparse(true);
		collection.createIndex(Document.parse(appConfig.getProperty(MONGODB_COLLECTION_USERS_INDEX)), indexOptions);
	}

	@Override
	public boolean insertUser(User user, boolean updateLastLogin) throws Exception {
		try {
			user.setPassword(makePasswordHash(user.getPassword(), appConfig.getProperty(ENCRYPTION_SALT)));
			if (updateLastLogin) {
				user.setLastLogin(getDateTimeToday());
			}
			String jsonInString = javaToJson(user);
			Document dbObject = Document.parse(jsonInString);
			collection.insertOne(dbObject);
			return true;
		} catch (JsonProcessingException e) {
			LOGGER.error("Exception Occured: " + e);
			throw e;
		} catch (MongoWriteException e) {
			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				LOGGER.error(generateCustomErrorMessage(e.getMessage()));
				return false;
			}
			throw e;
		}
	}

	@Override
	public User fetchUserByEmail(String email, boolean updateLastLogin) throws Exception {
		try {
			Document user = null;
			if (updateLastLogin) {
				String json = "{\"$set\": {\"lastLogin\":" + javaToJson(getDateTimeToday()) + "}}";
				Document update = Document.parse(json);
				user = collection.findOneAndUpdate(eq(MONGODB_FIELD_ID, email), update);
			}
			else {
				user = collection.find(eq(MONGODB_FIELD_ID, email)).first();
			}
			if (user != null) {
				return jsonToJava(javaToJson(user), User.class);
			}
			else {
				return null;
			}
		} catch (JsonParseException e) {
			LOGGER.error("Exception Occured: " + e);
			throw e;
		} catch (JsonMappingException e) {
			LOGGER.error("Exception Occured: " + e);
			throw e;
		} catch (JsonProcessingException e) {
			LOGGER.error("Exception Occured: " + e);
			throw e;
		} catch (IOException e) {
			LOGGER.error("Exception Occured: " + e);
			throw e;
		}
	}

	@Override
	public User validateUser(User user, boolean updateLastLogin) {

		try {
			Document dbUser = null;

			Document query = new Document("_id", user.getEmail())
					.append("password", makePasswordHash(user.getPassword(), appConfig.getProperty(ENCRYPTION_SALT)));

			if (updateLastLogin) {
				String json = "{\"$set\": {\"lastLogin\":" + javaToJson(getDateTimeToday()) + "}}";
				Document update = Document.parse(json);
				
				dbUser = collection.findOneAndUpdate(query, update);
			}
			else {
				dbUser = collection.find(query).first();
			}

			if (dbUser == null) {
				LOGGER.debug("User not in database");
				return null;
			}

			return jsonToJava(javaToJson(dbUser), User.class);
		} catch (com.fasterxml.jackson.core.JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateUserPoints(String email, BigInteger points) throws Exception {
		try {
			Document update = Document.parse("{\"$inc\" : {\"points\": " + points + "}}" );
			FindOneAndUpdateOptions updateOptions = new FindOneAndUpdateOptions();
			updateOptions.upsert(false);
			updateOptions.returnDocument(ReturnDocument.AFTER);
			Document book = collection.findOneAndUpdate(eq("_id", email), update, updateOptions );
			if (book != null) {
				return DonateKnowledgeUtils.jsonToJava(DonateKnowledgeUtils.javaToJson(book), User.class);
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
