package com.fgy.myblog.service.impl;

import com.fgy.myblog.bean.UserInfo;
import com.fgy.myblog.dao.UserInfoMapper;
import com.fgy.myblog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public List<UserInfo> selecetAll(UserInfo user) {

        return userInfoMapper.selectAll(user);
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo isLogin(UserInfo user) {
        return userInfoMapper.isLogin(user);
    }

    @Override
    public int getUserCount() {
        return userInfoMapper.getUserCount();
    }

    @Override
    public int deleteById(Integer id) {
        return userInfoMapper.deleteById(id);
    }

    @Override
    public int update(UserInfo user) {
        return userInfoMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int add(UserInfo user) {
        return userInfoMapper.insertSelective(user);
    }


}
