package com.donateknowledge.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.EMAIL;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_SESSIONS;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_FIELD_ID;
import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateTimeToday;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.configurator.ApplicationConfigurator;
import com.donateknowledge.dao.ISessionDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;

@Repository
public class SessionDAOImpl implements ISessionDAO {

	private final MongoCollection<Document> collection;

	@Autowired
	public SessionDAOImpl(ApplicationConfigurator appConfig) {
		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_SESSIONS));
	}

	@Override
	public String findUserEmailBySessionId(String sessionID) {
		Document session = collection.find(eq(MONGODB_FIELD_ID, sessionID))
				.projection(Document.parse("{\"email\" : 1}")).first();
		return session == null ? null : session.get(EMAIL).toString();
	}

	@Override
	public boolean insertSession(String cookieValue) {

		Document session;
		try {
			session = new Document(MONGODB_FIELD_ID, cookieValue).append("createdOn", getDateTimeToday().getTime());
			collection.insertOne(session);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateSession(String cookieValue, String email) {


		try {
			Document update = Document.parse("{\"$set\" : {\""+ EMAIL + "\" : \"" + email + "\"}}");
			UpdateOptions options = new UpdateOptions();
			options.upsert(true);
			collection.updateOne(eq(MONGODB_FIELD_ID, cookieValue), update, options);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	// ends the session by deleting it from the sesisons table
	public void endSession(String sessionID) {
		collection.deleteOne(eq(MONGODB_FIELD_ID, sessionID));
	}
}
