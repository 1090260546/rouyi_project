package com.ruoyi.outbound.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机器人标签管理对象 export_template_label
 *
 * @author ruoyi
 * @date 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("export_template_label")
public class ExportLabels extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 模板名称
     */
    private String bizName;
    /**
     * 机器人ID
     */
    private Long bizNo;
    /**
     * 标签项
     */
    private String obLabels;
    /**
     * 外呼状态
     */
    private String obStatus;
    /**
     * 模板标签页
     */
    private String sheetName;
    /**
     * 是否删除（1：是，0：否）
     */
    private Integer isDelete;

}
