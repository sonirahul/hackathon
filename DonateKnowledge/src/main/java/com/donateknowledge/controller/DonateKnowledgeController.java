package com.donateknowledge.controller;

import static com.donateknowledge.constant.ApplicationConstants.COMPARE_PAGE;
import static com.donateknowledge.constant.ApplicationConstants.LOGIN_PAGE;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE_DEFAULT;
import static com.donateknowledge.constant.ApplicationConstants.USER;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.donateknowledge.dto.product.ProductCategory;
import com.donateknowledge.dto.product.book.Book;
import com.donateknowledge.dto.user.User;
import com.donateknowledge.service.IDonateKnowledgeService;

@RestController
public class DonateKnowledgeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DonateKnowledgeController.class);

	@Autowired private IDonateKnowledgeService service;

	@RequestMapping(value = {"/newpost"}, method = RequestMethod.GET)
	public ModelAndView getNewPost(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) throws Exception {

		User user = service.getLoggedInUser(cookieValue, session, response);
		ModelAndView mv = null;
		if (user != null) {
			mv = new ModelAndView("newPost");
			mv.addObject(USER, user);
		}
		else {
			mv = new ModelAndView(LOGIN_PAGE);
		}

		return mv;
	}

	@RequestMapping(value = {"/newpost"}, method = RequestMethod.POST)
	public void submitNewPost(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {

		String title = StringEscapeUtils.escapeHtml4(request.getParameter("subject"));
		String post = StringEscapeUtils.escapeHtml4(request.getParameter("body"));
		String tags = StringEscapeUtils.escapeHtml4(request.getParameter("tags"));

		User user = service.getLoggedInUser(cookieValue, session, response);

		/*ModelAndView mv = null;

        if (user == null) {
        	mv = new ModelAndView(LOGIN_PAGE);   // only logged in users can post to blog
        }
        else if (title.equals("") || post.equals("")) {
            // redisplay page with errors
            HashMap<String, String> root = new HashMap<String, String>();
            root.put("errors", "post must contain a title and blog entry.");
            root.put("subject", title);
            root.put("username", username);
            root.put("tags", tags);
            root.put("body", post);
            template.process(root, writer);
        }
        else {
            // extract tags
            ArrayList<String> tagsArray = extractTags(tags);

            // substitute some <p> for the paragraph breaks
            post = post.replaceAll("\\r?\\n", "<p>");

            String permalink = blogPostDAO.addPost(title, post, tagsArray, username);

            // now redirect to the blog permalink
            response.redirect("/post/" + permalink);
        }*/
	}

	@RequestMapping(value = {"/compare"}, method = RequestMethod.GET)
	public ModelAndView compare(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView(COMPARE_PAGE);
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		return mv;
	}

	@RequestMapping(value = {"/uploader"}, method = RequestMethod.GET)
	public ModelAndView getUploader(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("uploader");
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		mv.addObject("phoneFinder", null);
		return mv;
	}

	@RequestMapping(value = {"/uploader"}, method = RequestMethod.POST)
	public ModelAndView submitUploader(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {


		String bookTitle = StringEscapeUtils.escapeHtml4(request.getParameter("bookTitle"));
		String authorName = StringEscapeUtils.escapeHtml4(request.getParameter("authorName"));
		String publisherName = StringEscapeUtils.escapeHtml4(request.getParameter("publisherName"));
		String isbn = StringEscapeUtils.escapeHtml4(request.getParameter("isbn"));
		String year = StringEscapeUtils.escapeHtml4(request.getParameter("year"));
		String edition = StringEscapeUtils.escapeHtml4(request.getParameter("edition"));
		String mrpPrice = StringEscapeUtils.escapeHtml4(request.getParameter("mrpPrice"));
		String piecesInStock = StringEscapeUtils.escapeHtml4(request.getParameter("piecesInStock"));

		System.out.println("testing productName:" + bookTitle + ", manufacturer:" + bookTitle + ", modelName:" + bookTitle);

		Book book = new Book();
		book.setAuthorName(authorName);
		book.setBookTitle(bookTitle);
		book.setEdition(edition);
		book.setGenre("");

		ModelAndView mv = new ModelAndView("donateSuccess");
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		book.setInsertedBy(user.getEmail());
		book.setInsertedDate(new Date());
		book.setIsbn(isbn);
		if (mrpPrice != null && "".equals(mrpPrice)) {
			book.setMrpPrice(new BigDecimal(mrpPrice));
		}
		book.setPiecesInStock(new BigInteger(piecesInStock));
		book.setProductCategory(ProductCategory.BOOK.getValue());
		book.setPublisherName(publisherName);
		book.setYear(Integer.valueOf(year));
		
		service.insertBook(book);
		mv.addObject("phoneFinder", null);
		return mv;
	}

	@RequestMapping(value = {"/donate"}, method = RequestMethod.GET)
	public ModelAndView donate(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("donate");
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		mv.addObject("phoneFinder", null);
		return mv;
	}


	@RequestMapping(value = {"/receive"}, method = RequestMethod.GET)
	public ModelAndView receive(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {




		ModelAndView mv = new ModelAndView("receive");
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		mv.addObject("phoneFinder", null);
		return mv;
	}
}
