package com.donateknowledge.service;

import com.donateknowledge.model.User;
import java.util.List;

public interface UserService {
    public void saveUser(User User);
    public List<User> listUser();
    public void removeUser(Integer userNo);
    public User getUserById(Integer userNo);
    public User getUserByUsername(String userName);
}
