package com.donateknowledge.dao;

import com.donateknowledge.model.Book;
import com.donateknowledge.model.Category;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCategory(Category category) {
        sessionFactory.getCurrentSession().merge(category);
    }

    @Override
    public List<Category> listCategory() {
        return sessionFactory.getCurrentSession().createQuery("from Category ORDER BY category").list();
    }

    @Override
    public void removeCategory(Integer categoryNo) {
        Category category = (Category) sessionFactory.getCurrentSession().load(Category.class, categoryNo);
        if (null != category) {
            sessionFactory.getCurrentSession().delete(category);
        }
    }

    @Override
    public Category getCategoryById(Integer categoryNo) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, categoryNo);
    }

    @Override
    public List<Book> getAllBooksByCategory(Integer categoryNo) {
        return sessionFactory.getCurrentSession().createQuery("FROM Book WHERE categoryNo = " + categoryNo).list();
    }
}
