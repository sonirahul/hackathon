package com.donateknowledge.analytics.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.*;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.analytics.dao.IUserAnalytics;
import com.donateknowledge.configurator.ApplicationConfigurator;
import com.mongodb.client.MongoCollection;

@Repository
public class UserAnalyticsDAOImpl implements IUserAnalytics {

	private final MongoCollection<Document> collection;

	@Autowired
	public UserAnalyticsDAOImpl(ApplicationConfigurator appConfig) {
		collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_ANALYTICS_USERS));
	}

	
}
