package com.fgy.myblog.dao;


import com.fgy.myblog.bean.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);

    List<ArticleInfo> selectAll(ArticleInfo article);

    List<ArticleInfo> select();

    List<ArticleInfo> selectArtBycategoryId(Integer categoryId);


    int selectCount();
}