package com.ruoyi.outbound.controller;

import java.util.List;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.outbound.domain.ObBusiness;
import com.ruoyi.outbound.entity.Item;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.outbound.domain.vo.ObBusinessVo;
import com.ruoyi.outbound.domain.bo.ObBusinessBo;
import com.ruoyi.outbound.service.IObBusinessService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 机器人标签管理
 *
 * @author ruoyi
 * @date 2022-09-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/outbound/export")
public class ObBusinessController extends BaseController {

    private final IObBusinessService iObBusinessService;

    /**
     * 查询机器人标签管理列表
     */
    @GetMapping("/list")
    public TableDataInfo<ObBusinessVo> list(ObBusinessBo bo, PageQuery pageQuery) {
        return iObBusinessService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出机器人标签管理列表
     */
    @Log(title = "机器人标签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ObBusinessBo bo, HttpServletResponse response) {
        List<ObBusinessVo> list = iObBusinessService.queryList(bo);
        ExcelUtil.exportExcel(list, "机器人标签管理", ObBusinessVo.class, response);
    }

    /**
     * 获取机器人标签管理详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ObBusinessVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iObBusinessService.queryById(id));
    }


    /**
     * 查询机器人label
     * @param bizNo
     */
    @GetMapping("/getRobotLabel")
    @ResponseBody
    public R<List<Item>> getRobotLabel(@RequestParam(value="bizNo") String bizNo){
        return R.ok(iObBusinessService.selectRobotLabel(Long.parseLong(bizNo)));
    }

    /**
     * 新增导出模板
     */
    @Log(title = "导出模板", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
   public R<Void> add(@RequestBody ObBusiness bo) {
        return toAjax(1);
    }



}
