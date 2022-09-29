package com.ruoyi.outbound.domain.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 短信发送模板对象
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SendMessageBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送内容
     */
    @NotBlank(message = "发送内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sendContent;

    /**
     * 发送手机号
     */
    @NotBlank(message = "号码不能为空")
    @ExcelProperty(value = "手机号码")
    private String sendMobile;

    /**
     * 发送用户Id
     */
    private Long sendUserId;

    /**
     * 批次号
     */
    private String batchId;

    private String regionId;


}
