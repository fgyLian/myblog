package com.fgy.myblog.service;

import com.fgy.myblog.bean.ArticleInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文章管理的业务逻辑接口
 * @author Administrator
 *
 */
public interface ArticleService {


    List<ArticleInfo> selecetAll(ArticleInfo article);


    int add(ArticleInfo article);

    int update(ArticleInfo article);

    ArticleInfo selectById(Integer id);

    int deleteById(Integer id);

    public String doPutFile(MultipartFile file);

    List<ArticleInfo> getAll();

    List<ArticleInfo> selectArtBycategoryId(Integer categoryId);

    int getArtCount();
}
