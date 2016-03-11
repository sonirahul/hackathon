package com.donateknowledge.service.impl;

import static com.donateknowledge.constant.ApplicationConstants.COOKIE_MAX_AGE;
import static com.donateknowledge.constant.ApplicationConstants.NOT_REGISTERED;
import static com.donateknowledge.constant.ApplicationConstants.REGISTERED_LOGGED_IN;
import static com.donateknowledge.constant.ApplicationConstants.REGISTERED_lOGGED_OFF;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE_DEFAULT;
import static com.donateknowledge.constant.ApplicationConstants.USER;
import static com.donateknowledge.utils.CheapestGadgetUtils.decrypt;
import static com.donateknowledge.utils.CheapestGadgetUtils.encrypt;
import static com.donateknowledge.utils.CheapestGadgetUtils.generateRandomSessionId;
import static com.donateknowledge.utils.CheapestGadgetUtils.getDateTimeToday;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donateknowledge.dao.IBlogPostDAO;
import com.donateknowledge.dao.ICellPhoneDAO;
import com.donateknowledge.dao.ISessionDAO;
import com.donateknowledge.dao.IUserDAO;
import com.donateknowledge.dto.compare.impl.CompareProductByPopularity;
import com.donateknowledge.dto.product.phone.Phone;
import com.donateknowledge.dto.product.phone.PhoneFinder;
import com.donateknowledge.dto.user.User;
import com.donateknowledge.service.ICheapestGadgetService;

