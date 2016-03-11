package com.donateknowledge.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESencrp {

	private static final String ALGO = "AES";
	
	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());

		Base64 newEncoder = new Base64();
		String encryptedValue = newEncoder.encodeToString(encVal);
		
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);

		Base64 newEncoder = new Base64();
		byte[] decordedValue = newEncoder.decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);

		return decryptedValue;
	}
	private static Key generateKey() throws Exception {
		String keyPharse = "5F77BDE1981C3A99";
		Key key = new SecretKeySpec(keyPharse.getBytes(), ALGO);
		return key;
	}
	
	public static void main(String[] args) throws Exception {

        String password = "mypassword";
        String passwordEnc = AESencrp.encrypt(password);
        String passwordDec = AESencrp.decrypt(passwordEnc);

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
    }

}