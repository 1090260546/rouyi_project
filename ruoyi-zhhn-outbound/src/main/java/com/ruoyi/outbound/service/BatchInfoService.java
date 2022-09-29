package com.ruoyi.outbound.service;


import com.ruoyi.outbound.entity.dto.BatchDetailInfoDto;
import com.ruoyi.outbound.entity.dto.BatchInfoDto;

import java.util.List;

public interface BatchInfoService {


    List<BatchInfoDto> selectBatchInfoList(String runTime);

    List<BatchDetailInfoDto> selectBatchDetailList(Long batchId);
    long countBatchDetail(long batchId);
    List<String> selectSuccessPhoneNo(long batchId);
    List<BatchDetailInfoDto> selectDetailsByPhones(List<String> phones,long batchId );


    List<BatchInfoDto> selectConfigLabelCategory(long robotId);

    List<BatchInfoDto> selectConfigActionReplyDetail(long robotId);


    List<BatchDetailInfoDto> countByBatchId(long batchId);


    List<BatchDetailInfoDto> getByBatchIdAndPhoneNo(long batchId,String phoneNo);

}
