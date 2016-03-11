package com.donateknowledge.dao;

import com.donateknowledge.model.PopularSearch;
import java.util.Iterator;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDAOImpl implements SearchDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List searchResults(String searchCriteria) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT BookNo, BookName, Year, Synopsis"
                + " FROM Books, Authors, Publishers "
                + "WHERE Authors.AuthorNo = Books.Author1No "
                + "OR Authors.AuthorNo = Books.Author2No "
                + "OR Authors.AuthorNo = Books.Author3No "
                + "OR Authors.AuthorNo = Books.Author4No "
                + "OR Publishers.PublisherNo = Books.PublisherNo "
                + "AND (BookName LIKE '%"+ searchCriteria + "%' "
                + "OR ISBN LIKE '%"+ searchCriteria + "%' "
                + "OR Edition LIKE '%"+ searchCriteria + "%' "
                + "OR Year LIKE '%"+ searchCriteria + "%' "
                + "OR Synopsis LIKE '%"+ searchCriteria + "%' "
                + "OR AboutAuthors LIKE '%"+ searchCriteria + "%' "
                + "OR TopicsCovered LIKE '%"+ searchCriteria + "%' "
                + "OR ContentsCDROM LIKE '%"+ searchCriteria + "%' "
                + "OR Cost LIKE '%"+ searchCriteria + "%' "
                + "OR FirstName LIKE '%"+ searchCriteria + "%' "
                + "OR LastName LIKE '%"+ searchCriteria + "%' "
                + "OR PublisherName LIKE '%"+ searchCriteria + "%')")
                .list();
    }

    @Override
    public List searchAllResults() {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT BookNo, BookName, Year, Synopsis"
                + " FROM Books, Authors, Publishers "
                + "WHERE Authors.AuthorNo = Books.Author1No "
                + "OR Authors.AuthorNo = Books.Author2No "
                + "OR Authors.AuthorNo = Books.Author3No "
                + "OR Authors.AuthorNo = Books.Author4No "
                + "OR Publishers.PublisherNo = Books.PublisherNo")
                .list();
    }

    @Override
    public List listPopularSearches() {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT Value, COUNT(*) AS Weight FROM PopularSearches GROUP BY Value").list();
    }

    @Override
    public void savePopularSearch(PopularSearch popularSearch) {
        sessionFactory.getCurrentSession().save(popularSearch);
    }

    @Override
    public int getTotalPopularSearches() {
        Object TransactionNo = sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(*) AS Total FROM PopularSearches").addScalar("Total").uniqueResult();
        return Integer.parseInt(TransactionNo.toString());
    }

    @Override
    public void deletePopularSearches() {
        if(getTotalPopularSearches()>600) {
            List<PopularSearch> ps = sessionFactory.getCurrentSession().createQuery("FROM PopularSearch").setMaxResults(10).list();
            for (Iterator i = ps.iterator(); i.hasNext();) {
                Object objPs = i.next();
                sessionFactory.getCurrentSession().delete(objPs);
            }
        }
    }
}
