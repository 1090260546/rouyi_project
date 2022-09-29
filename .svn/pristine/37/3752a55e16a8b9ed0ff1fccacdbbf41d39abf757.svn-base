package com.ruoyi.outbound.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机器人标签管理业务对象 export_template_label
 *
 * @author ruoyi
 * @date 2022-09-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ExportLabelsBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bizName;

    /**
     * 机器人ID
     */
    @NotNull(message = "机器人ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bizNo;

    /**
     * 标签项
     */
    @NotBlank(message = "标签项不能为空", groups = { AddGroup.class, EditGroup.class })
    private String obLabels;

    /**
     * 外呼状态
     */
    @NotBlank(message = "外呼状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String obStatus;

    /**
     * 模板标签页
     */
    @NotBlank(message = "模板标签页不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sheetName;

    /**
     * 是否删除（1：是，0：否）
     */
    @NotNull(message = "是否删除（1：是，0：否）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer isDelete;


}
