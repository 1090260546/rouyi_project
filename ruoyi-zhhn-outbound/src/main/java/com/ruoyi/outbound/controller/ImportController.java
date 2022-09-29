package com.ruoyi.outbound.controller;

import com.ruoyi.outbound.domain.vo.ImportVo;
import com.ruoyi.outbound.service.ImportService;
import com.ruoyi.outbound.utils.CheckFileTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导入数据控制器
 */
@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ImportService importService;


    /**
     * 导入excel
     * @param importFile
     * @param regionId
     * @return
     * @throws Exception
     */
    @PostMapping("/importExcel")
    @ResponseBody
    public Map<String,Object> importExcel(@RequestPart("file") MultipartFile importFile,
                                          @RequestParam("regionId") Integer regionId) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        if(!CheckFileTypeUtil.getFileType(importFile).equals("xlsx")){
            map.put("code",401);
            map.put("msg","文件格式不对，请重新上传");
            return map;
        }

        Map<Integer, String> errorMap = new HashMap<>();
        List<ImportVo> errors = new ArrayList<>();
        try{
            if(importService.importExcel(importFile,errorMap,errors,regionId)){
                map.put("code",200);
                map.put("msg","导入成功!");
                return map;
            }
            map.put("code",402);
            map.put("msg","导入失败"+ StringUtils.join(errors,","));
            return map;
        }catch (RuntimeException ex){
            ex.printStackTrace();
            map.put("code",402);
            map.put("msg","导入失败"+ StringUtils.join(errors,","));
            return map;
        }
    }
}
