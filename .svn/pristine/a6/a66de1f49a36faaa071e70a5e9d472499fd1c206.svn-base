package com.ruoyi.outbound.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import static org.apache.poi.ss.usermodel.CellType.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ImportExcel {


    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    SimpleDateFormat createDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file=File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public Workbook getWorkbook(File file) {
        Workbook book = null;
        FileInputStream in = null;
        try {

         in = new FileInputStream(file);
         book = new XSSFWorkbook(in);
         return book;
        } catch (IOException ex) {
            try{
                book = new HSSFWorkbook(in);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return book;
    }


    public <T> List<T> readExcel(Class<T> class1, Sheet sheet, int sheetIndex,Map<Integer, String> errorMap,List<T> errors)
            throws Exception {
//        Sheet sheet = workbook.getSheetAt(sheetIndex);
        int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
        if (rowCount == 1) { // 表格最小长度应为2
            return null;
        }

        List<T> list = new ArrayList<T>(rowCount - 1);
        T obj;
        // 遍历每一行
        for (int r = 2; r < rowCount; r++) {
            Row row = sheet.getRow(r);
            if(row == null){
                continue;
            }
            obj = class1.newInstance();
            Field[] fields = class1.getDeclaredFields();
            Field field;
            boolean errorFlag = false;  //标识是否有错误点
            boolean flag = true;     //标识,确定该条数据是否通过第一轮判断
            StringBuffer error = new StringBuffer();
            for (int j = 0; j < fields.length; j++) {
                field = fields[j];
                ExcelAnnotation excelFiled = field.getAnnotation(ExcelAnnotation.class);
                if(excelFiled == null){
                    continue;
                }
                Cell cell = row.getCell(excelFiled.colIndex());
                try {
                    //序号为0,编号为空,则为无效行
                    if(excelFiled.colIndex() == 0 ){
                        if(cell == null || cell.toString().length() == 0){
                            flag  = false;
                            break;
                        }
                    }

                    if(!excelFiled.skip()){   //必填字段,需要判断是否非空
                        if (cell == null || cell.toString().length() == 0) {
                            //errogMsg(errorMap, r, j, excelFiled,"不能为空");
                            error.append(excelFiled.colName()+"不能为空"+"|");
                            flag = false;
                            continue;
                        }
                    }

                    if (field.getType().equals(Date.class)) {
                        if (STRING == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), dateFormat.parse(cell.getStringCellValue()));
                        } else {
                            ReflectUtil.setValue(obj, field.getName(), new Date(cell.getDateCellValue().getTime()));
                        }
                    } else if (field.getType().equals(Integer.class)) {
                        if (NUMERIC == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), (int) cell.getNumericCellValue());
                        } else if (STRING == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), Integer.parseInt(cell.getStringCellValue()));
                        }
                    } else if (field.getType().equals(BigDecimal.class)) {
                        if (NUMERIC == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), new BigDecimal(cell.getNumericCellValue()));
                        } else if (STRING == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), cell.getStringCellValue());
                        }
                    }else if (field.getType().equals(Double.class)) {
                        if (NUMERIC == cell.getCellType()) {
                            if(excelFiled.precision() == 0){   //没有小数点
                                ReflectUtil.setValue(obj, field.getName(),Double.valueOf(new BigDecimal(cell.getNumericCellValue()).intValue()));
                            }else{
                                ReflectUtil.setValue(obj, field.getName(),new BigDecimal(cell.getNumericCellValue()).doubleValue());
                            }
                        } else if (STRING == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), new Double(cell.getStringCellValue()));
                        }
                    } else if (field.getType().equals(String.class)) {
                        if (NUMERIC == cell.getCellType()) {
                            if(excelFiled.precision() == 0){   //没有小数点
                                ReflectUtil.setValue(obj, field.getName(),new BigDecimal(cell.getNumericCellValue()).intValue()+"");
                            }else{
                                ReflectUtil.setValue(obj, field.getName(),new BigDecimal(cell.getNumericCellValue()).doubleValue()+"");
                            }
                        } else if (STRING == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), cell.getStringCellValue());
                        }
                    }  else {
                        if (NUMERIC == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), new BigDecimal(cell.getNumericCellValue()));
                        } else if (STRING == cell.getCellType()) {
                            ReflectUtil.setValue(obj, field.getName(), cell.getStringCellValue());
                        }
                    }
                } catch (Exception e) {
                    flag = false;
                    errorFlag = true;
                    error.append(excelFiled.colName()+"类型格式有误"+"|");
                }
            }
            if(flag){
                list.add(obj);
            }

            if(errorFlag){
                errorMap.put(r, error.toString());
                errors.add(obj);
            }

        }
        return list;
    }


    public static void main(String[] args) {
    }
}
