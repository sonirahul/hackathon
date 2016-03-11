package com.donateknowledge.controller;

import static com.donateknowledge.constant.ApplicationConstants.INDEX_PAGE;
import static com.donateknowledge.constant.ApplicationConstants.LOGIN_PAGE;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_COOKIE_DEFAULT;
import static com.donateknowledge.constant.ApplicationConstants.USER;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donateknowledge.dto.user.User;
import com.donateknowledge.service.IDonateKnowledgeService;

@RestController
public class DonateKnowledgeInitialController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DonateKnowledgeInitialController.class);

	@Autowired private IDonateKnowledgeService service;

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView index(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView(INDEX_PAGE);
		
        mv.addObject("myposts", null);
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv.addObject(USER, user);
		}
		return mv;
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView login(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response, final RedirectAttributes redirectAttributes) throws Exception {

		ModelAndView mv = null;
		User user = service.getLoggedInUser(cookieValue, session, response);
		if (user != null) {
			mv = new ModelAndView("redirect:/welcome");
			redirectAttributes.addFlashAttribute("user", user);
		}
		else {
			mv = new ModelAndView(LOGIN_PAGE);
		}
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User validateUser, 
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response, final RedirectAttributes redirectAttributes) throws Exception {

		User user = service.validateUser(validateUser);
		if(user != null) {
			session.setAttribute(USER, user);
			service.updateCookieValue(cookieValue, user.getEmail(), response);
			redirectAttributes.addFlashAttribute("user", user);
			return new ModelAndView("redirect:/welcome");
		}
		else {
			LOGGER.error("Error");
			throw new Exception();
		}

		/*
		 * 	<#if (userNameya)??>${userNameya}<#else>boo</#if>
			<#if (user.email)??>${user.email}<#else>boo</#if>
			<#if (user)??>${user}<#else>boo</#if>
			<#if cars??>
    		<#list cars as car>${car.owner};</#list>
			</#if>
		 */
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(User user, 
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response, final RedirectAttributes redirectAttributes) throws Exception {

		if (service.insertUser(user)) {
			session.setAttribute(USER, user);
			service.updateCookieValue(cookieValue, user.getEmail(), response);
			redirectAttributes.addFlashAttribute("user", user);
			return new ModelAndView("redirect:/welcome");
		}
		else {
			throw new Exception();
		}
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			@ModelAttribute("user") User user, HttpSession session, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView(INDEX_PAGE);

        mv.addObject("myposts", null);
		if (user.getEmail() == null) {
			user = service.getLoggedInUser(cookieValue, session, response);
		}
		if (user != null) {
			mv.addObject(USER, user);
		}
		else {
			mv.addObject(USER, null);
		}
		return mv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(
			@CookieValue(value = SESSION_COOKIE, defaultValue = SESSION_COOKIE_DEFAULT) String cookieValue, 
			HttpSession session, HttpServletResponse response) {
		if (session.getAttribute(USER) != null) {
			session.removeAttribute(USER);
			session.invalidate();
		}
		service.endSession(cookieValue, response);
		/*if (!SESSION_COOKIE_DEFAULT.equals(cookieValue)) {
			Cookie cookie = new Cookie(SESSION_COOKIE, null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}*/
		return new ModelAndView("redirect:/index");
	}
}
