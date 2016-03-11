package com.donateknowledge.analytics.dao;

import com.donateknowledge.analytics.dto.Analytics;

public interface IAnalyticsDAO {

	boolean insertSearchKeyWords(Analytics keyWords) throws Exception;

	boolean deleteSearchKeyWords();

	boolean insertUserCounts(Analytics keyWords) throws Exception;
}
