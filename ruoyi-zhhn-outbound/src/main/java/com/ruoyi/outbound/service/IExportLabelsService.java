package com.ruoyi.outbound.service;

import com.ruoyi.outbound.domain.ExportLabels;
import com.ruoyi.outbound.domain.vo.ExportLabelsVo;
import com.ruoyi.outbound.domain.bo.ExportLabelsBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 机器人标签配置Service接口
 *
 * @author ruoyi
 * @date 2022-09-22
 */
public interface IExportLabelsService {

    /**
     * 查询机器人标签配置
     */
    ExportLabelsVo queryById(Long id);

    /**
     * 查询机器人标签配置列表
     */
    TableDataInfo<ExportLabelsVo> queryPageList(ExportLabelsBo bo, PageQuery pageQuery);

    /**
     * 查询机器人标签配置列表
     */
    List<ExportLabelsVo> queryList(ExportLabelsBo bo);

    /**
     * 新增机器人标签配置
     */
    Boolean insertByBo(ExportLabelsBo bo);

    /**
     * 修改机器人标签配置
     */
    Boolean updateByBo(ExportLabelsBo bo);

    /**
     * 校验并批量删除机器人标签配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
