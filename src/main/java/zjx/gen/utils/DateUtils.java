package zjx.gen.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    //修改日期控件格式和数据库日期不匹配问题
    public static String formatDate(String date) {
        if (date != null && date.trim() != "") {
            String[] str = date.split("-");
            if (Integer.parseInt(str[1]) < 10) {
                str[1] = "0" + str[1];
            }
            if (Integer.parseInt(str[2]) < 10) {
                str[2] = "0" + str[2];
            }
            return str[0] + "-" + str[1] + "-" + str[2];
        }
        return null;
    }

    //获取当前日期
    public static Date getTodayDate() {
        Date d = new Date();
        return d;
    }

    //获取当前日期之前的一周时间
    public static Date getLastWeekDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        return d;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将当前日期修改为这一天的开始 例如Tue Jun 17 00:00:00 CST 2014
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date String2Date(String str) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date resultDate = format.parse(str);
        return resultDate;
    }

    /**
     * 将当前日期修改为这一天的最后一秒 例如Tue Jun 17 23:59:59 CST 2014
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String str) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(str);
        Integer dayMis = 1000 * 60 * 60 * 24;//一天的毫秒-1
        long curMillisecond = date.getTime();
        long resultMis = curMillisecond + (dayMis - 1); //当天最后一秒
        Date resultDate = new Date(resultMis);
        return resultDate;
    }

    /**
     * 计算时间相隔天数，
     *
     * @param sDate 开始时间：yyyy-MM-dd
     * @param eDate 结束时间：yyyy-MM-dd
     * @return
     */
    public static int calDaysBetweenDate(String sDate, String eDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            long sTime = sdf.parse(sDate).getTime();
            long eTime = sdf.parse(eDate).getTime();
            return (int) ((eTime - sTime) / (3600 * 24 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算几天前或几天后，
     *
     * @param date
     * @param dateCount 负值为几天前；正值为几天后
     * @return
     */
    public static Date getNewDate(Date date, int dateCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + dateCount);
        return calendar.getTime();
    }


    /**
     * 	获取 最后一秒 的时间
     * @param date
     * @return 返回date当日的23时59分59秒
     */
    public static Date getDateEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23 );
        calendar.set(Calendar.MINUTE, 59 );
        calendar.set(Calendar.SECOND, 59 );
        return calendar.getTime();
    }
    
    /**
     * 获取当天 0点
     * @param date
     * @return
     */
    public static Date getDateStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0 );
        calendar.set(Calendar.MINUTE, 0 );
        calendar.set(Calendar.SECOND, 0 );
        return calendar.getTime();
    }
}
