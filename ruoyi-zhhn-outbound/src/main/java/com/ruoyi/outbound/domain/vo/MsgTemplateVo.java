package com.ruoyi.outbound.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 短信发送模板视图对象 ob_message_template
 *
 * @author ruoyi
 * @date 2022-08-31
 */
@Data
@ExcelIgnoreUnannotated
public class MsgTemplateVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 模板名称
     */
    @ExcelProperty(value = "模板名称")
    private String tempName;

    /**
     * 模板内容
     */
    @ExcelProperty(value = "模板内容")
    private String tempContent;

    /**
     * 街道名称
     */
    @ExcelProperty(value = "街道名称", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ob_region_info")
    private Integer tempRegion;

    private Integer tempTaskid;
    /**
     * 任务名称
     */
    @ExcelProperty(value = "任务名称")
    private String tempTaskName;
    private String tempLabel;
    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String tempLabelName;
    /**
     * 呼叫状态
     */
    @ExcelProperty(value = "呼叫状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ob_template_type")
    private Integer tempType;

}
