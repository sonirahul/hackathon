package com.donateknowledge.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.donateknowledge.dto.product.phone.Phone;
import com.donateknowledge.dto.product.phone.PhoneFinder;
import com.donateknowledge.dto.user.User;

public interface ICheapestGadgetService {

	String generateCookieValue();

	User validateUser(User validateUser);

	Phone fetchCellPhoneById(String productName) throws Exception;

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

	Set<Phone> fetchCellPhone(String searchStr, int limit, boolean fetchImage) throws Exception;

	List<Phone> fetchCellPhoneByTextIndex(String searchStr, int limit, boolean fetchImage) throws Exception;

	List<Phone> fetchCellPhoneByRegex(String searchStr, int limit, boolean fetchImage) throws Exception;

	PhoneFinder updateCache() throws Exception;

	List<Document> findByDateDescending();

	Document findByPermalink(String permalink);
}
