package com.ruoyi.outbound.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 短信发送模板对象 ob_message_template
 *
 * @author ruoyi
 * @date 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ob_message_template")
public class MsgTemplate extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 模板名称
     */
    private String tempName;
    /**
     * 模板内容
     */
    private String tempContent;
    /**
     * 街道名称
     */
    private Integer tempRegion;
    /**
     * 任务名称
     */
    private Integer tempTaskid;
    /**
     * 标签名称
     */
    private String tempLabel;
    /**
     * 呼叫状态
     */
    private Integer tempType;
    /**
     * 是否删除(0：否1：是)
     */
    private String isDelete;

}
