package com.donateknowledge.controller;

import static com.donateknowledge.constant.ApplicationConstants.PHONE;
import static com.donateknowledge.constant.ApplicationConstants.PHONE_LIST;
import static com.donateknowledge.constant.ApplicationConstants.PRODUCT_PAGE;
import static com.donateknowledge.constant.ApplicationConstants.SEARCH_PAGE;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE_DEFAULT;
import static com.donateknowledge.constant.ApplicationConstants.USER;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.donateknowledge.dto.product.Product;
import com.donateknowledge.dto.product.book.Book;
import com.donateknowledge.dto.user.User;
import com.donateknowledge.service.IDonateKnowledgeService;

@RestController
public class DonateKnowledgeAjaxController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DonateKnowledgeAjaxController.class);

	@Autowired private IDonateKnowledgeService service;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(String query, 
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) throws Exception {

		return new ModelAndView("redirect:/product/"+query);
	}

	@RequestMapping(value = "/fetchUserByEmail", method = RequestMethod.POST)
	public Document fetchUserByEmail(@RequestBody String email) throws Exception {

		User user = service.findUserByEmail(email.replaceAll("\"", ""), false);
		if (user != null) {
			return new Document("userExists", true);
		}
		return new Document("userExists", false);
	}

	@RequestMapping(value = "/validatePasscode", method = RequestMethod.POST)
	public Document validatePasscode(@RequestBody String reqData) throws Exception {
		
		String[] tokens = reqData.replaceAll("\"", "").split(",");
		String isbn = tokens[0];
		String passcode = tokens[1];
		
		Book book = (Book) service.fetchBookById(isbn);
		if (book.getSecretCode().equalsIgnoreCase(passcode)) { 
			return new Document("passCodeValidated", true);
		}
		return new Document("passCodeValidated", false);
	}
	
	@RequestMapping(value = "/addeditproduct", method = RequestMethod.POST) 
	public Product addEditProduct(@RequestBody String productName) throws Exception {
		
		productName = productName.replaceAll("\"", "").replaceFirst("_", " ");
		
		Product phone = service.fetchBookById(productName);
		
		return phone;
	}

	@RequestMapping(value = "/product/{productName}", method = RequestMethod.GET) 
	public ModelAndView getProduct(@PathVariable String productName, 
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) throws Exception {
		ModelAndView mv = null; 

		productName = productName.replaceFirst("_", " ");

		Product phone = service.fetchBookById(productName);
		if (phone != null) {
			mv = new ModelAndView(PRODUCT_PAGE);
			mv.addObject(PHONE, phone);
		}
		else {
			mv = new ModelAndView(SEARCH_PAGE);
			Set<Product> phoneList = service.fetchProduct(productName, 20, true);
			mv.addObject(PHONE_LIST, phoneList);
		}

		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		return mv;
	}

	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody
	Set<Tag> getTags(@RequestParam String tagName) throws Exception {
		Set<Tag> result = new LinkedHashSet<Tag>();

		int limit = 10; 
		Set<Product> phoneSet = service.fetchProduct(tagName, limit, false);
		int i = 0;
		for (Product itr : phoneSet) {
			result.add(new Tag(i++, itr.getProductId()));
		}
		return result;
	}

	@RequestMapping(value = {"/phoneFinder"}, method = RequestMethod.GET)
	public ModelAndView phoneFinder(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("finder");
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		mv.addObject("phoneFinder", null);
		return mv;
	}
}
class Tag {

	public int id;
	public String tagName;

	//getter and setter methods

	public Tag(int id, String tagName) {
		this.id = id;
		this.tagName = tagName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}