package com.donateknowledge.dao;

public interface ISessionDAO {

	void endSession(String sessionID);

	String findUserEmailBySessionId(String sessionId);

	boolean insertSession(String cookieValue);

	boolean updateSession(String cookieValue, String email);
}
