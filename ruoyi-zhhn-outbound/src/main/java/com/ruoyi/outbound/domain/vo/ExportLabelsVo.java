package com.ruoyi.outbound.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 机器人标签管理视图对象 export_template_label
 *
 * @author ruoyi
 * @date 2022-09-22
 */
@Data
@ExcelIgnoreUnannotated
public class ExportLabelsVo {

    private static final long serialVersionUID = 1L;

    /**
     * 模板名称
     */
    @ExcelProperty(value = "模板名称")
    private String bizName;

    /**
     * 机器人ID
     */
    @ExcelProperty(value = "机器人ID")
    private Long bizNo;

    /**
     * 标签项
     */
    @ExcelProperty(value = "标签项")
    private String obLabels;

    /**
     * 外呼状态
     */
    @ExcelProperty(value = "外呼状态")
    private String obStatus;

    /**
     * 模板标签页
     */
    @ExcelProperty(value = "模板标签页")
    private String sheetName;

    /**
     * 是否删除（1：是，0：否）
     */
    @ExcelProperty(value = "是否删除", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=：是，0：否")
    private Integer isDelete;


}
