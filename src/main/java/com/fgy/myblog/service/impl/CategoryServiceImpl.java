package com.fgy.myblog.service.impl;

import com.fgy.myblog.bean.CategoryInfo;
import com.fgy.myblog.dao.CategoryInfoMapper;
import com.fgy.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

   @CacheEvict(cacheNames = "category",allEntries = true)
    @Override
    public int add(CategoryInfo category) {

        return categoryInfoMapper.insert(category);
    }

    @CacheEvict(cacheNames = "category",allEntries = true)
    @Override
    public int delete(Integer id) {
        return categoryInfoMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "category",key = "#p0")
    @Override
    public List<CategoryInfo> categoryList(CategoryInfo categoryInfo) {
        return categoryInfoMapper.categoryList(categoryInfo);
    }

    @Override
    public CategoryInfo selectById(Integer id) {
        return categoryInfoMapper.selectByPrimaryKey(id);
    }

    @CacheEvict(cacheNames = "category",allEntries = true)
    @Override
    public int doUpdate(CategoryInfo category) {
        return categoryInfoMapper.updateByPrimaryKey(category);
    }
}
