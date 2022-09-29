package com.ruoyi.outbound.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.outbound.domain.vo.SendMessageLogVo;
import com.ruoyi.outbound.entity.SendMessageLog;

import java.util.List;

/**
 * 短信发送记录Service接口
 *
 * @author ruoyi
 * @date 2022-08-26
 */
public interface ISendMessageLogService
{
    /**
     * 查询短信发送记录
     *
     * @param id 短信发送记录ID
     * @return 短信发送记录
     */
    public SendMessageLog selectSendMessageLogById(Long id);

    /**
     * 查询短信发送记录列表
     *
     * @param sendMessageLog 短信发送记录
     * @return 短信发送记录集合
     */
    public Page<SendMessageLog> selectSendMessageLogList(SendMessageLogVo sendMessageLog, PageQuery pageQuery);

    /**
     * 新增短信发送记录
     *
     * @param sendMessageLog 短信发送记录
     * @return 结果
     */
    public int insertSendMessageLog(SendMessageLog sendMessageLog);

    /**
     * 修改短信发送记录
     *
     * @param sendMessageLog 短信发送记录
     * @return 结果
     */
    public int updateSendMessageLog(SendMessageLog sendMessageLog);

    /**
     * 批量删除短信发送记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSendMessageLogByIds(String ids);

    /**
     * 删除短信发送记录信息
     *
     * @param id 短信发送记录ID
     * @return 结果
     */
    public int deleteSendMessageLogById(Long id);


    void batchInsertSendMassageLog(List<SendMessageLog> sendMessageLogList);
}
