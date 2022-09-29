package com.ruoyi.outbound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("data_import.ob_task")
public class Task {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String taskNo;

    private String name;

    private String content;

    private Date taskDate;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private Integer regionId;

    private Integer resPhoneNum;

    private Integer resSubmitNum;

    private Integer retSuccNum;

    private Integer retFailNum;

    private Integer retOutDateNum;

    private Integer svcTime;

    private Long batchId;


    private Integer status;

    private Long bizNo;

    public Long getBizNo() {
        return bizNo;
    }

    public void setBizNo(Long bizNo) {
        this.bizNo = bizNo;
    }

    public Integer getResPhoneNum() {
        return resPhoneNum;
    }

    public void setResPhoneNum(Integer resPhoneNum) {
        this.resPhoneNum = resPhoneNum;
    }

    public Integer getResSubmitNum() {
        return resSubmitNum;
    }

    public void setResSubmitNum(Integer resSubmitNum) {
        this.resSubmitNum = resSubmitNum;
    }

    public Integer getRetSuccNum() {
        return retSuccNum;
    }

    public void setRetSuccNum(Integer retSuccNum) {
        this.retSuccNum = retSuccNum;
    }

    public Integer getRetFailNum() {
        return retFailNum;
    }

    public void setRetFailNum(Integer retFailNum) {
        this.retFailNum = retFailNum;
    }

    public Integer getRetOutDateNum() {
        return retOutDateNum;
    }

    public void setRetOutDateNum(Integer retOutDateNum) {
        this.retOutDateNum = retOutDateNum;
    }

    public Integer getSvcTime() {
        return svcTime;
    }

    public void setSvcTime(Integer svcTime) {
        this.svcTime = svcTime;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}
