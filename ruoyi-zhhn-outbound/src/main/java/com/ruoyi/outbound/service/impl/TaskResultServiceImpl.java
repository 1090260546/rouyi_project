package com.ruoyi.outbound.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.outbound.domain.bo.TaskInfoBo;
import com.ruoyi.outbound.domain.bo.TaskResultBo;
import com.ruoyi.outbound.entity.TaskResult;
import com.ruoyi.outbound.mapper.TaskResultMapper;
import com.ruoyi.outbound.service.TaskResultService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskResultServiceImpl extends ServiceImpl<TaskResultMapper,TaskResult> implements TaskResultService {

    @Autowired
    private TaskResultMapper taskResultMapper;


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void batchInsertTaskResult(List<TaskResult> taskResults) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        try{
            long start = System.currentTimeMillis();
            TaskResultMapper taskResultMapperNew = sqlSession.getMapper(TaskResultMapper.class);
            taskResults.forEach(taskResultMapperNew::insert);
            sqlSession.commit();
            sqlSession.clearCache();
            System.out.println(System.currentTimeMillis() - start);
        }catch (Exception exception){
            sqlSession.rollback();
            exception.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Override
    public List<TaskResultBo> countByTaskId(long taskId) {
        return taskResultMapper.countByTaskId(taskId);
    }
}
