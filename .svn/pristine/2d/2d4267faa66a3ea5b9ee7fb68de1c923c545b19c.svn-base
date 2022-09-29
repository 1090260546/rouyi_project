package com.ruoyi.outbound.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.outbound.domain.bo.TaskResultBo;
import com.ruoyi.outbound.entity.TaskResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@DS("master")
public interface TaskResultMapper extends BaseMapper<TaskResult> {

    @Select("SELECT\n" +
            "            t2.phone\n" +
            "        FROM\n" +
            "             data_import.ob_task_result t1 left join data_import.ob_user t2 on t1.user_id = t2.id\n" +
            "        where t1.task_id = #{taskId};")
    List<String> selectPhoneNo(@Param("taskId")long taskId);

    @Select("select count(1) from data_import.ob_task_result where task_id = #{taskId}")
    long countTaskResultByTaskId(@Param("taskId")long taskId);


    @Select("SELECT\n" +
        "\t result,COUNT(1) countNum,task_id\n" +
        "FROM\n" +
        "\t data_import.ob_task_result \n" +
        "WHERE\n" +
        "\ttask_id = #{taskId}\n" +
        "\tgroup by result")
    List<TaskResultBo> countByTaskId(@Param("taskId") long taskId);
}
