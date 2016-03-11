package com.donateknowledge.service;

import com.donateknowledge.dao.CountryDAO;
import com.donateknowledge.model.Country;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDAO countryDAO;
    
    @Transactional
    @Override
    public void saveCountry(Country country) {
        countryDAO.saveCountry(country);
    }

    @Transactional
    @Override
    public List<Country> listCountry() {
        return countryDAO.listCountry();
    }

    @Transactional
    @Override
    public void removeCountry(Integer countryNo) {
        countryDAO.removeCountry(countryNo);
    }

    @Transactional
    @Override
    public Country getCountryById(Integer countryNo) {
        return countryDAO.getCountryById(countryNo);
    }
}
