package com.ruoyi.outbound.service;

import com.ruoyi.outbound.domain.vo.ImportVo;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface ImportService {

    boolean importExcel(MultipartFile importFile, Map<Integer, String> errorMap, List<ImportVo> errors, Integer regionId) throws Exception;


    long saveTask(Sheet sheet,Integer regionId) throws ParseException;

}
