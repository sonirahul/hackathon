package com.donateknowledge.utils;

import java.math.BigInteger;

import com.donateknowledge.dto.user.User;
import com.donateknowledge.dto.user.UserPhone;
import com.donateknowledge.dto.user.UserRole;

public class DonateKnowledgeHardCodeHelper {

	//private static final Logger LOGGER = LoggerFactory.getLogger(CheapestGadgetHardCodeHelper.class);

	public static User getSampleUser(UserRole userRole) {
		User user = new User();
		user.setEmail("soni_rahul@live.com");
		UserPhone phone = new UserPhone();
		phone.setPhoneNum(new BigInteger("8696467002"));
		user.getPhone().add(phone);
		user.setFullName("Rahul Soni");
		user.setPassword("password");
		user.getOldPasswords().add("1");
		user.getOldPasswords().add("2");
		user.getOldPasswords().add("3");
		user.getOldPasswords().add("4");
		user.getOldPasswords().add("5");
		user.getOldPasswords().add("6");
		if (userRole == UserRole.ADMIN) {
			user.getUserRole().add(UserRole.ADMIN.toString());
			user.getUserRole().add(UserRole.DBA.toString());
		}
		else if (userRole == UserRole.DBA) {
			user.getUserRole().add(UserRole.DBA.toString());
		}

		return user;

	}
}
