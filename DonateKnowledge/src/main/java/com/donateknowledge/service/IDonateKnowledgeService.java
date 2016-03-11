package com.donateknowledge.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.donateknowledge.dto.product.Product;
import com.donateknowledge.dto.user.User;

public interface IDonateKnowledgeService {

	String generateCookieValue();

	User validateUser(User validateUser);

	Product fetchCellPhoneById(String productName) throws Exception;

	boolean insertUser(User validateUser) throws Exception;

	User findUserByCookie(String sessionIdCookie);

	String getRegistrationStatus(String cookieValue);

	void updateCookieValue(String cookieValue, HttpServletResponse response);

	String createCookie(String cookieValue, HttpServletResponse response);

	String createCookie(HttpServletResponse response);

	void updateCookieValue(String cookieValue, String email, HttpServletResponse response);

	void endSession(String cookieValue, HttpServletResponse response);

	User getLoggedInUser(String cookieValue, HttpSession session,
			HttpServletResponse response) throws Exception;

	User findUserByEmail(String email, boolean updateLastLogin) throws Exception;

	Set<Product> fetchCellPhone(String searchStr, int limit, boolean fetchImage) throws Exception;

	List<Product> fetchCellPhoneByTextIndex(String searchStr, int limit, boolean fetchImage) throws Exception;

	List<Product> fetchCellPhoneByRegex(String searchStr, int limit, boolean fetchImage) throws Exception;
}