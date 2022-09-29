package com.ruoyi.outbound.service;

import com.ruoyi.outbound.domain.vo.SendMessageVo;
import com.ruoyi.outbound.domain.bo.SendMessageBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.outbound.entity.SendMessageLog;

import java.util.Collection;
import java.util.List;

/**
 * 短信发送记录Service接口
 *
 * @author ruoyi
 * @date 2022-09-05
 */
public interface ISendMessageService {

    /**
     * 查询短信发送记录
     */
    SendMessageVo queryById(Long id);

    /**
     * 查询短信发送记录列表
     */
    TableDataInfo<SendMessageVo> queryPageList(SendMessageBo bo, PageQuery pageQuery);

    /**
     * 查询短信发送记录列表
     */
    List<SendMessageVo> queryList(SendMessageBo bo);

    /**
     * 新增短信发送记录
     */
    Boolean insertByBo(SendMessageBo bo);

    /**
     * 修改短信发送记录
     */
    Boolean updateByBo(SendMessageBo bo);

    /**
     * 校验并批量删除短信发送记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Boolean saveBatch(List<SendMessageLog> list,SendMessageBo bo);
}
