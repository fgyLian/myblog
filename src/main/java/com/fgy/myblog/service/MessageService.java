package com.fgy.myblog.service;


import com.fgy.myblog.bean.MessageInfo;

import java.util.List;

public interface MessageService {
    List<MessageInfo> selecetAll(MessageInfo message);


    int add(MessageInfo message);

    int update(MessageInfo message);

    MessageInfo selectById(Integer id);

    int deleteById(Integer id);

    int getMessageCount();
}
