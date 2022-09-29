package com.ruoyi.outbound.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 机器人信息视图对象 ob_business
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@Data
@ExcelIgnoreUnannotated
public class ObBusinessVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 机器人编号
     */
    @ExcelProperty(value = "机器人编号")
    private Long bizNo;

    /**
     * 机器人名称
     */
    @ExcelProperty(value = "机器人名称")
    private String bizName;

    /**
     * 业务描述
     */
    @ExcelProperty(value = "业务描述")
    private String desc;

    /**
     * 创建人ID
     */
    @ExcelProperty(value = "创建人ID")
    private Long createUserId;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private String createUser;

    /**
     * 状态描述
     */
    @ExcelProperty(value = "状态描述")
    private String statusDesc;


}
