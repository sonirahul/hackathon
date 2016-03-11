package com.donateknowledge.configurator;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionTrackingMode;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;  

public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {  
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
		ctx.register(SpringConfigurator.class);  
		ctx.setServletContext(servletContext); 
		servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
		servletContext.addListener(new SessionListener());
		DispatcherServlet dp = new DispatcherServlet(ctx);
		dp.setThrowExceptionIfNoHandlerFound(true);
		Dynamic dynamic = servletContext.addServlet("dispatcher", dp);  
		dynamic.addMapping("/");  
		dynamic.setLoadOnStartup(1);  
	}  
}