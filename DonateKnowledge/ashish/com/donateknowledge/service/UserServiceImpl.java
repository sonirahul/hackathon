package com.donateknowledge.service;

import com.donateknowledge.dao.UserDAO;
import com.donateknowledge.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    
    @Transactional
    @Override
    public void saveUser(User User) {
        userDAO.saveUser(User);
    }

    @Transactional
    @Override
    public List<User> listUser() {
        return userDAO.listUser();
    }

    @Transactional
    @Override
    public void removeUser(Integer userNo) {
        userDAO.removeUser(userNo);
    }

    @Transactional
    @Override
    public User getUserById(Integer userNo) {
        return userDAO.getUserById(userNo);
    }

    @Transactional
    @Override
    public User getUserByUsername(String userName) {
        return userDAO.getUserByUserName(userName);
    }
}
