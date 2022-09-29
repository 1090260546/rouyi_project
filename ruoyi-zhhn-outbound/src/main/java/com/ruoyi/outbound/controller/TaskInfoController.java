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
import com.ruoyi.outbound.domain.vo.TaskInfoVo;
import com.ruoyi.outbound.domain.bo.TaskInfoBo;
import com.ruoyi.outbound.service.ITaskInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务列表和短信列表
 * @author ruoyi
 * @date 2022-09-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/outbound/task")
public class TaskInfoController extends BaseController {

    private final ITaskInfoService iTaskInfoService;

    /**
     * 查询任务分页列表
     */
    @GetMapping("/list")
    public TableDataInfo<TaskInfoVo> list(TaskInfoBo bo, PageQuery pageQuery) {
        return iTaskInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询街道id查询任务列表
     */
    @GetMapping("/taskList")
    public R<List<TaskInfoVo>> taskList(TaskInfoBo bo) {
        List<TaskInfoVo> list = iTaskInfoService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出任务列列表
     */
    @Log(title = "任务列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TaskInfoBo bo, HttpServletResponse response) {
        List<TaskInfoVo> list = iTaskInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "任务列", TaskInfoVo.class, response);
    }

    /**
     * 获取任务列详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<TaskInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iTaskInfoService.queryById(id));
    }

    /**
     * 新增任务列
     */
    @Log(title = "任务列表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TaskInfoBo bo) {
        return toAjax(iTaskInfoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改任务列
     */
    @Log(title = "任务列表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TaskInfoBo bo) {
        return toAjax(iTaskInfoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除任务列
     *
     * @param ids 主键串
     */
    @Log(title = "任务列表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iTaskInfoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
