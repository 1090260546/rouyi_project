package com.ruoyi.outbound.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.outbound.domain.MsgTemplate;
import com.ruoyi.outbound.domain.bo.MsgTemplateBo;
import com.ruoyi.outbound.domain.vo.MsgTemplateVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.outbound.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 短信发送模板Mapper接口
 *
 * @author ruoyi
 * @date 2022-08-31
 */
public interface MsgTemplateMapper extends BaseMapperPlus<MsgTemplateMapper, MsgTemplate, MsgTemplateVo> {

    List<Item> selectTaskInfoList(@Param("regionId") int regionId);

    Page<MsgTemplateVo> selectTempListPage(@Param("page") Page<MsgTemplateBo> page, @Param("msgTemplateBo") MsgTemplateBo msgTemplateBo);



    List<MsgTemplate> getMsgTemplateList(@Param("regionId") Integer regionId,@Param("taskId") Long taskId,@Param("label") String label);
}
