package com.fgy.myblog.service.impl;

import com.fgy.myblog.bean.MessageInfo;
import com.fgy.myblog.dao.MessageInfoMapper;
import com.fgy.myblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Override
    public int getMessageCount() {
        return messageInfoMapper.selectMessageCount();
    }

    @Override
    public List<MessageInfo> selecetAll(MessageInfo message) {
        return messageInfoMapper.selectAll(message);
    }

    @Override
    public int add(MessageInfo message) {
        return messageInfoMapper.insert(message);
    }

    @Override
    public int update(MessageInfo message) {
        return messageInfoMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public MessageInfo selectById(Integer id) {

        return messageInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {

        return messageInfoMapper.deleteByPrimaryKey(id);
    }
}
