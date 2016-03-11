package com.donateknowledge.configurator;

import static com.donateknowledge.constant.ApplicationConstants.DATE_TODAY;
import static com.donateknowledge.constant.ApplicationConstants.ENCRYPTION_SALT;
import static com.donateknowledge.constant.ApplicationConstants.MONGODB_URL;
import static com.donateknowledge.constant.ApplicationConstants.NOT_REGISTERED;
import static com.donateknowledge.constant.ApplicationConstants.REGISTERED_LOGGED_IN;
import static com.donateknowledge.constant.ApplicationConstants.REGISTERED_lOGGED_OFF;
import static com.donateknowledge.constant.ApplicationConstants.VIEWS_LOC;
import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateToday;
import static com.donateknowledge.utils.DonateKnowledgeUtils.makePasswordHash;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.donateknowledge.analytics.dto.Analytics;
import com.donateknowledge.analytics.dto.User;
import com.donateknowledge.utils.DonateKnowledgeUtils;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration 
@ComponentScan("com.donateknowledge") 
@EnableWebMvc
@EnableAspectJAutoProxy
public class SpringConfigurator extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public FreeMarkerViewResolver viewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setCache(false);
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".html");
		viewResolver.setRequestContextAttribute("rc");
		return viewResolver;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig(ApplicationConfigurator appConfig) {
		FreeMarkerConfigurer freemarkerConfig = new FreeMarkerConfigurer();
		freemarkerConfig.setTemplateLoaderPath(appConfig.getProperty(VIEWS_LOC));
		return freemarkerConfig;
	}

	@Bean
	public MongoClient mongoClient(ApplicationConfigurator appConfig) {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(appConfig.getProperty(MONGODB_URL)));
		return mongoClient;
	}

	@Bean
	public Analytics keyWords() {
		Analytics keyWords = new Analytics();
		keyWords.setDate(getDateToday());
		keyWords.getUser().add(new User(DonateKnowledgeUtils.getDateTimeToday()));
		return keyWords;
	}

	@Bean
	public Map<String, Object> cache(ApplicationConfigurator appConfig) {
		Map<String, Object> cache = new HashMap<String, Object>();
		cache.put(makePasswordHash(REGISTERED_LOGGED_IN, appConfig.getProperty(ENCRYPTION_SALT)), REGISTERED_LOGGED_IN);
		cache.put(makePasswordHash(REGISTERED_lOGGED_OFF, appConfig.getProperty(ENCRYPTION_SALT)), REGISTERED_lOGGED_OFF);
		cache.put(makePasswordHash(NOT_REGISTERED, appConfig.getProperty(ENCRYPTION_SALT)), NOT_REGISTERED);
		cache.put(makePasswordHash(String.valueOf(getDateToday().getTime()), appConfig.getProperty(ENCRYPTION_SALT)), DATE_TODAY);
		cache.put(REGISTERED_LOGGED_IN, makePasswordHash(REGISTERED_LOGGED_IN, appConfig.getProperty(ENCRYPTION_SALT)));
		cache.put(REGISTERED_lOGGED_OFF, makePasswordHash(REGISTERED_lOGGED_OFF, appConfig.getProperty(ENCRYPTION_SALT)));
		cache.put(NOT_REGISTERED, makePasswordHash(NOT_REGISTERED, appConfig.getProperty(ENCRYPTION_SALT)));
		cache.put(DATE_TODAY, makePasswordHash(String.valueOf(getDateToday().getTime()), appConfig.getProperty(ENCRYPTION_SALT)));
		return cache;
	}
}