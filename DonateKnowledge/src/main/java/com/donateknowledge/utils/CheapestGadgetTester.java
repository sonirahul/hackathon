package com.donateknowledge.utils;

import static com.donateknowledge.utils.CheapestGadgetUtils.getDateNDaysFromToday;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.donateknowledge.analytics.dao.IAnalyticsDAO;
import com.donateknowledge.analytics.dao.IHitsTrackerDAO;
import com.donateknowledge.analytics.dao.impl.AnalyticsDAOImpl;
import com.donateknowledge.analytics.dao.impl.HitsTrackerDAOImpl;
import com.donateknowledge.analytics.dto.Analytics;
import com.donateknowledge.analytics.dto.Keys;
import com.donateknowledge.configurator.SpringConfigurator;
import com.donateknowledge.dao.ICellPhoneDAO;
import com.donateknowledge.dao.IProductImageDAO;
import com.donateknowledge.dao.IUserDAO;
import com.donateknowledge.dao.impl.CellPhoneDAOImpl;
import com.donateknowledge.dao.impl.ProductImageDAOImpl;
import com.donateknowledge.dao.impl.UserDAOImpl;
import com.donateknowledge.dto.product.ProductImage;
import com.donateknowledge.dto.product.phone.Phone;
import com.donateknowledge.dto.user.User;
import com.donateknowledge.dto.user.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;

