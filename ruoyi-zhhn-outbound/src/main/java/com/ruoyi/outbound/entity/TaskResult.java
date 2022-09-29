package com.ruoyi.outbound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("data_import.ob_task_result")
public class TaskResult {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private long taskId;


    private long batchDetailId;

    private Integer titleId;

    private Integer answerId;

    private String userTag;

    private Integer userId;

    private Date callTime;

    private Date onTime;

    private String recordingUrl;

    private Integer numberOfCall;

    private Long callDuration;

    private String result;

    private String content;

    private Date createTime;


    private Date updateTime;


    private Integer status;

    private String Item1;

    private String Item2;

    private String Item3;

    private String Item4;

    private String Item5;

    private String Item6;

    private String Item7;

    private String Item8;

    private String Item9;

    private String Item10;

    private String Item11;

    private String Item12;

    private String Item13;

    private String Item14;

    private String Item15;

    private String Item16;

    private String Item17;

    private String Item18;

    private String Item19;

    private String Item20;

    private String Item21;

    private String Item22;

    private String Item23;

    private String Item24;

    private String Item25;

    private String Item26;

    private String Item27;

    private String Item28;

    private String Item29;

    private String Item30;

    private String Item31;

    private String Item32;

    private String Item33;

    private String Item34;

    private String Item35;

    private String Item36;

    private String Item37;

    private String Item38;

    private String Item39;

    private String Item40;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getBatchDetailId() {
        return batchDetailId;
    }

    public void setBatchDetailId(long batchDetailId) {
        this.batchDetailId = batchDetailId;
    }

    public String getItem1() {
        return Item1;
    }

    public void setItem1(String item1) {
        Item1 = item1;
    }

    public String getItem2() {
        return Item2;
    }

    public void setItem2(String item2) {
        Item2 = item2;
    }

    public String getItem3() {
        return Item3;
    }

    public void setItem3(String item3) {
        Item3 = item3;
    }

    public String getItem4() {
        return Item4;
    }

    public void setItem4(String item4) {
        Item4 = item4;
    }

    public String getItem5() {
        return Item5;
    }

    public void setItem5(String item5) {
        Item5 = item5;
    }

    public String getItem6() {
        return Item6;
    }

    public void setItem6(String item6) {
        Item6 = item6;
    }

    public String getItem7() {
        return Item7;
    }

    public void setItem7(String item7) {
        Item7 = item7;
    }

    public String getItem8() {
        return Item8;
    }

    public void setItem8(String item8) {
        Item8 = item8;
    }

    public String getItem9() {
        return Item9;
    }

    public void setItem9(String item9) {
        Item9 = item9;
    }

    public String getItem10() {
        return Item10;
    }

    public void setItem10(String item10) {
        Item10 = item10;
    }

    public String getItem11() {
        return Item11;
    }

    public void setItem11(String item11) {
        Item11 = item11;
    }

    public String getItem12() {
        return Item12;
    }

    public void setItem12(String item12) {
        Item12 = item12;
    }

    public String getItem13() {
        return Item13;
    }

    public void setItem13(String item13) {
        Item13 = item13;
    }

    public String getItem14() {
        return Item14;
    }

    public void setItem14(String item14) {
        Item14 = item14;
    }

    public String getItem15() {
        return Item15;
    }

    public void setItem15(String item15) {
        Item15 = item15;
    }

    public String getItem16() {
        return Item16;
    }

    public void setItem16(String item16) {
        Item16 = item16;
    }

    public String getItem17() {
        return Item17;
    }

    public void setItem17(String item17) {
        Item17 = item17;
    }

    public String getItem18() {
        return Item18;
    }

    public void setItem18(String item18) {
        Item18 = item18;
    }

    public String getItem19() {
        return Item19;
    }

    public void setItem19(String item19) {
        Item19 = item19;
    }

    public String getItem20() {
        return Item20;
    }

    public void setItem20(String item20) {
        Item20 = item20;
    }

    public String getItem21() {
        return Item21;
    }

    public void setItem21(String item21) {
        Item21 = item21;
    }

    public String getItem22() {
        return Item22;
    }

    public void setItem22(String item22) {
        Item22 = item22;
    }

    public String getItem23() {
        return Item23;
    }

    public void setItem23(String item23) {
        Item23 = item23;
    }

    public String getItem24() {
        return Item24;
    }

    public void setItem24(String item24) {
        Item24 = item24;
    }

    public String getItem25() {
        return Item25;
    }

    public void setItem25(String item25) {
        Item25 = item25;
    }

    public String getItem26() {
        return Item26;
    }

    public void setItem26(String item26) {
        Item26 = item26;
    }

    public String getItem27() {
        return Item27;
    }

    public void setItem27(String item27) {
        Item27 = item27;
    }

    public String getItem28() {
        return Item28;
    }

    public void setItem28(String item28) {
        Item28 = item28;
    }

    public String getItem29() {
        return Item29;
    }

    public void setItem29(String item29) {
        Item29 = item29;
    }

    public String getItem30() {
        return Item30;
    }

    public void setItem30(String item30) {
        Item30 = item30;
    }

    public String getItem31() {
        return Item31;
    }

    public void setItem31(String item31) {
        Item31 = item31;
    }

    public String getItem32() {
        return Item32;
    }

    public void setItem32(String item32) {
        Item32 = item32;
    }

    public String getItem33() {
        return Item33;
    }

    public void setItem33(String item33) {
        Item33 = item33;
    }

    public String getItem34() {
        return Item34;
    }

    public void setItem34(String item34) {
        Item34 = item34;
    }

    public String getItem35() {
        return Item35;
    }

    public void setItem35(String item35) {
        Item35 = item35;
    }

    public String getItem36() {
        return Item36;
    }

    public void setItem36(String item36) {
        Item36 = item36;
    }

    public String getItem37() {
        return Item37;
    }

    public void setItem37(String item37) {
        Item37 = item37;
    }

    public String getItem38() {
        return Item38;
    }

    public void setItem38(String item38) {
        Item38 = item38;
    }

    public String getItem39() {
        return Item39;
    }

    public void setItem39(String item39) {
        Item39 = item39;
    }

    public String getItem40() {
        return Item40;
    }

    public void setItem40(String item40) {
        Item40 = item40;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
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

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }


    public Long getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Long callDuration) {
        this.callDuration = callDuration;
    }
}
