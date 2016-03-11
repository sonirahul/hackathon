package com.donateknowledge.utils;

import static com.donateknowledge.constant.ApplicationConstants.COMMA;
import static com.donateknowledge.constant.ApplicationConstants.DD_MMM_YYYY;
import static com.donateknowledge.constant.ApplicationConstants.MD5;
import static com.donateknowledge.constant.ApplicationConstants.TIMEZONE_IST;
import static com.donateknowledge.constant.ApplicationConstants.TODAY;
import static com.donateknowledge.constant.ApplicationConstants.UTF_8;
import static com.donateknowledge.constant.ApplicationConstants.ZERO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.donateknowledge.dto.product.EnumInterface;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DonateKnowledgeUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(DonateKnowledgeUtils.class);

	private static ObjectMapper mapper = new ObjectMapper();
	private static SimpleDateFormat formatter = new SimpleDateFormat(DD_MMM_YYYY);
	private static final String ALGO = "AES";
	private static Key key;
	private static Cipher c;
	private static Base64 newEncoder;

	static {
		try {
			key = generateKey();
			c = Cipher.getInstance(ALGO);
			newEncoder = new Base64();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> getEnumMappedList(List<String> list, List<EnumInterface> enumValues) {
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<String>() {

				private static final long serialVersionUID = 1L;

				@Override
				public boolean add(String str) {
					for (EnumInterface itr : enumValues) {
						if (itr.toString().equals(str)) {
							return super.add(itr.toString());
						}
					}
					return false;
				}
			};
		}
		return list;
	}

	public static String javaToJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}

	public static String javaToJsonPretty(Object object) throws JsonProcessingException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	}

	public static <T> T jsonToJava(String json, Class<T> valueType) 
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, valueType);
	}

	public static Date stringToDate(String dateStr) throws ParseException {
		return formatter.parse(dateStr);
	}

	public static String dateToString(Date date) {
		return formatter.format(date);
	}

	/**
	 * Returns Date N days from Today, also provides an option for display/hide time field.
	 * @param nDays int
	 * @param displayTimeComponent boolean
	 * @return java.util.Date
	 */
	public static Date getDateNDaysFromToday(int nDays, boolean displayTimeComponent) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(TIMEZONE_IST));
		calendar.add(Calendar.DAY_OF_MONTH, nDays);
		if (!displayTimeComponent) {
			calendar.set(Calendar.HOUR_OF_DAY, ZERO);
			calendar.set(Calendar.MINUTE, ZERO);
			calendar.set(Calendar.SECOND, ZERO);
			calendar.set(Calendar.MILLISECOND, ZERO);
		}
		return calendar.getTime();
	}

	/**
	 * Returns Today's Date without Time field
	 * @return java.util.Date
	 */
	public static Date getDateToday() {
		return getDateNDaysFromToday(TODAY, false);
	}

	/**
	 * Return Today's Date with Time field
	 * @return java.util.Date
	 */
	public static Date getDateTimeToday() {
		return getDateNDaysFromToday(TODAY, true);
	}

	public static List<List<String>> readCSV(String csvFile) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<List<String>> products = new ArrayList<>();
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				products.add(Arrays.asList(line.split(cvsSplitBy)));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return products;
	}

	public static void generateCsvFile(String sFileName, String object)
	{
		try
		{
			FileWriter writer = new FileWriter(sFileName);

			writer.append(object);

			//generate whatever data you want

			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
	}

	public static String generateCustomErrorMessage(String message) {
		String[] tokens = message.split(" ");
		if ("CheapestGadget.users".equals(tokens[5])) {
			if ("_id_".equals(tokens[7])) {
				message = "Email " + tokens[12] + " already registered.";
			}
			else if ("phone.phoneNum_1_phone.countryCode_1".equals(tokens[7])){
				message = "Phone Number \"+" + tokens[14] + "-" + tokens[12].replace(",", "") + "\" already registered.";
			}
		}
		else {

		}
		return message;
	}

	public static BigDecimal stringToBigDecimal(String str) {
		if (StringUtils.isNotEmpty(str)) {
			try {
				return NumberUtils.createBigDecimal(str);
			} catch (NumberFormatException e) {
				LOGGER.debug("errrrorrr : '" + str + "'");
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else {
			return null;
		}
	}

	public static String makePasswordHash(String password, String salt) {
		try {
			String saltedAndHashed = password + COMMA + salt;
			MessageDigest digest = MessageDigest.getInstance(MD5);
			digest.update(saltedAndHashed.getBytes());

			Base64 newEncoder = new Base64();
			byte hashedBytes[] = (new String(digest.digest(), UTF_8)).getBytes();
			return newEncoder.encodeToString(hashedBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 is not available", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
		}
	}

	public static String encrypt(String Data) {
		try {
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(Data.getBytes());
			String encryptedValue = newEncoder.encodeToString(encVal);
			return encryptedValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public static String decrypt(String encryptedData) {

		try {
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = newEncoder.decode(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			String decryptedValue = new String(decValue);
			return decryptedValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	private static Key generateKey() throws Exception {
		String keyPharse = "5F77BDE1981C3A99";
		Key key = new SecretKeySpec(keyPharse.getBytes(), ALGO);
		return key;
	}

	public static String generateRandomSessionId() {

		// get 32 byte random number. that's a lot of bits.
		SecureRandom generator = new SecureRandom();
		byte randomBytes[] = new byte[32];
		generator.nextBytes(randomBytes);

		Base64 encoder = new Base64();
		String sessionID = encoder.encodeToString(randomBytes);

		return sessionID;
	}

	public static void main(String[] args) {
		System.out.println(makePasswordHash("AES Symmetric Encryption Decryption", "5F77BDE1981C3A9964E666DD2AE897CA"));
		System.out.println(makePasswordHash("AES Symmetric Encryption Decryption", "5F77BDE1981C3A9964E666DD2AE897CA"));
		System.out.println(makePasswordHash("AES Symmetric Encryption Decryption", "5F77BDE1981C3A9964E666DD2AE897CA"));
		Calendar.getInstance().getTimeInMillis();
		System.out.println(makePasswordHash(String.valueOf(getDateTimeToday().getTime()), "5F77BDE1981C3A9964E666DD2AE897CA"));

		String test = String.valueOf(getDateTimeToday().getTime());
		String encrypt = encrypt(test);
		String decrypt = decrypt(encrypt);
		System.out.println(test + ", " + encrypt + ", " + decrypt);
	}
}






