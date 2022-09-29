package com.ruoyi.outbound.service;

import com.ruoyi.outbound.domain.ObBusiness;
import com.ruoyi.outbound.domain.vo.ObBusinessVo;
import com.ruoyi.outbound.domain.bo.ObBusinessBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.outbound.entity.Item;

import java.util.Collection;
import java.util.List;

/**
 * 机器人信息Service接口
 *
 * @author ruoyi
 * @date 2022-09-21
 */
public interface IObBusinessService {

    /**
     * 查询机器人信息
     */
    ObBusinessVo queryById(Long id);

    /**
     * 查询机器人信息列表
     */
    TableDataInfo<ObBusinessVo> queryPageList(ObBusinessBo bo, PageQuery pageQuery);

    /**
     * 查询机器人信息列表
     */
    List<ObBusinessVo> queryList(ObBusinessBo bo);

    /**
     * 查询机器人标签列表
     */
    List<Item> selectRobotLabel(long bizNo);
}
