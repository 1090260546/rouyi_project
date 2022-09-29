package com.ruoyi.outbound.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 短信发送模板业务对象 ob_message_template
 *
 * @author ruoyi
 * @date 2022-08-31
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MsgTemplateBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 模板名称
     */
    //@NotBlank(message = "模板名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tempName;

    /**
     * 模板内容
     */
    //@NotBlank(message = "模板内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tempContent;

    /**
     * 街道名称
     */
    //@NotBlank(message = "街道名称不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
