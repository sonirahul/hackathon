package com.donateknowledge.configurator;

import static com.donateknowledge.constant.ApplicationConstants.CONFIG_FILE_PATH;
import static com.donateknowledge.constant.ApplicationConstants.SESSION_MAX_INACTIVE_INTERVAL;
import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateTimeToday;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = SessionListener.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);

			prop.load(input);

			event.getSession().setMaxInactiveInterval(Integer.valueOf(prop.getProperty(SESSION_MAX_INACTIVE_INTERVAL)));
			LOGGER.debug("==== Session is created ====" + getDateTimeToday());

		} catch (Exception e) {
			LOGGER.error("Error occurred: " + e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error("Error occurred: " + e);
				}
			}
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LOGGER.debug("==== Session is destroyed ====" + getDateTimeToday());
	}
}