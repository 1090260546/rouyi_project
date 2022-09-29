package com.ruoyi.outbound.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.outbound.domain.MsgTemplate;
import com.ruoyi.outbound.domain.bo.MsgTemplateBo;
import com.ruoyi.outbound.domain.vo.MsgTemplateVo;
import com.ruoyi.outbound.entity.Item;
import com.ruoyi.outbound.entity.Task;

import java.util.Collection;
import java.util.List;

/**
 * 短信发送模板Service接口
 *
 * @author ruoyi
 * @date 2022-08-31
 */
public interface IMsgTemplateService extends IService<MsgTemplate> {



    /**
     * 查询短信发送模板
     */
    MsgTemplateVo queryById(Long id);

    /**
     * 查询短信发送模板列表
     */
    TableDataInfo<MsgTemplateVo> queryPageList(MsgTemplateBo bo, PageQuery pageQuery);

    /**
     * 查询短信发送模板列表
     */
    List<MsgTemplateVo> queryList(MsgTemplateBo bo);

    /**
     * 新增短信发送模板
     */
    Boolean insertByBo(MsgTemplateBo bo);

    /**
     * 修改短信发送模板
     */
    Boolean updateByBo(MsgTemplateBo bo);

    /**
     * 校验并批量删除短信发送模板信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    List<Item> selectTaskInfoList(int regionId);

    List<Item> selectTaskLabel(int taskId);


    List<MsgTemplate> getMsgTemplateList(Integer regionId,Long tempTaskId,String label);
}