@Service
public class CheapestGadgetServiceImpl implements ICheapestGadgetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheapestGadgetServiceImpl.class);

	@Autowired private ICellPhoneDAO cellPhoneDao;
	@Autowired private ISessionDAO sessionDao;
	@Autowired private IUserDAO userDao;
	@Autowired private IBlogPostDAO postDao;

	@Resource(name = "cache") private Map<String, Object> cache;

	@Override
	public User getLoggedInUser(String cookieValue, HttpSession session, 
			HttpServletResponse response) throws Exception {

		User user = null;

		// New User
		if (SESSION_COOKIE_DEFAULT.equals(cookieValue)) {
			createCookie(response);
		}
		// Existing User
		else {
			String decryptedCookieCreationTime = decrypt(cookieValue.split(",")[1]);
			BigInteger cookieCreationTime = new BigInteger(decryptedCookieCreationTime == null ? "0" : decryptedCookieCreationTime);
			BigInteger sessionCreateTime = new BigInteger(String.valueOf(session.getCreationTime()));

			if (sessionCreateTime.compareTo(cookieCreationTime) != -1) {
				LOGGER.debug("Cookie update required");
				updateCookieValue(cookieValue, response);
			}

			String registrationStatus = getRegistrationStatus(cookieValue);
			if (REGISTERED_LOGGED_IN.equals(registrationStatus)) {
				LOGGER.debug("Available user in cookie is logged in");
				if (session.getAttribute(USER) != null) {
					user = (User)session.getAttribute(USER);
				}
				else {
					LOGGER.debug("Available user in cookie is registered, setting in session");
					user = findUserByCookie(cookieValue);
					if (user != null) {
						session.setAttribute(USER, user);
					}
				}
			}
			else if (REGISTERED_lOGGED_OFF.equals(registrationStatus)){
				LOGGER.debug("Available user in cookie is logged off");
			}
			else if (NOT_REGISTERED.equals(registrationStatus)) {
				LOGGER.debug("Available user in cookie is not registered");
			}
		}
		return user;
	}

	@Override
	public String createCookie(HttpServletResponse response) {
		return createCookie(SESSION_COOKIE_DEFAULT, response);
	}

	@Override
	public String createCookie(String cookieValue, HttpServletResponse response) {
		boolean insertRequired = false;
		if (SESSION_COOKIE_DEFAULT.equals(cookieValue)) {
			cookieValue = generateCookieValue();
			insertRequired = true;
		}

		Cookie cookie = new Cookie(SESSION_COOKIE, cookieValue); //bake cookie
		cookie.setMaxAge(COOKIE_MAX_AGE); //set expire time to 1 year
		response.addCookie(cookie); //put cookie in response

		if (insertRequired) {
			sessionDao.insertSession(cookieValue.split(",")[2]);
		}
		return cookieValue;
	}

	@Override
	public String generateCookieValue() {

		StringBuilder sb = new StringBuilder();

		sb.append(cache.get(NOT_REGISTERED).toString()).append(",");
		sb.append(encrypt(String.valueOf(getDateTimeToday().getTime()))).append(",");
		sb.append(generateRandomSessionId());
		System.out.println("cooki created at :" + getDateTimeToday().getTime());
		return sb.toString();
	}

	@Override
	public void updateCookieValue(String cookieValue, HttpServletResponse response) {
		updateCookieValue(cookieValue, null, response);	
	}

	@Override
	public void updateCookieValue(String cookieValue, String email, HttpServletResponse response) {

		if (!SESSION_COOKIE_DEFAULT.equals(cookieValue)) {
			String[] cookieTokens = cookieValue.split(",");

			if (cookieTokens.length == 3) {

				StringBuilder sb = new StringBuilder();
				if (email != null) {
					LOGGER.debug("updating cookie with email");
					sessionDao.updateSession(cookieTokens[2], email);
					sb.append(cache.get(REGISTERED_LOGGED_IN).toString()).append(",");
				}
				else {
					sb.append(cookieTokens[0]).append(",");
				}
				sb.append(encrypt(String.valueOf(getDateTimeToday().getTime()))).append(",");
				sb.append(cookieTokens[2]);
				createCookie(sb.toString(), response);
			}
		}
	}

	@Override
	public String getRegistrationStatus(String cookieValue) {
		if (!SESSION_COOKIE_DEFAULT.equals(cookieValue)) {
			cookieValue = cookieValue.substring(0, cookieValue.indexOf(","));
			return (String) cache.get(cookieValue);
		}
		return null;
	}

	@Override
	public User validateUser(User validateUser) {
		return userDao.validateUser(validateUser, true);
	}

	@Override
	public Phone fetchCellPhoneById(String productName) throws Exception {
		return cellPhoneDao.fetchCellPhoneById(productName);
	}

	@Override
	public boolean insertUser(User validateUser) throws Exception {
		return userDao.insertUser(validateUser, true);
	}

	@Override
	public void endSession(String cookieValue, HttpServletResponse response) {
		if (!SESSION_COOKIE_DEFAULT.equals(cookieValue)) {
			String[] cookieToken = cookieValue.split(",");

			StringBuilder sb = new StringBuilder();

			sb.append(cache.get(REGISTERED_lOGGED_OFF).toString()).append(",");
			sb.append(cookieToken[1]).append(",");
			sb.append(cookieToken[2]).append(",");
			createCookie(sb.toString(), response);
		}
	}

	@Override
	public User findUserByCookie(String cookieValue) {
		if (!SESSION_COOKIE_DEFAULT.equals(cookieValue)) {
			try {
				cookieValue = cookieValue.split(",")[2];
				String email = sessionDao.findUserEmailBySessionId(cookieValue);
				if (StringUtils.isNotEmpty(email)) {
					return findUserByEmail(email, true);
				} 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error("Error occurred : " + e);
			}
		}
		return null;
	}

	@Override
	public User findUserByEmail(String email, boolean updateLastLogin) throws Exception {
		return userDao.fetchUserByEmail(email, updateLastLogin);
	}

	@Override
	public List<Phone> fetchCellPhoneByTextIndex(String searchStr, int limit, boolean fetchImage) throws Exception {
		return cellPhoneDao.fetchCellPhoneByTextIndex(searchStr, 0, limit, fetchImage, null);
	}

	@Override
	public List<Phone> fetchCellPhoneByRegex(String searchStr, int limit, boolean fetchImage) throws Exception {
		String[] searchTokens = searchStr.split(" ");

		if (searchTokens.length == 1) {
			return cellPhoneDao.fetchCellPhoneByRegex(searchTokens, 0, limit, fetchImage, null);
		}
		else {
			String[] searchArr = new String[1];
			searchArr[0] = searchStr.replaceAll(" ", "[\\\\\\\\s]");
			Set<Phone> phoneSet = new LinkedHashSet<>(cellPhoneDao.fetchCellPhoneByRegex(searchArr, 0, limit, fetchImage, null));
			if (phoneSet.size() < limit) {
				phoneSet.addAll(cellPhoneDao.fetchCellPhoneByRegex(searchTokens, 0, limit - phoneSet.size(), fetchImage, new CompareProductByPopularity()));
			}

			List<Phone> phoneList = new ArrayList<Phone>(phoneSet);
			return phoneList;
		}

	}

	@Override
	public Set<Phone> fetchCellPhone(String searchStr, int limit, boolean fetchImage) throws Exception {
		Set<Phone> phoneSet = new LinkedHashSet<>();
		phoneSet.addAll(fetchCellPhoneByTextIndex(searchStr.toLowerCase(), limit, fetchImage));

		if (phoneSet.size() < limit) {
			phoneSet.addAll(fetchCellPhoneByRegex(searchStr.toLowerCase(), limit - phoneSet.size(), fetchImage));
			if (phoneSet.size() > limit) {
				throw new Exception(searchStr);
			}
		}
		return phoneSet;
	}

	@Override
	public PhoneFinder updateCache() throws Exception {
		return cellPhoneDao.updateCache();
	}

	@Override
	public List<Document> findByDateDescending() {
		return postDao.findByDateDescending(10);
	}
	
	@Override
	public Document findByPermalink(String permalink) {
		return postDao.findByPermalink(permalink);
	}

}