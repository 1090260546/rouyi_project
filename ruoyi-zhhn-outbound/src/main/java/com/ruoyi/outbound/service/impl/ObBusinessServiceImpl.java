package com.ruoyi.outbound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.outbound.domain.vo.TaskInfoVo;
import com.ruoyi.outbound.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.outbound.domain.bo.ObBusinessBo;
import com.ruoyi.outbound.domain.vo.ObBusinessVo;
import com.ruoyi.outbound.domain.ObBusiness;
import com.ruoyi.outbound.mapper.ObBusinessMapper;
import com.ruoyi.outbound.service.IObBusinessService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 机器人信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@RequiredArgsConstructor
@Service
public class ObBusinessServiceImpl implements IObBusinessService {

    private final ObBusinessMapper baseMapper;

    /**
     * 查询机器人信息
     */
    @Override
    public ObBusinessVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询机器人信息列表
     */
    @Override
    public TableDataInfo<ObBusinessVo> queryPageList(ObBusinessBo bo, PageQuery pageQuery) {
        //LambdaQueryWrapper<ObBusiness> lqw = buildQueryWrapper(bo);
        //Page<ObBusinessVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        Page<ObBusinessVo> result = baseMapper.selectRobotListPage(new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize()), bo);
        return TableDataInfo.build(result);
    }

    /**
     * 查询机器人信息列表
     */
    @Override
    public List<ObBusinessVo> queryList(ObBusinessBo bo) {
        LambdaQueryWrapper<ObBusiness> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public List<Item> selectRobotLabel(long bizNo) {
        return baseMapper.selectRobotLabel(bizNo);
    }

    private LambdaQueryWrapper<ObBusiness> buildQueryWrapper(ObBusinessBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ObBusiness> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBizName()), ObBusiness::getBizName, bo.getBizName());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ObBusiness::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }


}
