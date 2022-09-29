package com.ruoyi.outbound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * 短信发送记录对象 send_message_log
 */
@Data
@TableName("data_import.ob_send_message_log")
public class SendMessageLog {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务ID
     */
    private Long taskId;

    private String taskName;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送内容
     */
    private String sendContent;

    /**
     * 发送手机号
     */
    private String sendMobile;

    /**
     * 发送用户Id
     */
    private Long sendUserId;

    private String result;

    /**
     * 是否删除(0：否1：是)
     */
    private Integer isDelete;

    private Date createTime;

    private Date updateTime;
    /**
     * 手动发送短信批次id
     */
    private String batchId;
}


