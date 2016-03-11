package com.donateknowledge.service;

import com.donateknowledge.dao.CategoryDAO;
import com.donateknowledge.model.Book;
import com.donateknowledge.model.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Transactional
    @Override
    public void saveCategory(Category category) {
        categoryDAO.saveCategory(category);
    }

    @Transactional
    @Override
    public List<Category> listCategory() {
        return categoryDAO.listCategory();
    }

    @Transactional
    @Override
    public void removeCategory(Integer categoryNo) {
        categoryDAO.removeCategory(categoryNo);
    }

    @Transactional
    @Override
    public Category getCategoryById(Integer categoryNo) {
        return categoryDAO.getCategoryById(categoryNo);
    }
    
    @Transactional
    @Override
    public List<Book> getAllBooksByCategory(Integer categoryNo) {
        return categoryDAO.getAllBooksByCategory(categoryNo);
    }
}
