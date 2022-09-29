package com.ruoyi.outbound.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.outbound.common.ImportUtils;
import com.ruoyi.outbound.domain.MsgTemplate;
import com.ruoyi.outbound.domain.bo.TaskInfoBo;
import com.ruoyi.outbound.domain.bo.TaskResultBo;
import com.ruoyi.outbound.domain.vo.ImportVo;
import com.ruoyi.outbound.entity.*;
import com.ruoyi.outbound.entity.dto.BatchDetailInfoDto;
import com.ruoyi.outbound.entity.dto.BatchInfoDto;
import com.ruoyi.outbound.mapper.*;
import com.ruoyi.outbound.service.*;
import com.ruoyi.outbound.utils.ImportExcel;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImportServiceImpl implements ImportService {

    private static Logger logger = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Autowired
    private TaskResultMapper taskResultMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ImportExcel importExcel;

    @Autowired
    private BatchInfoService batchInfoService;

    @Autowired
    private MessageService messageService;

    @Autowired
    ISendMessageLogService sendMessageLogService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImportUtils importUtils;

    @Autowired
    private TaskResultService taskResultService;

    @Autowired
    private IMsgTemplateService msgTemplateService;

    @Autowired
    private RegionInfoService regionInfoService;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    static SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    @Value("${spring.profiles.active}")
    private String runTime;

//    @DS("master")
    @Override
    @Transactional
    public boolean importExcel(MultipartFile importFile, Map<Integer, String> errorMap, List<ImportVo> errors,Integer regionId) throws Exception {
        File file = importExcel.transferToFile(importFile);
        Workbook book = importExcel.getWorkbook(file);
        if(book == null){
            return  false;
        }
        long taskId  = saveTask(book.getSheetAt(0),regionId);
        for(int i =1; i<=book.getNumberOfSheets()-1; i++){
            Sheet sheet = book.getSheetAt(i);

            List<ImportVo> importVoList =  importExcel.readExcel(ImportVo.class,sheet,i,errorMap,errors);
            if(errors != null && !errors.isEmpty()){
                throw new RuntimeException("导入失败"); //导入失败抛出异常回滚事务
            }
            for(ImportVo importVo : importVoList){
                User user =  userService.saveUser(importVo.getPhone(),importVo.getProvince(),importVo.getCity(),"","");
                if(user == null)return false;
                TaskResult taskResult = new TaskResult();
                taskResult.setUserId(user.getId());
                taskResult.setUserTag(sheet.getSheetName().trim());
                taskResult.setCreateTime(new Date());
                taskResult.setTaskId(taskId);
                BeanUtils.copyProperties(importVo,taskResult,TaskResult.class);
                taskResultMapper.insert(taskResult);
            }
        }
        return true;
    }


    @Override
    public long saveTask(Sheet sheet,Integer regionId) throws ParseException {
        Task task = null;
        task = taskMapper.selectOne(new QueryWrapper<Task>().
                eq("name",sheet.getSheetName()));
        if(!ObjectUtils.isEmpty(task)){
            task = new Task();
            return task.getId();
        }
        task = new Task();
        task.setName(sheet.getSheetName());
        Row oneRow = sheet.getRow(1);
        Cell cell = oneRow.getCell(1);
        String title = cell.getStringCellValue();
        String [] titleArr = title.split("：");
        String taskNo = titleArr[1];
        task.setTaskNo(taskNo);
        Row fourRow = sheet.getRow(1);
        cell = fourRow.getCell(1);
        task.setTaskDate(dateFormat.parse(cell.getStringCellValue()));
        cell = fourRow.getCell(4);
        task.setContent(cell.getStringCellValue());
        task.setCreateTime(new Date());
        task.setIsDelete(0);
        task.setStatus(0);
        task.setRegionId(regionId);
        taskMapper.insert(task);
        return task.getId();
    }

    @XxlJob("getBatchInfoJob")
    public void getBatchInfoList(){
        try{
            List<BatchInfoDto> batchInfoDtoList = batchInfoService.selectBatchInfoList(runTime);
            for(BatchInfoDto batch : batchInfoDtoList){
                if(runTime.equals("prod") && batch.getResPhoneNum()<5){
                    continue;
                }
                long count = taskMapper.countByBatchId(batch
                    .getTaskId()); //查询是否同步批次任务
                List<BatchInfoDto> labels = new ArrayList<>();
                int isDel =0;
                if(!ObjectUtils.isEmpty(batch.getBizNo())){
                    labels = batchInfoService.selectConfigLabelCategory(batch.getBizNo()); //查询是否存在人群标签
                    if(CollectionUtils.isEmpty(labels)){
                        //如果无人群标签则查询通知
                        isDel = 2;  //将labels设为通知类型任务的labels
                        labels = batchInfoService.selectConfigActionReplyDetail(batch.getBizNo());
                    }
                }
                if(count<=0){ //未同步则开始同步
                    //保存任务
                    Task task = new Task();
                    taskMapper.insert(importUtils.convertTOTask(task,batch));//保存任务信息
                    asyncSaveTask(task.getId(),batch.getTaskId(),task); //异步同步任务数据

                    userTagService.batchInsertUserTag(labels,task.getId(),isDel);
                }else{ //已同步，或同步中状态
                    List<Task> taskList = taskMapper.selectByBatchIdAndStatusTaskList(batch.getTaskId(),2); //查询同步中或未同步的批次任务
                    if(CollectionUtils.isEmpty(taskList)) {
                        continue;
                    }
                    Task task = taskList.get(0);
                    if(userTagService.countByTaskId(task.getId())<1){
                        userTagService.batchInsertUserTag(labels,task.getId(),isDel);
                    }
                    long taskCount = taskResultMapper.countTaskResultByTaskId(task.getId()); //获取已同步号码数量
                    long batchDetailCount = batchInfoService.countBatchDetail(batch.getTaskId());//获取总号码数量
                    if(taskCount!= batchDetailCount){ //判断本地库是否与外呼库数据是否相同
                        asyncSaveTask(task.getId(),batch.getTaskId(),task); //数量不相同则异步同步任务数据
                    }
                }
            }
            logger.info("任务同步完成："+dateFormat1.format(new Date()));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    //异步处理任务
    @Async
    public void asyncSaveTask(long taskId,long batchId,Task task){
        long start = System.currentTimeMillis();
        task.setId(taskId);
        task.setStatus(1);
        taskMapper.updateById(task); //把任务状态修改为同步中
        MsgTemplate msgTemplate = null;
        RegionInfo regionInfo = regionInfoService.getById(task.getRegionId());
        if(!ObjectUtils.isEmpty(regionInfo)){
            if(task.getRegionId()!= null){
                List<MsgTemplate> msgTemplateList = msgTemplateService.getMsgTemplateList(null,taskId,null); //任务所属模板
                if(CollectionUtils.isEmpty(msgTemplateList)){
                    msgTemplateList = msgTemplateService.getMsgTemplateList(task.getRegionId(),null,null); //街道所属模板
                }
                if(!CollectionUtils.isEmpty(msgTemplateList)){
                    msgTemplate = msgTemplateList.get(0);
                }
            }
        }
        List<String> successPhoneNoList =batchInfoService.selectSuccessPhoneNo(batchId); //获取呼叫成功的手机号码
        List<String> phoneList = taskResultMapper.selectPhoneNo(taskId); //获取已同步的数据
        List<String> resultList = importUtils.getDiffrent(successPhoneNoList,phoneList); //获取未同步的数据
        if(CollectionUtils.isEmpty(resultList)){
            return;
        }
        int round = 0;
        if(resultList.size()<=500){
            round = 0;
        }else{
            // 获取执行的轮次
             round = (resultList.size() - 1) / 500;
        }
        for(int i = 0; i <= round; i++){
            // 求每一个批次起始位置
            int fromIndex = i * 500;
            int toIndex = (i + 1) * 500;
            // 若是是最后一个批次，则不能越界
            if (i == round){
                toIndex = resultList.size();
            }
            List<String> subList = resultList.subList(fromIndex, toIndex);
            List<String> sentList = new ArrayList<>();
            List<SendMessageLog> sendMessageLogList = new ArrayList<>();
            logger.info("轮次：" + i);
            List<BatchDetailInfoDto> batchDetailInfoDtoList = batchInfoService.selectDetailsByPhones(subList,batchId);
            List<TaskResult> taskResults = new ArrayList<>();
            for(BatchDetailInfoDto batchDetailInfoDto : batchDetailInfoDtoList){
                User user = userService.saveUser(batchDetailInfoDto.getPhoneNo(),batchDetailInfoDto.getProvince(),batchDetailInfoDto.getCity(),batchDetailInfoDto.getContent(),batchDetailInfoDto.getCarrierCode()); //获取用户
                if(user == null){
                    continue;
                }
                if(batchDetailInfoDto.getStatus() == 3 || batchDetailInfoDto.getStatus() == 4 || batchDetailInfoDto.getStatus() == 5){ //判断号码是否能发送短信
                   String label = importUtils.getUserTags(taskId,batchDetailInfoDto); //获取用户标签Item
                    List<MsgTemplate> msgTemplateList = msgTemplateService.getMsgTemplateList(task.getRegionId(),taskId,label); //根据标签获取发送短信信息
                   if(!CollectionUtils.isEmpty(msgTemplateList)){
                       MsgTemplate labMsgTemplate = msgTemplateList.get(0);
                       String result = messageService.sendMessage(batchDetailInfoDto.getPhoneNo(), labMsgTemplate.getTempContent(),regionInfo.getMsgId());
                       sendMessageLogList.add(importUtils.convertTOSendMessageLog(taskId, null, batchDetailInfoDto.getPhoneNo(), labMsgTemplate.getTempContent(),result));
                       sentList.add(batchDetailInfoDto.getPhoneNo());
                   }
                }
                taskResults.add(importUtils.convertTOTaskResult(taskId, user.getId(),batchDetailInfoDto.getLabel(),batchDetailInfoDto));
            }
            //保存任务批次数据
            taskResultService.batchInsertTaskResult(taskResults);
            //开始发送短信
            if(null != msgTemplate && !StringUtils.isEmpty(msgTemplate.getTempContent())){
                List<String> notSentList = importUtils.getDiffrent(subList,sentList); //获取未发送的手机号
                String result = messageService.sendMessage(String.join(",", notSentList), msgTemplate.getTempContent(),regionInfo.getMsgId());//发送短信
                if(!StringUtils.isEmpty(result)){
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    if(jsonObject.getString("result").equals("1")){
                        logger.info("发送短信成功！！！"+result);
                    }else{
                        logger.info("发送短信失败！！！"+result);
                    }
                    for(String phone : notSentList){
                        sendMessageLogList.add(importUtils.convertTOSendMessageLog(taskId, null, phone, msgTemplate.getTempContent(),result));
                    }
                    sendMessageLogService.batchInsertSendMassageLog(sendMessageLogList);
                }
            }

        }
        logger.info("{}号任务同步完成,耗时{}毫秒",taskId,(System.currentTimeMillis() - start));
    }
}
