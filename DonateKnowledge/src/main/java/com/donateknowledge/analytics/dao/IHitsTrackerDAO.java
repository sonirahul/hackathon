package com.donateknowledge.analytics.dao;

public interface IHitsTrackerDAO {

	boolean updateHitCount(String productId) throws Exception;

	Integer getProductHitCountInNDays(String productId, Integer nDays) throws Exception;
}
