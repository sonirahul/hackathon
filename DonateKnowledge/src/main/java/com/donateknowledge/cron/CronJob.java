package com.donateknowledge.cron;

import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateTimeToday;
import static com.donateknowledge.utils.DonateKnowledgeUtils.getDateToday;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.donateknowledge.analytics.dao.IAnalyticsDAO;
import com.donateknowledge.analytics.dto.Analytics;

@Service
@EnableScheduling
public class CronJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(CronJob.class);

	@Autowired Analytics keyWords;
	@Autowired IAnalyticsDAO keyDAO;

	@Scheduled(cron="1 0 0 * * ?")
	public void dailyCleanUpTasks()
	{
		LOGGER.debug("Running Daily Cron job : " + getDateTimeToday());
		keyWords.setDate(getDateToday());
		keyDAO.deleteSearchKeyWords();
	}

	@Scheduled(cron="0 * * * * ?")
	public void updateKeywords()
	{
		LOGGER.debug("Running 1 min Cron job : " + getDateTimeToday());
		try {
			if (keyDAO.insertSearchKeyWords(keyWords)) {
				keyWords.getKeyWords().clear();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 */15 * * * ?")
	public void updateUserCounts()
	{
		LOGGER.debug("Running 1 min Cron job : " + getDateTimeToday());
		try {
			if (keyDAO.insertUserCounts(keyWords)) {
				keyWords.getUser().get(0).resetUser();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
