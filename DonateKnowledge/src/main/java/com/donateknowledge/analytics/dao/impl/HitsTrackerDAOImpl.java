package com.donateknowledge.analytics.dao.impl;

import static com.donateknowledge.constant.ApplicationConstants.MONGODB_COLLECTION_ANALYTICS_HITS;
import static com.donateknowledge.utils.CheapestGadgetUtils.getDateNDaysFromToday;
import static com.donateknowledge.utils.CheapestGadgetUtils.getDateToday;
import static com.donateknowledge.utils.CheapestGadgetUtils.javaToJson;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donateknowledge.analytics.dao.IHitsTrackerDAO;
import com.donateknowledge.configurator.ApplicationConfigurator;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

@Repository
public class HitsTrackerDAOImpl implements IHitsTrackerDAO {

	private final MongoCollection<Document> collection;

    @Autowired
    public HitsTrackerDAOImpl(ApplicationConfigurator appConfig) {
        collection = appConfig.getMongoDB().getCollection(appConfig.getProperty(MONGODB_COLLECTION_ANALYTICS_HITS));
    }

    @Override
    public boolean updateHitCount(String productId) throws Exception {
    	Document query = new Document("productId", productId).append("date", getDateToday().getTime());
    	Document update = Document.parse("{\"$inc\" : {hits:1}}");
        UpdateOptions options = new UpdateOptions();
        options.upsert(true);
		UpdateResult result = collection.updateOne(query, update, options);

        return result.isModifiedCountAvailable();
    }

    @Override
    public Integer getProductHitCountInNDays(String productId, Integer nDays) throws Exception {
    	Document subQ = Document.parse("{\"$gte\" :" + javaToJson(getDateNDaysFromToday(nDays, false)) + "}");
    	Document query = new Document("productId", productId).append("date", subQ);
    	List<Document> result = collection.find(query).projection(Document.parse("{_id:0,hits:1}")).into(new ArrayList<Document>());
    	
    	Integer count  = 0;
    	if (result != null) {
    		for (Document itr : result) {
    			count = count + (Integer) itr.get("hits");
    		}
    	}
    	return count;
    }
}
