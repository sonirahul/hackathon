package com.donateknowledge.dao;

import com.donateknowledge.dto.user.User;

public interface IUserDAO {

	boolean insertUser(User	user, boolean updateLastLogin) throws Exception;

	User fetchUserByEmail(String email, boolean updateLastLogin) throws Exception;

	User validateUser(User validateUser, boolean updateLastLogin);
}
