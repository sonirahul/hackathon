package com.donateknowledge.dao;

import com.donateknowledge.model.Country;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl implements CountryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCountry(Country country) {
        sessionFactory.getCurrentSession().merge(country);
    }

    @Override
    public List<Country> listCountry() {
        return sessionFactory.getCurrentSession().createQuery("from Country").list();
    }

    @Override
    public void removeCountry(Integer countryNo) {
        Country country = (Country) sessionFactory.getCurrentSession().load(Country.class, countryNo);
        if (null != country) {
            sessionFactory.getCurrentSession().delete(country);
        }
    }

    @Override
    public Country getCountryById(Integer countryNo) {
        return (Country) sessionFactory.getCurrentSession().get(Country.class, countryNo);
    }
}
