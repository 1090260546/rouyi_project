package com.ruoyi.outbound.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.outbound.entity.TaskUserTag;
import com.ruoyi.outbound.entity.dto.BatchInfoDto;
import com.ruoyi.outbound.mapper.TaskUserTagMapper;
import com.ruoyi.outbound.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserTagServiceImpl extends ServiceImpl<TaskUserTagMapper,TaskUserTag> implements UserTagService {

    @Autowired
    private TaskUserTagMapper taskUserTagMapper;
    @Override
    public void batchInsertUserTag(List<BatchInfoDto> labels, Long taskId,int isDel) {
        if(CollectionUtils.isEmpty(labels)){
            return;
        }
        for(BatchInfoDto label : labels){
            TaskUserTag taskUserTag = new TaskUserTag();
            taskUserTag.setCreateTime(new Date());
            taskUserTag.setUpdateTime(new Date());
            taskUserTag.setIsDelete(isDel);
            taskUserTag.setUserTag(label.getDesc());
            taskUserTag.setTaskId(taskId);
            taskUserTag.setCategory(label.getCategory());
            taskUserTagMapper.insert(taskUserTag);
        }
    }

    @Override
    public Integer countByTaskId(long taskId) {
        return taskUserTagMapper.countByTaskId(taskId);
    }

}
