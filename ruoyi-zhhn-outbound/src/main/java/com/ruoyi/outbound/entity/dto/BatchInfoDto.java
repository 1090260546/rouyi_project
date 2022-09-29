package com.ruoyi.outbound.entity.dto;

import java.util.Date;

public class BatchInfoDto {

    private Long taskId;

    private String BatchName;

    private String BatchInfoDesc;

    private Date BeginDate;

    private Date EndDate;

    private String TimePhase;

    private Integer ResPhoneNum;

    private Integer ResSubmitNum;

    private Integer RetSuccNum;

    private Integer RetFailNum;

    private Integer RetOutDateNum;

    private Integer SvcTime;

    private String TaskName;

    private Long BusinessId;

    private String BusinessName;

    private Long BizNo;


    private String Desc;

    private String Category;


    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getBatchName() {
        return BatchName;
    }

    public void setBatchName(String batchName) {
        BatchName = batchName;
    }

    public String getBatchInfoDesc() {
        return BatchInfoDesc;
    }

    public void setBatchInfoDesc(String batchInfoDesc) {
        BatchInfoDesc = batchInfoDesc;
    }

    public Date getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(Date beginDate) {
        BeginDate = beginDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getTimePhase() {
        return TimePhase;
    }

    public void setTimePhase(String timePhase) {
        TimePhase = timePhase;
    }

    public Integer getResPhoneNum() {
        return ResPhoneNum;
    }

    public void setResPhoneNum(Integer resPhoneNum) {
        ResPhoneNum = resPhoneNum;
    }

    public Integer getResSubmitNum() {
        return ResSubmitNum;
    }

    public void setResSubmitNum(Integer resSubmitNum) {
        ResSubmitNum = resSubmitNum;
    }

    public Integer getRetSuccNum() {
        return RetSuccNum;
    }

    public void setRetSuccNum(Integer retSuccNum) {
        RetSuccNum = retSuccNum;
    }

    public Integer getRetFailNum() {
        return RetFailNum;
    }

    public void setRetFailNum(Integer retFailNum) {
        RetFailNum = retFailNum;
    }

    public Integer getRetOutDateNum() {
        return RetOutDateNum;
    }

    public void setRetOutDateNum(Integer retOutDateNum) {
        RetOutDateNum = retOutDateNum;
    }

    public Integer getSvcTime() {
        return SvcTime;
    }

    public void setSvcTime(Integer svcTime) {
        SvcTime = svcTime;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public Long getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(Long businessId) {
        BusinessId = businessId;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public Long getBizNo() {
        return BizNo;
    }

    public void setBizNo(Long bizNo) {
        BizNo = bizNo;
    }
}
