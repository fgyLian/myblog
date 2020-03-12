package com.fgy.myblog.service;

import com.fgy.myblog.bean.UserInfo;

import java.util.List;

/**
 * 用户管理的业务逻辑接口
 * @author Administrator
 *
 */
public interface UserInfoService {


    List<UserInfo> selecetAll(UserInfo user);


    int add(UserInfo user);

    int update(UserInfo user);

    UserInfo selectById(Integer id);

    int deleteById(Integer id);

    UserInfo isLogin(UserInfo user);

    int getUserCount();
}
