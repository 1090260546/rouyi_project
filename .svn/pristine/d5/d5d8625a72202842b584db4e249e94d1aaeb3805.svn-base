package com.ruoyi.outbound.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.outbound.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@DS("master")
public interface TaskMapper extends BaseMapper<Task> {

    @Select("select count(0) from data_import.ob_task where batch_id = #{batchId}")
    long countByBatchId(@Param("batchId")long batchId);

    @Select("select * from data_import.ob_task where batch_id = #{batchId} and status != #{status}")
    List<Task> selectByBatchIdAndStatusTaskList(@Param("batchId") long batchId, @Param("status") int status);


}