@SuppressWarnings("unused")
public class CheapestGadgetTester {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheapestGadgetTester.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurator.class);
		//testImagefetch(context);
		//testImageInsert(context);
		testCellPhoneInsert(context);
		//testInsertCellPhone(context);
		//testFetchCellPhoneById(context);
		//testInsertAndFetchUser(context);
		//testUpdateHitCount(context);
		//testAnalytics(context);
		//testCache(context);

	}

	private static void testCellPhoneInsert(ApplicationContext context) throws Exception{

		byte[] b = null;
		try {
			InputStream is = (new URL("http://bitcoinkjhkjhkjhtradefair.com/media/catalog/product/cache/1/thumbnail/600x/17f82f742ffe127f42dca9de82fb58b1/p/i/pic1_1_29.jpg")).openStream();
			b = IOUtils.toByteArray(is);
			is.close();
		} catch (Exception e) {
			LOGGER.error("Error in URL");
			File file = new File("myimage.jpg");
			b = Files.readAllBytes(file.toPath());
		}

		Base64 base = new Base64();
		byte[] encodeBase64 = base.encode(b);
		String base64Encoded = new String(encodeBase64, "UTF-8");
		
		
		
		ICellPhoneDAO cellDao = context.getBean(CellPhoneDAOImpl.class);
		Phone phone  = CheapestGadgetHardCodeHelper.getSampleCellPhone();
		phone.setProductImage(base64Encoded);
		
		String[] manufacturer = "samsung,sony,apple,lg,motorola,htc,lava,micromax,oneplus,oppo,nokia,xolo,carbon".split(",");
		int length = manufacturer.length;
		int good =0 ;
		int bad = 0;
		for (int i = 0 ; i < 200 ; i ++ ) {
			int index = i%length;
			phone.setManufacturer(manufacturer[index]);
			phone.getModels().get(0).getDisplay().setSize(BigDecimal.valueOf(Math.round(ThreadLocalRandom.current().nextDouble(3.0, 5.0 + 1) * 10)/10.0));
			phone.getModels().get(0).getMemory().setRam(BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(1, 4 + 1)));
			phone.setProductName(String.valueOf(i)); 
			if(cellDao.insertCellPhone(phone)) {
				good = good + 1;
			} else {
				bad = bad +1;
			}
			
			if (i%10 == 0) {
				System.out.println("REcords processed: " + i + ", inserted: " + good + ", failed: " + bad);
				good = 0; bad =0;
			}
		}		
	}

	private static void testImageInsert(ApplicationContext context) throws Exception{
		
		byte[] b = null;
		try {
			InputStream is = (new URL("https://www.google.co.in/search?q=iphone+6s+plus&espv=2&biw=1745&bih=861&site=webhp&source=lnms&tbm=isch&sa=X&sqi=2&ved=0ahUKEwiR-t-3jITKAhUGCY4KHb3yCToQ_AUIBigB#tbm=isch&q=iphone+6s+plus+png&imgrc=_W6BtoXkUC6oEM%3A")).openStream();
			b = IOUtils.toByteArray(is);
			is.close();
		} catch (Exception e) {
			File fi = new File("myfile.png");
			b = Files.readAllBytes(fi.toPath());
		}
		
		IProductImageDAO cellDao = context.getBean(ProductImageDAOImpl.class);

		ProductImage productImage = new ProductImage();
		
		String[] manufacturer = "samsung,sony,apple,lg,motorola,htc,lava,micromax,oneplus,oppo,nokia,xolo,carbon".split(",");
		int length = manufacturer.length;
		int good =0 ;
		int bad = 0;
		for (int i = 0 ; i < 100 ; i ++ ) {
			productImage.getOtherImages().clear();
			int index = i%length;
			productImage.setProductId(manufacturer[index] + " " + String.valueOf(i));
			productImage.getOtherImages().add(new String(b));
			productImage.getOtherImages().add(new String(b));
			productImage.getOtherImages().add(new String(b));
			productImage.getOtherImages().add(new String(b));
			if(cellDao.insertProductImage(productImage)) {
				good = good + 1;
			} else {
				bad = bad +1;
			}
			
			if (i%10 == 0) {
				System.out.println("REcords processed: " + i + ", inserted: " + good + ", failed: " + bad);
				good = 0; bad =0;
			}
		}		
	}
	private static void testImagefetch(ApplicationContext context) throws Exception{
		
		IProductImageDAO cellDao = context.getBean(ProductImageDAOImpl.class);
		
		String[] manufacturer = "samsung,sony,apple,lg,motorola,htc,lava,micromax,oneplus,oppo,nokia,xolo,carbon".split(",");
		int length = manufacturer.length;
		int good =0 ;
		int bad = 0;
		for (int i = 0 ; i < 100 ; i ++ ) {
			int index = i%length;
			ProductImage image = cellDao.fetchProductImage(manufacturer[index] + " " + String.valueOf(i));
			System.out.println(image);
		}		
	}

	private static void testCache(ApplicationContext context)
			throws Exception {
		Map<String, Object> cache = (Map<String, Object>) context.getBean("cache");
		for (Entry<String, Object> itr : cache.entrySet()) {
			System.out.println(itr.getKey() + ", " + itr.getValue());
		}
	}

	private static void testAnalytics(ApplicationContext context)
			throws Exception {
		IAnalyticsDAO keyDAO = context.getBean(AnalyticsDAOImpl.class);
		Analytics key = context.getBean(Analytics.class);

		for (int i = 0; i < 40; i ++ ) {
			key.setDate(getDateNDaysFromToday(i * -1 , false));
			key.getKeyWords().add(new Keys("test", i%2 == 0 ? true : false)); 
			key.getKeyWords().add(new Keys("test1", i%2 == 0 ? true : false)); 
			keyDAO.insertSearchKeyWords(key);
			key.getKeyWords().clear();
		}
		keyDAO.deleteSearchKeyWords();
	}

	private static void testUpdateHitCount(ApplicationContext context)
			throws Exception {
		IHitsTrackerDAO hitsDao = context.getBean(HitsTrackerDAOImpl.class);
		hitsDao.updateHitCount("samsung 0");
		System.out.println(hitsDao.getProductHitCountInNDays("samsung 0", -3));
	}

	private static void testInsertAndFetchUser(ApplicationContext context)
			throws Exception {
		IUserDAO userDao = context.getBean(UserDAOImpl.class);
		userDao.insertUser(CheapestGadgetHardCodeHelper.getSampleUser(UserRole.ADMIN), false);
		User user = userDao.fetchUserByEmail("soni_rahul@live.com", true);
		LOGGER.debug(user.getLastLogin().toString());
	}

	private static void testFetchCellPhoneById (ApplicationContext context) throws Exception {
		ICellPhoneDAO cellDao = context.getBean(CellPhoneDAOImpl.class);
		Phone phoneFromDB = cellDao.fetchCellPhoneById("samsung 0");
		System.out.println(phoneFromDB.getModels().get(0).getSimSlotCount());
		System.out.println(CheapestGadgetUtils.javaToJsonPretty(phoneFromDB));
	}

	private static void testInsertCellPhone(ApplicationContext context) throws JsonProcessingException {

		ICellPhoneDAO cellDao = context.getBean(CellPhoneDAOImpl.class);
		Phone phone  = CheapestGadgetHardCodeHelper.getSampleCellPhone();

		String[] manufacturer = "samsung,sony,apple,lg,motorola,htc,lava,micromax,oneplus,oppo,nokia,xolo,carbon".split(",");
		int length = manufacturer.length;
		for (int i = 0 ; i < 10000 ; i ++ ) {
			int index = i%length;
			phone.setManufacturer(manufacturer[index]);
			phone.getModels().get(0).getDisplay().setSize(BigDecimal.valueOf(Math.round(ThreadLocalRandom.current().nextDouble(3.0, 5.0 + 1) * 10)/10.0));
			phone.getModels().get(0).getMemory().setRam(BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(1, 4 + 1)));
			phone.setProductName(String.valueOf(i)); 
			cellDao.insertCellPhone(phone);
		}
	}
}
