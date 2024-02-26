package com.luijp.service;

import com.luijp.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> list();

    void add(Category category);

    Category findById(Integer id);

    void update(Category category);
}
