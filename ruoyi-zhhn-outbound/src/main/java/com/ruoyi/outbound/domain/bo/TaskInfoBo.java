package com.ruoyi.outbound.domain.bo;

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
 * 任务列业务对象 ob_task
 *
 * @author ruoyi
 * @date 2022-09-01
 */

@Data
//@EqualsAndHashCode(callSuper = true)
public class TaskInfoBo extends BaseEntity {

    /**
     * 任务ID
     */
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务日期
     */
//    private Date taskDate;

    /**
     * 街道id
     */
    private Long regionId;

    /**
     * 是否发送短信
     */
    private Integer sendMsgStatus;


}
