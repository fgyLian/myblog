package com.fgy.myblog.service;

import com.fgy.myblog.bean.CategoryInfo;

import java.util.List;

public interface CategoryService {
    int add(CategoryInfo category);
    /**
     * 查询所有栏目信息
     * @return
     */
    List<CategoryInfo> categoryList(CategoryInfo categoryInfo);

    CategoryInfo selectById(Integer id);

    int doUpdate(CategoryInfo category);

    int delete(Integer id);



}
