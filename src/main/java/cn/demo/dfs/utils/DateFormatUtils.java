package cn.demo.dfs.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    public static String dateTimeFormat = "yyy-MM-dd HH:mm:ss";
    public static String getDateStr(Date date){
        return sdf.format(date);
    }
}
