package com.ruoyi.outbound.service;

import com.ruoyi.outbound.domain.TaskInfo;
import com.ruoyi.outbound.domain.vo.TaskInfoVo;
import com.ruoyi.outbound.domain.bo.TaskInfoBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 任务列Service接口
 *
 * @author ruoyi
 * @date 2022-09-01
 */
public interface ITaskInfoService {

    /**
     * 查询任务列
     */
    TaskInfoVo queryById(Long id);

    /**
     * 查询任务列列表
     */
    TableDataInfo<TaskInfoVo> queryPageList(TaskInfoBo bo, PageQuery pageQuery);

    /**
     * 查询任务列列表
     */
    List<TaskInfoVo> queryList(TaskInfoBo bo);

    /**
     * 新增任务列
     */
    Boolean insertByBo(TaskInfoBo bo);

    /**
     * 修改任务列
     */
    Boolean updateByBo(TaskInfoBo bo);

    /**
     * 校验并批量删除任务列信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
