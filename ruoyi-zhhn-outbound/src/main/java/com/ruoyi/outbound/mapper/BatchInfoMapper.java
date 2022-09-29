package com.ruoyi.outbound.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.outbound.entity.Item;
import com.ruoyi.outbound.entity.dto.BatchDetailInfoDto;
import com.ruoyi.outbound.entity.dto.BatchInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BatchInfoMapper extends BaseMapper<BatchInfoDto> {
    @Select(" <script>SELECT\n" +
        "            t1.id taskId,\n" +
        "            t1.BatchName,\n" +
        "            t1.BatchInfoDesc,\n" +
        "            t1.BeginDate,\n" +
        "            t1.EndDate,\n" +
        "            t1.TimePhase,\n" +
        "            t1.ResPhoneNum,\n" +
        "            t1.ResSubmitNum,\n" +
        "            t1.RetSuccNum,\n" +
        "            t1.RetFailNum,\n" +
        "            t1.RetOutDateNum,\n" +
        "            t1.SvcTime,\n" +
        "            t2.TaskName,\n" +
        "            t2.BusinessId,\n" +
        "            t2.BusinessName,\n" +
        "            t3.BizNo\n" +
        "        FROM\n" +
        "            aisp_saas.marketbatchinfo t1\n" +
        "                LEFT JOIN aisp_saas.markettaskinfo t2 ON t1.TaskId = t2.Id\n" +
        "                left join aisp_saas.business t3 on t2.BusinessId = t3.Id\n" +
        "          <if test=\"runTime != null and runTime == 'prod'\">where t1.CreateUser = 'xiaoanOperator'</if>"+
        "          <if test=\"runTime != null and runTime == 'dev'\">where t1.CreateUser = 'xiaoanOperator'</if>"+
        "        ORDER BY\n" +
        "            t1.CreateTime DESC </script>")
    List<BatchInfoDto> selectBatchInfoList(String runTime);

    @Select("  SELECT\n" +
        "            *\n" +
        "        FROM\n" +
        "            aisp_saas.marketbatchdetails t1\n" +
        "                LEFT JOIN aisp_jydp.logusersessioninfo t2 ON t1.TransactionId = t2.Sid\n" +
        "        where t1.BatchId = #{batchId}")
    List<BatchDetailInfoDto> selectBatchDetailList(Long batchId);
    @Select("select count(t1.id) from aisp_saas.marketbatchdetails t1 where t1.BatchId = #{batchId}")
    long countBatchDetail(@Param("batchId")long batchId);
    @Select(" SELECT\n" +
        "            PhoneNo\n" +
        "        FROM\n" +
        "            aisp_saas.marketbatchdetails t1\n" +
        "        where t1.BatchId = #{batchId} and (t1.Status = 3 or t1.Status = 4 or t1.Status =6  or t1.Status = 8  or t1.Status = 9 or t1.Status = 5)")
    List<String> selectSuccessPhoneNo(@Param("batchId") long batchId);

    @Select(" <script>SELECT\n" +
        " t1.Id batchDetailId,\n"+
        " t1.PhoneNo,\n" +
        "t1.Province,\n" +
        "t1.City,\n" +
        "t1.Reason,\n" +
        "t1.WavUrl,\n" +
        "t1.CallTime,\n" +
        "t1.BeginTime,\n" +
        "t1.ElapsedTime,\n" +
        "t1.CurrentCallTime,\n" +
        "t1.CarrierCode,\n" +
        "t2.Label,\n" +
        "t1.STATUS,\n" +
        "t3.Item1,\n" +
        "t3.Item2,\n" +
        "t3.Item3,\n" +
        "t3.Item4,\n" +
        "t3.Item5,\n" +
        "t3.Item6,\n" +
        "t3.Item7,\n" +
        "t3.Item8,\n" +
        "t3.Item9,\n" +
        "t3.Item10,\n" +
        "t3.Item11,\n" +
        "t3.Item12,\n" +
        "t3.Item13,\n" +
        "t3.Item14,\n" +
        "t3.Item15,\n" +
        "t3.Item16,\n" +
        "t3.Item17,\n" +
        "t3.Item18,\n" +
        "t3.Item19,\n" +
        "t3.Item20,\n" +
        "t3.Item21,\n" +
        "t3.Item22,\n" +
        "t3.Item23,\n" +
        "t3.Item24,\n" +
        "t3.Item25,\n" +
        "t3.Item26,\n" +
        "t3.Item27,\n" +
        "t3.Item28,\n" +
        "t3.Item29,\n" +
        "t3.Item30,\n" +
        "t3.Item31,\n" +
        "t3.Item32,\n" +
        "t3.Item33,\n" +
        "t3.Item34,\n" +
        "t3.Item35,\n" +
        "t3.Item36,\n" +
        "t3.Item37,\n" +
        "t3.Item38,\n" +
        "t3.Item39,\n" +
        "t3.Item40\n" +
        "        FROM\n" +
        "        aisp_saas.marketbatchdetails t1\n" +
        "        LEFT JOIN aisp_jydp.logusersessioninfo t2 ON t1.TransactionId = t2.Sid\n" +
        "           left join aisp_saas.StatMarketBatchDetailData t3 ON t1.Id = t3.BatchDetailId \n" +
        "        WHERE\n" +
        "        1 = 1  and t1.BatchId = #{batchId} and (t1.Status = 3 or t1.Status = 4 or t1.Status =6  or t1.Status = 8  or t1.Status = 9 or t1.Status = 5)" +
        "        and  t1.PhoneNo in\n" +
        "        <foreach collection=\"phones\" item=\"phone\" index=\"index\" open=\"(\" close=\")\" separator=\",\">\n" +
        "            #{phone}\n" +
        "        </foreach>"+
        "</script>")
    List<BatchDetailInfoDto> selectDetailsByPhones(@Param("phones") List<String> phones, @Param("batchId")long batchId );

    @Select("SELECT\n" +
        " t1.Desc  \n" +
        " ,t1.Category  \n" +
        "FROM\n" +
        " aisp_jydp.configlabelcategory t1\n" +
        " LEFT JOIN aisp_jydp.flowversion t2 ON t1.VersionId = t2.Id\n" +
        " left JOIN aisp_jydp.robot t3 ON t2.robotId = t3.Id\n" +
        " where t3.Id = #{robotId}")
    List<BatchInfoDto> selectConfigLabelCategory(@Param("robotId") long robotId);


    @Select(" \n" +
        "SELECT\n" +
        " DISTINCT t1.Content `Desc`,\n" +
        " 'item1' Category"+
        "FROM\n" +
        "configactionreplydetail t1\n" +
        "LEFT JOIN flowversion t2 ON t1.VersionId = t2.Id where t2.RobotId = #{robotId}")
    List<BatchInfoDto> selectConfigActionReplyDetail(@Param("robotId") long robotId);



    @Select("select Reason,COUNT(1) countNum from marketbatchdetails where BatchId = #{batchId} GROUP BY Reason")
    List<BatchDetailInfoDto> countByBatchId(@Param("batchId") long batchId);


//    @Select("SELECT * FROM marketbatchdetails WHERE BatchId = #{batchId} AND PhoneNo = #{phoneNo} ")

    @Select("SELECT\n" +
        " t1.Id batchDetailId,\n"+
        " t1.PhoneNo,\n" +
        "t1.Province,\n" +
        "t1.City,\n" +
        "t1.Reason,\n" +
        "t1.WavUrl,\n" +
        "t1.CallTime,\n" +
        "t1.BeginTime,\n" +
        "t1.ElapsedTime,\n" +
        "t1.CurrentCallTime,\n" +
        "t1.CarrierCode,\n" +
        "t2.Label,\n" +
        "t1.STATUS,\n" +
        "t3.Item1,\n" +
        "t3.Item2,\n" +
        "t3.Item3,\n" +
        "t3.Item4,\n" +
        "t3.Item5,\n" +
        "t3.Item6,\n" +
        "t3.Item7,\n" +
        "t3.Item8,\n" +
        "t3.Item9,\n" +
        "t3.Item10,\n" +
        "t3.Item11,\n" +
        "t3.Item12,\n" +
        "t3.Item13,\n" +
        "t3.Item14,\n" +
        "t3.Item15,\n" +
        "t3.Item16,\n" +
        "t3.Item17,\n" +
        "t3.Item18,\n" +
        "t3.Item19,\n" +
        "t3.Item20,\n" +
        "t3.Item21,\n" +
        "t3.Item22,\n" +
        "t3.Item23,\n" +
        "t3.Item24,\n" +
        "t3.Item25,\n" +
        "t3.Item26,\n" +
        "t3.Item27,\n" +
        "t3.Item28,\n" +
        "t3.Item29,\n" +
        "t3.Item30,\n" +
        "t3.Item31,\n" +
        "t3.Item32,\n" +
        "t3.Item33,\n" +
        "t3.Item34,\n" +
        "t3.Item35,\n" +
        "t3.Item36,\n" +
        "t3.Item37,\n" +
        "t3.Item38,\n" +
        "t3.Item39,\n" +
        "t3.Item40\n" +
        "        FROM\n" +
        "        aisp_saas.marketbatchdetails t1\n" +
        "        LEFT JOIN aisp_jydp.logusersessioninfo t2 ON t1.TransactionId = t2.Sid\n" +
        "           left join aisp_saas.StatMarketBatchDetailData t3 ON t1.Id = t3.BatchDetailId \n" +
        "        WHERE  t1.BatchId = #{batchId} AND t1.PhoneNo = #{phoneNo} ")
    List<BatchDetailInfoDto> getByBatchIdAndPhoneNo(@Param("batchId") long batchId, @Param("phoneNo") String phoneNo);


}
