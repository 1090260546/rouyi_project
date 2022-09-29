package com.ruoyi.outbound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.outbound.domain.bo.ExportLabelsBo;
import com.ruoyi.outbound.domain.vo.ExportLabelsVo;
import com.ruoyi.outbound.domain.ExportLabels;
import com.ruoyi.outbound.mapper.ExportLabelsMapper;
import com.ruoyi.outbound.service.IExportLabelsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 机器人标签配置Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-22
 */
@RequiredArgsConstructor
@Service
public class ExportLabelsServiceImpl implements IExportLabelsService {

    private final ExportLabelsMapper baseMapper;

    /**
     * 查询机器人标签配置
     */
    @Override
    public ExportLabelsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询机器人标签配置列表
     */
    @Override
    public TableDataInfo<ExportLabelsVo> queryPageList(ExportLabelsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ExportLabels> lqw = buildQueryWrapper(bo);
        Page<ExportLabelsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询机器人标签配置列表
     */
    @Override
    public List<ExportLabelsVo> queryList(ExportLabelsBo bo) {
        LambdaQueryWrapper<ExportLabels> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ExportLabels> buildQueryWrapper(ExportLabelsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ExportLabels> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBizName()), ExportLabels::getBizName, bo.getBizName());
        lqw.eq(bo.getBizNo() != null, ExportLabels::getBizNo, bo.getBizNo());
        lqw.eq(StringUtils.isNotBlank(bo.getObLabels()), ExportLabels::getObLabels, bo.getObLabels());
        lqw.eq(StringUtils.isNotBlank(bo.getObStatus()), ExportLabels::getObStatus, bo.getObStatus());
        lqw.like(StringUtils.isNotBlank(bo.getSheetName()), ExportLabels::getSheetName, bo.getSheetName());
        lqw.eq(bo.getIsDelete() != null, ExportLabels::getIsDelete, bo.getIsDelete());
        return lqw;
    }

    /**
     * 新增机器人标签配置
     */
    @Override
    public Boolean insertByBo(ExportLabelsBo bo) {
        ExportLabels add = BeanUtil.toBean(bo, ExportLabels.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改机器人标签配置
     */
    @Override
    public Boolean updateByBo(ExportLabelsBo bo) {
        ExportLabels update = BeanUtil.toBean(bo, ExportLabels.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ExportLabels entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除机器人标签配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
