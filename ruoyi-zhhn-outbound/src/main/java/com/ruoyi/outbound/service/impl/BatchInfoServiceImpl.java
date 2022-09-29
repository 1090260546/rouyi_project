package com.ruoyi.outbound.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.outbound.entity.dto.BatchDetailInfoDto;
import com.ruoyi.outbound.entity.dto.BatchInfoDto;
import com.ruoyi.outbound.mapper.BatchInfoMapper;
import com.ruoyi.outbound.service.BatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("slave")
public class BatchInfoServiceImpl implements BatchInfoService {

    @Autowired
    private BatchInfoMapper batchInfoMapper;

    @Override
    public List<BatchInfoDto> selectBatchInfoList(String runTime) {
        return batchInfoMapper.selectBatchInfoList(runTime);
    }

    @Override
    public List<BatchDetailInfoDto> selectBatchDetailList(Long batchId) {
        return batchInfoMapper.selectBatchDetailList(batchId);
    }

    @Override
    public long countBatchDetail(long batchId) {
        return batchInfoMapper.countBatchDetail(batchId);
    }

    @Override
    public List<String> selectSuccessPhoneNo(long batchId) {
        return batchInfoMapper.selectSuccessPhoneNo(batchId);
    }

    @Override
    public List<BatchDetailInfoDto> selectDetailsByPhones(List<String> phones, long batchId) {
        return batchInfoMapper.selectDetailsByPhones(phones,batchId);
    }

    @Override
    public List<BatchInfoDto> selectConfigLabelCategory(long robotId) {
        return batchInfoMapper.selectConfigLabelCategory(robotId);
    }

    @Override
    public List<BatchInfoDto> selectConfigActionReplyDetail(long robotId) {
        return batchInfoMapper.selectConfigActionReplyDetail(robotId);
    }

    @Override
    public List<BatchDetailInfoDto> countByBatchId(long batchId) {
        return batchInfoMapper.countByBatchId(batchId);
    }

    @Override
    public List<BatchDetailInfoDto> getByBatchIdAndPhoneNo(long batchId, String phoneNo) {
        return batchInfoMapper.getByBatchIdAndPhoneNo(batchId,phoneNo);
    }


}
