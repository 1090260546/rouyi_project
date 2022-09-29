package com.ruoyi.outbound.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.outbound.domain.TaskInfo;
import com.ruoyi.outbound.domain.bo.TaskInfoBo;
import com.ruoyi.outbound.domain.vo.TaskInfoVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 任务列Mapper接口
 *
 * @author ruoyi
 * @date 2022-09-01
 */
public interface TaskInfoMapper extends BaseMapperPlus<TaskInfoMapper, TaskInfo, TaskInfoVo> {

    Page<TaskInfoVo> selectTaskListPage(@Param("page") Page<TaskInfoBo> page,@Param("taskInfoBo") TaskInfoBo taskInfoBo);
}
