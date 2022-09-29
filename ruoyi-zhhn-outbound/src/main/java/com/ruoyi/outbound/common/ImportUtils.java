package com.ruoyi.outbound.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.outbound.entity.*;
import com.ruoyi.outbound.entity.dto.BatchDetailInfoDto;
import com.ruoyi.outbound.entity.dto.BatchInfoDto;
import com.ruoyi.outbound.service.RegionInfoService;
import com.ruoyi.outbound.service.UserTagService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class ImportUtils {

    private static Logger logger = LoggerFactory.getLogger(ImportUtils.class);

    @Autowired
    private RegionInfoService regionInfoService;

    @Autowired
    private UserTagService userTagService;


    public SendMessageLog convertTOSendMessageLog(long taskId, Integer userId, String mobile, String content,String result){

        SendMessageLog sendMessageLog = new SendMessageLog();
        sendMessageLog.setCreateTime(new Date());
        sendMessageLog.setIsDelete(0);
        sendMessageLog.setSendMobile(mobile);
        sendMessageLog.setSendTime(new Date());
        sendMessageLog.setSendUserId(userId != null ? Long.valueOf(userId) : 0);
        sendMessageLog.setTaskId(taskId);
        sendMessageLog.setSendContent(content);
        sendMessageLog.setResult(result);
        return sendMessageLog;
    }

    public TaskResult convertTOTaskResult(long taskId, Integer userId, String userTag, BatchDetailInfoDto batchDetailInfoDto){
        TaskResult taskResult = new TaskResult();
        taskResult.setCreateTime(new Date());
        taskResult.setUserTag(userTag);
        taskResult.setTaskId(taskId);
        taskResult.setUserId(userId);
        taskResult.setStatus(batchDetailInfoDto.getStatus());
        taskResult.setBatchDetailId(batchDetailInfoDto.getBatchDetailId());
        taskResult.setContent(batchDetailInfoDto.getContent());
        taskResult.setCallTime(batchDetailInfoDto.getCallTime());
        taskResult.setRecordingUrl(batchDetailInfoDto.getWavUrl());
        taskResult.setResult(batchDetailInfoDto.getReason());
        taskResult.setCallDuration(batchDetailInfoDto.getElapsedTime());
        taskResult.setNumberOfCall(batchDetailInfoDto.getCurrentCallTime());
        BeanUtils.copyProperties(batchDetailInfoDto,taskResult,TaskResult.class);
        return taskResult;
//        taskResultMapper.insert(taskResult);
    }


    public TaskResult convertTOTaskResult(TaskResult taskResult, BatchDetailInfoDto batchDetailInfoDto){
        taskResult.setUpdateTime(new Date());
        taskResult.setUserTag(batchDetailInfoDto.getLabel());
        taskResult.setStatus(batchDetailInfoDto.getStatus());
        taskResult.setBatchDetailId(batchDetailInfoDto.getBatchDetailId());
        taskResult.setCallTime(batchDetailInfoDto.getCallTime());
        taskResult.setRecordingUrl(batchDetailInfoDto.getWavUrl());
        taskResult.setResult(batchDetailInfoDto.getReason());
        taskResult.setCallDuration(batchDetailInfoDto.getElapsedTime());
        taskResult.setNumberOfCall(batchDetailInfoDto.getCurrentCallTime());
        BeanUtils.copyProperties(batchDetailInfoDto,taskResult,TaskResult.class);
        return taskResult;
    }


    // 优化方法3，减少put次数
    public List<String> getDiffrent(List<String> list1, List<String> list2) {
        List<String> diff = new ArrayList<>();
        long start = System.currentTimeMillis();
        Map<String, Integer> map = new HashMap<>(list1.size() + list2.size());
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer count = map.get(string);
            if (count != null) {
                map.put(string, ++count);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        logger.info("方法4 耗时：" + (System.currentTimeMillis() - start) + " 毫秒");
        return diff;

    }

    public Task convertTOTask(Task task, BatchInfoDto batch){
        BeanUtils.copyProperties(batch,task,Task.class);
        task.setIsDelete(0);
        task.setTaskDate(batch.getBeginDate());
        task.setName(batch.getBatchName());
        task.setUpdateTime(new Date());
        task.setCreateTime(new Date());
        task.setStatus(0);
        task.setBatchId(batch.getTaskId());
        List<RegionInfo> regionInfos = regionInfoService.getListByName(batch.getBatchInfoDesc());
        if(!CollectionUtils.isEmpty(regionInfos)){
            task.setRegionId(regionInfos.get(0).getId());
        }
        return task;
    }

    public String getUserTags(long taskId,BatchDetailInfoDto batchDetailInfoDto){
        String label = batchDetailInfoDto.getLabel();
        if(!StringUtils.isEmpty(label)){
            JSONObject jsonObject = JSONObject.parseObject(label);
            StringBuilder tagsStr = new StringBuilder();
            for(String key : jsonObject.keySet()){
               List<TaskUserTag> taskUserTag = userTagService.list(new LambdaQueryWrapper<TaskUserTag>().
                    eq(TaskUserTag::getTaskId,taskId).
                    eq(TaskUserTag::getUserTag,key)
                );
                tagsStr.append(taskUserTag.get(0).getId()).append(",");
            }
            if(!StringUtils.isEmpty(tagsStr.toString())){
                tagsStr = new StringBuilder(tagsStr.substring(0, tagsStr.length() - 1));
            }
            return tagsStr.toString();
        }
        return "";
    }

}
