package com.ruoyi.outbound.service;

public interface MessageService {

    String sendMessage(String phone,String content,String userId);
}
