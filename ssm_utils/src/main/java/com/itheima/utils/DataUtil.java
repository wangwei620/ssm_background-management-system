package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    /**
     * 接受日期返回字符串
     */
    public static String formateDatatoStr(Date date){
        try {
            //创建格式的日期化
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return   simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            //如果有异常则返回空值
            return "";
        }
    }
    /**
     * 接受字符返回日期
     */
    public static  Date parseStringtoDate(String dateStr){
        //创建格式化类
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            //如果出现异常则返回空值
            return null;
        }
    }
}
