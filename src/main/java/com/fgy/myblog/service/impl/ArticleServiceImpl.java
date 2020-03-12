package com.fgy.myblog.service.impl;

import com.fgy.myblog.bean.ArticleInfo;
import com.fgy.myblog.dao.ArticleInfoMapper;
import com.fgy.myblog.service.ArticleService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
    @Override
    public List<ArticleInfo> selecetAll(ArticleInfo article) {
        return articleInfoMapper.selectAll(article);
    }

    @Override
    public int add(ArticleInfo article) {
        return articleInfoMapper.insert(article);
    }

    @Override
    public int update(ArticleInfo article) {
        return articleInfoMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public ArticleInfo selectById(Integer id) {
        return articleInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {

        return articleInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String doPutFile(MultipartFile file) {
        try {
            //文件服务器的地址
            String path="http://localhost:8200/upload/";
            //文件名称
            String fileName=file.getOriginalFilename();
            //时间格式化
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            String data =sdf.format(new Date());

            //重新生成文件名称
            String url=path+data+fileName;


            Client client=new Client();
            WebResource resource = client.resource(url);


            byte[] fileBytes = file.getBytes();
            resource.put(String.class,fileBytes);

            return url;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<ArticleInfo> getAll() {
        return articleInfoMapper.select();
    }

    @Override
    public int getArtCount() {
        return articleInfoMapper.selectCount();
    }

    @Override
    public List<ArticleInfo> selectArtBycategoryId(Integer categoryId) {
        return articleInfoMapper.selectArtBycategoryId(categoryId);
    }
}
