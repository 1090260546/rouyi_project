package com.ruoyi.outbound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.outbound.common.ImportUtils;
import com.ruoyi.outbound.entity.RegionInfo;
import com.ruoyi.outbound.entity.SendMessageLog;
import com.ruoyi.outbound.service.ISendMessageLogService;
import com.ruoyi.outbound.service.MessageService;
import com.ruoyi.outbound.service.RegionInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.outbound.domain.bo.SendMessageBo;
import com.ruoyi.outbound.domain.vo.SendMessageVo;
import com.ruoyi.outbound.mapper.SendMessageMapper;
import com.ruoyi.outbound.service.ISendMessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 短信发送记录Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-05
 */
@RequiredArgsConstructor
@Service
public class SendMessageServiceImpl implements ISendMessageService {

    private final SendMessageMapper baseMapper;
    private final RegionInfoService regionInfoService;
    private final MessageService messageService;

    private  final ISendMessageLogService sendMessageLogService;

    private final ImportUtils importUtils;
    /**
     * 查询短信发送记录
     */
    @Override
    public SendMessageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询短信发送记录列表
     */
    @Override
    public TableDataInfo<SendMessageVo> queryPageList(SendMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SendMessageLog> lqw = buildQueryWrapper(bo);
        Page<SendMessageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询短信发送记录列表
     */
    @Override
    public List<SendMessageVo> queryList(SendMessageBo bo) {
        LambdaQueryWrapper<SendMessageLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SendMessageLog> buildQueryWrapper(SendMessageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SendMessageLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTaskId() != null, SendMessageLog::getTaskId, bo.getTaskId());
        lqw.eq(StringUtils.isNotBlank(bo.getSendMobile()), SendMessageLog::getSendMobile, bo.getSendMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getBatchId()), SendMessageLog::getBatchId, bo.getBatchId());
        lqw.orderByDesc(SendMessageLog::getCreateTime);
        return lqw;
    }

    /**
     * 新增短信发送记录
     */
    @Override
    public Boolean insertByBo(SendMessageBo bo) {
        SendMessageLog add = BeanUtil.toBean(bo, SendMessageLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改短信发送记录
     */
    @Override
    public Boolean updateByBo(SendMessageBo bo) {
        SendMessageLog update = BeanUtil.toBean(bo, SendMessageLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SendMessageLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除短信发送记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean saveBatch(List<SendMessageLog> list,SendMessageBo bo) {
        String batchId = DateUtils.dateTimeNow();
        int rowCount = 5;  //一次发送条数
        int round = 0;
        if(list.size() <= rowCount){
            round = 0;
        }else{
            round = (list.size() - 1) / rowCount;
        }
        RegionInfo regionInfo = regionInfoService.getById(bo.getRegionId());
        for(int i = 0; i <= round; i++){
            int fromIndex = i * rowCount;
            int toIndex = (i + 1) * rowCount;
            if (i == round){
                toIndex = list.size();
            }
            List<SendMessageLog> subList = list.subList(fromIndex, toIndex);
            String phone = subList.stream().map(SendMessageLog::getSendMobile).collect(Collectors.joining(","));
            String result = messageService.sendMessage(phone, bo.getSendContent(), regionInfo.getMsgId());//发送短信
            List<SendMessageLog> sendMessageLogList = new ArrayList<>();
            for(SendMessageLog obj : subList){
                SendMessageLog messageLog =  importUtils.convertTOSendMessageLog(0, null, obj.getSendMobile(), bo.getSendContent(), result);
                messageLog.setTaskName(bo.getTaskName());
                messageLog.setBatchId(batchId);
                sendMessageLogList.add(messageLog);
            }
            sendMessageLogService.batchInsertSendMassageLog(sendMessageLogList);//保存发送短信日志
        }
        return true;
    }
}
