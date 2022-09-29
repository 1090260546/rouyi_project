package com.ruoyi.outbound.controller;

import java.util.List;
import java.util.Arrays;

import com.ruoyi.outbound.entity.Item;
import com.ruoyi.outbound.service.BatchInfoService;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.outbound.domain.vo.MsgTemplateVo;
import com.ruoyi.outbound.domain.bo.MsgTemplateBo;
import com.ruoyi.outbound.service.IMsgTemplateService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 短信发送模板
 *
 * @author ruoyi
 * @date 2022-08-31
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/outbound/messageTemplate")
public class MsgTemplateController extends BaseController {

    private final IMsgTemplateService iMsgTemplateService;
    private final BatchInfoService batchInfoService;

    /**
     * 查询短信发送模板列表
     */
    @GetMapping("/list")
    public TableDataInfo<MsgTemplateVo> list(MsgTemplateBo bo, PageQuery pageQuery) {
        return iMsgTemplateService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出短信发送模板列表
     */
    @Log(title = "短信发送模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MsgTemplateBo bo, HttpServletResponse response) {
        List<MsgTemplateVo> list = iMsgTemplateService.queryList(bo);
        ExcelUtil.exportExcel(list, "短信发送模板", MsgTemplateVo.class, response);
    }

    /**
     * 获取短信发送模板详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<MsgTemplateVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iMsgTemplateService.queryById(id));
    }

    /**
     * 新增短信发送模板
     */
    @Log(title = "短信发送模板", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MsgTemplateBo bo) {
        return toAjax(iMsgTemplateService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * /a`+
     * .
    @Log(title = "短信发送模板", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MsgTemplateBo bo) {
        return toAjax(iMsgTemplateService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除短信发送模板
     *
     * @param ids 主键串
     */
    @Log(title = "短信发送模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iMsgTemplateService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    /**
     * 根据街道加载任务数据
     * @param regionId
     */
    @GetMapping("/taskList")
    public R<List<Item>> taskList(@RequestParam int regionId) {
        List<Item> list = iMsgTemplateService.selectTaskInfoList(regionId);
        return R.ok(list);
    }

    /**
     * 根据任务id查询label项
     * @param taskId
     */
    @GetMapping("/getTaskLabel")
    public R<List<Item>> selectTaskLabel(@RequestParam int taskId) {
        List<Item> list = iMsgTemplateService.selectTaskLabel(taskId);
        return R.ok(list);
    }
}
