package com.luijp.service;

import com.luijp.pojo.Article;
import com.luijp.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
