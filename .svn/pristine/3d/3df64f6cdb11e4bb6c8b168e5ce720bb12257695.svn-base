package com.ruoyi.outbound.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.excel.ExcelResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.outbound.domain.bo.MsgImportTemplate;
import com.ruoyi.outbound.domain.bo.SendMessageBo;
import com.ruoyi.outbound.domain.vo.SendMessageVo;
import com.ruoyi.outbound.entity.SendMessageLog;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import org.springframework.http.MediaType;
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
import com.ruoyi.outbound.service.ISendMessageService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 短信发送记录
 *
 * @author ruoyi
 * @date 2022-09-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/outbound/sendMessage")
public class SendMessageController extends BaseController {

    private final ISendMessageService iSendMessageService;


    /**
     * 查询短信发送记录列表
     */
    @GetMapping("/list")
    public TableDataInfo<SendMessageVo> list(SendMessageBo bo, PageQuery pageQuery) {
        return iSendMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出短信发送记录列表
     */
    @Log(title = "短信发送记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SendMessageBo bo, HttpServletResponse response) {
        List<SendMessageVo> list = iSendMessageService.queryList(bo);
        ExcelUtil.exportExcel(list, "短信发送记录", SendMessageVo.class, response);
    }

    /**
     * 获取短信发送记录详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<SendMessageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iSendMessageService.queryById(id));
    }

    /**
     * 新增短信发送记录
     */
    @Log(title = "短信发送记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@RequestPart("file") MultipartFile file,
                       @Validated(AddGroup.class) @RequestBody SendMessageBo bo) {
        if (ObjectUtil.isNull(file)) {
            throw new ServiceException("上传文件不能为空");
        }
        return toAjax(iSendMessageService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 短信手动发送
     */
    @Log(title = "短信手动发送", businessType = BusinessType.IMPORT)
    @RepeatSubmit()
    @PostMapping(value = "/importData",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Void> importData(@RequestParam(value="file")MultipartFile file,SendMessageBo bo) throws Exception {
        if (ObjectUtil.isNull(file)) {
            throw new ServiceException("上传文件不能为空");
        }
        ExcelResult<SendMessageBo> excelResult = ExcelUtil.importExcel(file.getInputStream(), SendMessageBo.class, true);
        List<SendMessageBo> volist = excelResult.getList();
        List<SendMessageLog> list = BeanUtil.copyToList(volist, SendMessageLog.class);
        if(list.size()>0){
            iSendMessageService.saveBatch(list,bo);
        }
        return R.ok(excelResult.getAnalysis());
    }

    /**
     * 获取导入模板
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "用户数据", MsgImportTemplate.class, response);
    }

    /**
     * 修改短信发送记录
     */
    @Log(title = "短信发送记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SendMessageBo bo) {
        return toAjax(iSendMessageService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除短信发送记录
     *
     * @param ids 主键串
     */
    @Log(title = "短信发送记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iSendMessageService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
