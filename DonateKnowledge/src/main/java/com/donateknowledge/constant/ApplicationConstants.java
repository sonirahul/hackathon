package com.donateknowledge.constant;

public class ApplicationConstants {

	public static final String CONFIG_FILE_PATH = "com/donateknowledge/config/config.properties";
	// Configured in config.properties file - start
	// Mongo DB
	public static final String MONGODB = "mongodb.db";
	public static final String MONGODB_URL="mongodb.url";
	public static final String MONGODB_COLLECTION_CELL_PHONES = "mongodb.collection.cellphones";
	public static final String MONGODB_COLLECTION_SESSIONS = "mongodb.collection.sessions";
	public static final String MONGODB_COLLECTION_USERS = "mongodb.collection.users";
	public static final String MONGODB_COLLECTION_POSTS = "mongodb.collection.posts";
	public static final String MONGODB_COLLECTION_IMAGES = "mongodb.collection.images";
	public static final String MONGODB_COLLECTION_ANALYTICS_HITS = "mongodb.collection.analytics.hits";
	public static final String MONGODB_COLLECTION_ANALYTICS_KEYWORDS = "mongodb.collection.analytics.keywords";
	public static final String MONGODB_COLLECTION_ANALYTICS_USERS = "mongodb.collection.analytics.users";
	public static final String MONGODB_COLLECTION_USERS_INDEX = "mongodb.collection.users.index";
	public static final String MONGODB_COLLECTION_CELL_PHONES_INDEX = "mongodb.collection.cellPhones.index";
	public static final String SESSION_MAX_INACTIVE_INTERVAL = "sessionMaxInactiveInterval";
	// Configured in config.properties file - end

    public static final String MONGODB_FIELD_ID = "_id";

	// Cookie
	public static final String SESSION_COOKIE = "www.cheapestgadget.com";
	public static final String SESSION_COOKIE_DEFAULT = "defaultValue";

	// View Location
	public static final String VIEWS_LOC = "views.loc";

	// View Page
	public static final String PRODUCT_PAGE = "product";
	public static final String INDEX_PAGE = "index";
	public static final String LOGIN_PAGE = "login";
	public static final String COMPARE_PAGE = "compare";
	public static final String SEARCH_PAGE = "search";

	// Application Generic
	public static final String EMAIL = "email";
	public static final String USER = "user";
	public static final String PHONE = "phone";
	public static final String PHONE_LIST = "phoneList";
	public static final String COMMA = ",";
	public static final String MD5 = "MD5";
	public static final String UTF_8 = "UTF-8";
	public static final String PASSWORD = "password";
	public static final String DD_MMM_YYYY = "dd-MMM-yyyy";

	public static final int ZERO = 0;
	public static final int TODAY = 0;
	public static final int YESTERDAY = -1;
	public static final int WEEK_AGO = -7;
	public static final int MONTH_AGO = -30;
	
	public static final String TIMEZONE_IST = "IST";

	public static final String DATE_TODAY = "Date Today";
	public static final String REGISTERED_LOGGED_IN = "Registered Logged In";
	public static final String REGISTERED_lOGGED_OFF = "Registered Logged Off";
	public static final String NOT_REGISTERED = "Not Registered";

	public static final String ENCRYPTION_SALT = "encryption.salt";
	
	public static final int COOKIE_MAX_AGE = 31536000;
}
