package com.ruoyi.outbound.domain.vo;

import java.util.Date;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import java.util.Date;



/**
 * 任务列视图对象 ob_task
 *
 * @author ruoyi
 * @date 2022-09-01
 */
@Data
@ExcelIgnoreUnannotated
public class TaskInfoVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @ExcelProperty(value = "任务ID")
    private Long id;

    /**
     * 任务名称
     */
    @ExcelProperty(value = "任务名称")
    private String name;

    /**
     * 任务内容
     */
    @ExcelProperty(value = "任务内容")
    private String content;

    /**
     * 任务编号
     */
    @ExcelProperty(value = "任务编号")
    private String taskNo;

    /**
     * 任务日期
     */
    @ExcelProperty(value = "任务日期")
    private Date taskDate;

    /**
     * 街道id
     */
    @ExcelProperty(value = "街道id")
    private Long regionId;

    /**
     * 所属地区id
     */
    @ExcelProperty(value = "街道名称")
    private String regionName;
    /**
     * 号码总量
     */
    @ExcelProperty(value = "号码总量")
    private Long resPhoneNum;

    /**
     * 平台实际入库的号码量
     */
    @ExcelProperty(value = "平台实际入库的号码量")
    private Long resSubmitNum;

    /**
     * 完成营销的量（指营销过程正常发起的量）
     */
    @ExcelProperty(value = "完成营销的量", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "指=营销过程正常发起的量")
    private Long retSuccNum;

    /**
     * 营销失败的量（指营销过程未能成功执行的数量）
     */
    @ExcelProperty(value = "营销失败的量", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "指=营销过程未能成功执行的数量")
    private Long retFailNum;

    /**
     * 营销过期用户数
     */
    @ExcelProperty(value = "营销过期用户数")
    private Long retOutDateNum;

    /**
     * 外呼平台批次id
     */
    @ExcelProperty(value = "外呼平台批次id")
    private Long batchId;

    /**
     * 是否发送短信
     */
    @ExcelProperty(value = "是否发送短信")
    private Integer sendMsgStatus;

    /**
     * 短信模板数
     */
    @ExcelProperty(value = "短信模板数")
    private Integer tempNum;


}
