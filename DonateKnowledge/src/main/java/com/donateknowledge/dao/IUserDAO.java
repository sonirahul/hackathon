package com.donateknowledge.dao;

import java.math.BigInteger;

import com.donateknowledge.dto.user.User;

public interface IUserDAO {

	boolean insertUser(User	user, boolean updateLastLogin) throws Exception;

	User fetchUserByEmail(String email, boolean updateLastLogin) throws Exception;

	User validateUser(User validateUser, boolean updateLastLogin);
	
	User updateUserPoints(String email, BigInteger points) throws Exception;
}
