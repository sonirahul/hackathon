package com.donateknowledge.analytics.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_ANALYTICS_KEYWORDS;
import static com.donateknowledge.constant.ApplicationConstants.MONTH_AGO;
import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateNDaysFromToday;
import static com.donateknowledge.utils.DonateKnowledgeUtils.javaToJson;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.analytics.dao.IAnalyticsDAO;
import com.donateknowledge.analytics.dto.Analytics;
import com.donateknowledge.analytics.dto.Keys;
import com.donateknowledge.configurator.ApplicationConfigurator;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Repository
public class AnalyticsDAOImpl implements IAnalyticsDAO {

	private final MongoCollection<Document> collection;

	@Autowired
	public AnalyticsDAOImpl(ApplicationConfigurator appConfig) {
		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_ANALYTICS_KEYWORDS));
	}

	@Override
	public boolean insertSearchKeyWords(Analytics keyWords) throws Exception {
		if (!keyWords.getKeyWords().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Keys itr : keyWords.getKeyWords()) {
				sb.append(javaToJson(itr)).append(",");
			}
			Document update = 
					Document.parse("{\"$pushAll\" : {\"keyWords\" : [" + sb.substring(0, sb.lastIndexOf(",")).toString() + "]}}");
			UpdateOptions updateOptions = new UpdateOptions();
			updateOptions.upsert(true);
			UpdateResult result = collection.updateOne(eq("_id", keyWords.getDate().getTime()), 
					update, updateOptions );
			return result.isModifiedCountAvailable();
		}
		else {
			return false;
		}
	}

	@Override
	public boolean deleteSearchKeyWords() {
		Document query = Document.parse("{\"_id\" : {\"$lte\" : " + getDateNDaysFromToday(MONTH_AGO, false).getTime() + "}}");
		DeleteResult result = collection.deleteMany(query);
		return result.getDeletedCount() >= 1 ? true : false;
	}

	@Override
	public boolean insertUserCounts(Analytics keyWords) throws Exception {
		if (!keyWords.getUser().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append(javaToJson(keyWords.getUser().get(0)));
			Document update = 
					Document.parse("{\"$pushAll\" : {\"user\" : [" + sb.toString() + "]}}");
			UpdateOptions updateOptions = new UpdateOptions();
			updateOptions.upsert(true);
			UpdateResult result = collection.updateOne(eq("_id", keyWords.getDate().getTime()), 
					update, updateOptions );
			return result.isModifiedCountAvailable();
		}
		else {
			return false;
		}
	}
}
