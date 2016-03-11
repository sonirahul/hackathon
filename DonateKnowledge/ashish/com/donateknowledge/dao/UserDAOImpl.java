package com.donateknowledge.dao;

import com.donateknowledge.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User User) {
        sessionFactory.getCurrentSession().merge(User);
    }

    @Override
    public List<User> listUser() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void removeUser(Integer userNo) {
        User User = (User) sessionFactory.getCurrentSession().load(User.class, userNo);
        if (null != User) {
            sessionFactory.getCurrentSession().delete(User);
        }
    }

    @Override
    public User getUserById(Integer userNo) {
        return (User) sessionFactory.getCurrentSession().get(User.class, userNo);
    }

    @Override
    public User getUserByUserName(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User WHERE userName = :UserName");
        query.setString("UserName", userName);
        return (User) query.uniqueResult();
    }
}
