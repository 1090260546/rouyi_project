package com.ruoyi.outbound.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.outbound.domain.bo.TaskInfoBo;
import com.ruoyi.outbound.domain.bo.TaskResultBo;
import com.ruoyi.outbound.entity.TaskResult;

import java.util.List;

public interface TaskResultService extends IService<TaskResult> {


    void batchInsertTaskResult(List<TaskResult> taskResults);


    List<TaskResultBo> countByTaskId(long taskId);
}
