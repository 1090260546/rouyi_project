package com.ruoyi.outbound.controller;


import com.ruoyi.common.core.domain.R;
import com.ruoyi.outbound.domain.vo.RegionInfoVo;
import com.ruoyi.outbound.service.RegionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地区控制器
 */
@RestController
@RequestMapping("/region")
public class RegionInfoController {

    @Autowired
    RegionInfoService regionInfoService;

    /**
     * 查询地区列表
     */
    @GetMapping("/getList")
    @ResponseBody
    public R<List<RegionInfoVo>> getList() {
        return R.ok(regionInfoService.getList());
    }




}
