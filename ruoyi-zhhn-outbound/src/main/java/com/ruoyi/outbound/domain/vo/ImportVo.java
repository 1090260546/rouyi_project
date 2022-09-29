package com.ruoyi.outbound.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import java.util.Date;

public class ImportVo {

    @ExcelProperty(value = "客户手机号码")
    private String phone;

    @ExcelProperty(value = "号码归属地省份")
    private String province;

    @ExcelProperty(value = "号码归属地城市")
    private String city;

    @ExcelProperty(value = "呼叫时间")
    private Date callTime;

    @ExcelProperty(value = "呼叫时长")
    private Integer callDuration;

    @ExcelProperty(value = "接通时间")
    private Date onTime;

    @ExcelProperty(value = "通话结果")
    private String result;

    @ExcelProperty(value = "录音文件地址")
    private String recordingUrl;

    @ExcelProperty(value =  "拨打次数")
    private Integer numberOfCall;

    public Integer getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Integer callDuration) {
        this.callDuration = callDuration;
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    public Integer getNumberOfCall() {
        return numberOfCall;
    }

    public void setNumberOfCall(Integer numberOfCall) {
        this.numberOfCall = numberOfCall;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }
}
