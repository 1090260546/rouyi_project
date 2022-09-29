package com.ruoyi.outbound.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.outbound.domain.vo.ExportLabelsVo;
import com.ruoyi.outbound.domain.bo.ExportLabelsBo;
import com.ruoyi.outbound.service.IExportLabelsService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 机器人标签配置
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/outbound/labels")
public class ExportLabelsController extends BaseController {

    private final IExportLabelsService iExportLabelsService;

    /**
     * 查询机器人标签配置列表
     */
    @GetMapping("/list")
    public TableDataInfo<ExportLabelsVo> list(ExportLabelsBo bo, PageQuery pageQuery) {
        return iExportLabelsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出机器人标签配置列表
     */
    @Log(title = "机器人标签配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ExportLabelsBo bo, HttpServletResponse response) {
        List<ExportLabelsVo> list = iExportLabelsService.queryList(bo);
        ExcelUtil.exportExcel(list, "机器人标签配置", ExportLabelsVo.class, response);
    }

    /**
     * 获取机器人标签配置详细信息
     * @param id 主键
     */
    @GetMapping("/getExportLabels/{id}")
    public R<ExportLabelsVo> getInfo(@NotNull(message = "id不能为空")
                                     @PathVariable Long id) {
        return R.ok(iExportLabelsService.queryById(id));
    }

    /**
     * 新增机器人标签配置
     */
    @Log(title = "机器人标签配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ExportLabelsBo bo) {
        return toAjax(iExportLabelsService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改机器人标签配置
     */
    @Log(title = "机器人标签配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ExportLabelsBo bo) {
        return toAjax(iExportLabelsService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除机器人标签配置
     * @param ids 主键串
     */
    @Log(title = "机器人标签配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iExportLabelsService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
