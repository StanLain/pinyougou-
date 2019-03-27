package cn.itcast.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    //时间转成字符串
    public static String getStringDate(Date date,String pattn){
        SimpleDateFormat sdf = new SimpleDateFormat(pattn);
        String format = sdf.format(date);
        return format;
    }
    //字符串转时间
    public static Date getDateByString(String str,String pattn)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(pattn);
        Date date2 = sdf.parse(str);
        return date2;
    }
}
