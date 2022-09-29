package com.ruoyi.outbound.service.impl;

import com.ruoyi.common.config.MessageProperties;
import com.ruoyi.outbound.service.MessageService;
import com.ruoyi.outbound.utils.HttpClient;
import com.ruoyi.outbound.utils.SendMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageProperties messageProperties;

    @Override
    public String sendMessage(String phone, String content,String userId) {
        System.out.println("messageProperties "+messageProperties);
        Map<String, String> paramMap = new HashMap<>();
        long  timeMillis = System.currentTimeMillis();
        paramMap.put("cmd","send");
        paramMap.put("userId",userId);
        paramMap.put("eprId",messageProperties.getEprId());
        paramMap.put("key", SendMessageUtils.getMD5Str(messageProperties.getEprId()+messageProperties.getUserId()+messageProperties.getPwd()+timeMillis));
        paramMap.put("format","1");
        paramMap.put("mobile",phone);
        paramMap.put("content",content);
        paramMap.put("msgId", SendMessageUtils.getMessageId());
        paramMap.put("timestamp",timeMillis+"");
        String result = HttpClient.doGet(messageProperties.getUrl(),paramMap);
        log.info("------sendMessageResult:"+result);
        return result;
    }
}
