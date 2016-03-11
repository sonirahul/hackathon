package com.donateknowledge.configurator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.donateknowledge.constant.ApplicationConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Configuration
@PropertySources({
	@PropertySource("classpath:com/donateknowledge/config/config.properties")
})
public class ApplicationConfigurator {

	@Autowired private Environment env;
	@Autowired(required=true) private MongoClient mongoClient;
	private MongoDatabase mongodb;
	
	public MongoDatabase getMongoDB() {
		if (mongodb == null && mongoClient != null) {
			synchronized(this) {
				if (mongodb == null) {
					mongodb = mongoClient.getDatabase(getProperty(ApplicationConstants.MONGODB));
				}
			}

		}
		return mongodb;
	}

	public String getProperty(String property) {
		String prop = env.getProperty(property);
		if (prop != null)
			prop = prop.trim();
		return prop;
	}

}
