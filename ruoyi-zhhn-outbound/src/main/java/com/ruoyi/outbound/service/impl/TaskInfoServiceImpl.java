package com.ruoyi.outbound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.outbound.domain.vo.MsgTemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.outbound.domain.bo.TaskInfoBo;
import com.ruoyi.outbound.domain.vo.TaskInfoVo;
import com.ruoyi.outbound.domain.TaskInfo;
import com.ruoyi.outbound.mapper.TaskInfoMapper;
import com.ruoyi.outbound.service.ITaskInfoService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务列Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-01
 */
@RequiredArgsConstructor
@Service
public class TaskInfoServiceImpl implements ITaskInfoService {

    private final TaskInfoMapper baseMapper;

    /**
     * 查询任务列
     */
    @Override
    public TaskInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询任务列列表
     */
    public TableDataInfo<TaskInfoVo> queryPageList2(TaskInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TaskInfo> lqw = buildQueryWrapper(bo);
        Page<TaskInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        List<TaskInfoVo> records = result.getRecords();
        List<SysDictData> dictList = CacheUtils.get("sys_dict","ob_region_info");
        Map<String, String> dictMap = dictList.stream().collect(Collectors.toMap(SysDictData::getDictValue,SysDictData::getDictLabel));
        for(TaskInfoVo taskInfoVo : records){
            if(taskInfoVo.getRegionId() != null){
                taskInfoVo.setRegionName(dictMap.get(taskInfoVo.getRegionId().toString()));
            }
        }
        return TableDataInfo.build(result);
    }
    /**
     * 查询任务列列表
     */
    @Override
    public TableDataInfo<TaskInfoVo> queryPageList(TaskInfoBo bo, PageQuery pageQuery) {
        Page<TaskInfoVo> result = baseMapper.selectTaskListPage(new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize()), bo);
        List<TaskInfoVo> records = result.getRecords();
        List<SysDictData> dictList = CacheUtils.get("sys_dict","ob_region_info");
        Map<String, String> dictMap = dictList.stream().collect(Collectors.toMap(SysDictData::getDictValue,SysDictData::getDictLabel));
        for(TaskInfoVo taskInfoVo : records){
            if(taskInfoVo.getRegionId() != null){
                taskInfoVo.setRegionName(dictMap.get(taskInfoVo.getRegionId().toString()));
            }
        }
        return TableDataInfo.build(result);
    }
    /**
     * 查询任务列列表
     */
    @Override
    public List<TaskInfoVo> queryList(TaskInfoBo bo) {
        LambdaQueryWrapper<TaskInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw,TaskInfoVo.class);
    }

    private LambdaQueryWrapper<TaskInfo> buildQueryWrapper(TaskInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TaskInfo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), TaskInfo::getName, bo.getName());
        lqw.between(params.get("beginTaskDate") != null && params.get("endTaskDate") != null,
            TaskInfo::getTaskDate , DateUtils.parseDate(params.get("beginTaskDate")), DateUtils.parseDate(params.get("endTaskDate")));
        lqw.eq(bo.getRegionId() != null, TaskInfo::getRegionId, bo.getRegionId());
        lqw.eq(bo.getSendMsgStatus() != null, TaskInfo::getSendMsgStatus, bo.getSendMsgStatus());
        return lqw;
    }

    /**
     * 新增任务列
     */
    @Override
    public Boolean insertByBo(TaskInfoBo bo) {
        TaskInfo add = BeanUtil.toBean(bo, TaskInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改任务列
     */
    @Override
    public Boolean updateByBo(TaskInfoBo bo) {
        TaskInfo update = BeanUtil.toBean(bo, TaskInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TaskInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除任务列
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
