package com.ruoyi.outbound.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.outbound.domain.vo.SendMessageLogVo;
import com.ruoyi.outbound.entity.SendMessageLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 短信发送记录Mapper接口
 *
 * @author ruoyi
 * @date 2022-08-26
 */
@Mapper
public interface SendMessageLogMapper extends BaseMapper<SendMessageLog>
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
    public Page<SendMessageLog> selectSendMessageLogList(Page<SendMessageLog> page,@Param("sendMessageLog") SendMessageLogVo sendMessageLog);


    /**
     * 新增短信发送记录
     *
     * @param sendMessageLog 短信发送记录
     * @return 结果
     */
    public int insertSendMessageLog(@Param("sendMessageLog") SendMessageLog sendMessageLog);

    /**
     * 修改短信发送记录
     *
     * @param sendMessageLog 短信发送记录
     * @return 结果
     */
    public int updateSendMessageLog(@Param("sendMessageLog") SendMessageLog sendMessageLog);

    /**
     * 删除短信发送记录
     *
     * @param id 短信发送记录ID
     * @return 结果
     */
    public int deleteSendMessageLogById(@Param("id") Long id);

    /**
     * 批量删除短信发送记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSendMessageLogByIds(@Param("ids") String[] ids);
}
