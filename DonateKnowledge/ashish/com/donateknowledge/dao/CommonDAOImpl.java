package com.donateknowledge.dao;

import java.sql.Blob;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAOImpl implements CommonDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Blob getBlob(byte[] is) {
        Blob blob = sessionFactory.getCurrentSession().getLobHelper().createBlob(is);
        return blob;
    }
}
