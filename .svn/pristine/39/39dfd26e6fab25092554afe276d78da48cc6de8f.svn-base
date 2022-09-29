package com.ruoyi.outbound.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标题注解类
 * @author tangyb
 * @date 2020/03/27
 */
@Target(value = {ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public  @interface ExcelAnnotation {

     /**
      * 列头名称
      * @return
      */
     String colName() default "";

     /**
      * 数据精度，精确到后几位数
      * 默认0位
      * @return
      */
     int precision() default 0;

     /**
      * 日期格式
      * 默认 “yyyy-MM-dd HH:mm:ss”
      * @return
      */
     String dateFormat() default "";

     /**
      * 字段对应的列索引
      * @return
      */
     int colIndex() default 0;
     /**
      * 忽略该字段  为true表示为选填字段，为false为必填字段,必须判断是否为非空
      * @return
      */
     boolean skip() default false;
}
