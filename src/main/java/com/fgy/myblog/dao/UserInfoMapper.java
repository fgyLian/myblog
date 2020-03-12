package com.fgy.myblog.dao;

import com.fgy.myblog.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户管理的数据访问接口
 * @author Administrator
 * 
 */
@Repository
@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

   /*
   * 查询所有*/
    List<UserInfo> selectAll(UserInfo user);
    /*假删除*/
    int deleteById(Integer id);

    UserInfo isLogin(UserInfo user);

    /**
     * 查询用户总数
     * @return
     */
    int getUserCount();
}