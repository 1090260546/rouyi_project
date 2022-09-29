package com.ruoyi.outbound.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.outbound.domain.vo.RegionInfoVo;
import com.ruoyi.outbound.entity.RegionInfo;
import com.ruoyi.outbound.mapper.RegionInfoMapper;
import com.ruoyi.outbound.service.RegionInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RegionInfoServiceImpl extends ServiceImpl<RegionInfoMapper, RegionInfo > implements RegionInfoService {

    @Autowired
    private RegionInfoMapper regionInfoMapper;

    @Override
//    @DS("master")
    public List<RegionInfoVo> getList() {
        List<RegionInfo> list = regionInfoMapper.selectList(null);
        List<RegionInfoVo> resultVoList = new ArrayList<>();
        for(RegionInfo regionInfo : list){
            RegionInfoVo regionInfoVo = new RegionInfoVo();
            BeanUtils.copyProperties(regionInfo,regionInfoVo,RegionInfoVo.class);
            resultVoList.add(regionInfoVo);
        }
        return resultVoList;
    }

    @Override
    public List<RegionInfo> getListByName(String name) {
        return regionInfoMapper.selectList(new QueryWrapper<RegionInfo>().eq("name",name));
    }

    @Override
    public boolean add(RegionInfoVo regionInfoVo) {
        RegionInfo regionInfo = new RegionInfo();
        BeanUtils.copyProperties(regionInfoVo,regionInfo,RegionInfoVo.class);
        regionInfo.setCreateTime(new Date());
        return regionInfoMapper.insert(regionInfo)>=1;
    }

    @Override
    public boolean update(RegionInfoVo regionInfoVo) {
        RegionInfo regionInfo = new RegionInfo();
        BeanUtils.copyProperties(regionInfoVo,regionInfo,RegionInfoVo.class);
        return regionInfoMapper.updateById(regionInfo)>=1;
    }

    @Override
    public boolean deleteById(int id) {
        return regionInfoMapper.deleteById(id)>=1;
    }
}
