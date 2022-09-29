package com.ruoyi.outbound.utils;
import java.lang.reflect.Field;


public class ReflectUtil {

    /**
     * 使用反射设置变量值
     *
     * @param target    被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可是常量！
     * @param value     值
     * @param <T>       value类型，泛型
     */
    public static <T> void setValue(Object target, String fieldName, T value) {
        try {
            Class c = target.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用反射获取变量值
     *
     * @param target    被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可以是常量
     * @param <T>       返回类型，泛型
     * @return 值
     */
    public static <T> T getValue(Object target, String fieldName) {
        T value = null;
        try {
            Class c = target.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            value = (T) f.get(target);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }
}

