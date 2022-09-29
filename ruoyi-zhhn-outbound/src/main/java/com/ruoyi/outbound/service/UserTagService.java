package com.ruoyi.outbound.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.outbound.entity.TaskUserTag;
import com.ruoyi.outbound.entity.dto.BatchInfoDto;

import java.util.List;

public interface UserTagService extends IService<TaskUserTag> {


    void batchInsertUserTag(List<BatchInfoDto> labels, Long taskId,int isDel);


    Integer countByTaskId(long taskId);
}
