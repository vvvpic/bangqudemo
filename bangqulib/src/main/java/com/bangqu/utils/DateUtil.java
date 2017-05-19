package com.bangqu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by 唯图 on 2016/8/19.
 */
public class DateUtil {
    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;

    /**
     * 获取系统当前时间
     */
    public static String getNowDateforString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取系统当前时间
     */
    public static String getNowDateforString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        String str = formatter.format(date);
        return str;
    }

    /**
     * 自定义格式
     */
    public static String getTimeDateString(Date date, String string) {
        SimpleDateFormat formatter = new SimpleDateFormat(string);
        String str = formatter.format(date);
        return str;
    }


    /**
     * 获取系统当前时间
     */
    public static Date getNowDateforDate() {
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return curDate;
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dfs.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间差 hms
     *
     * @return
     */
    public static String getHms(Date startDate, Date endDate) {
        String str = "";
        long diff = (endDate.getTime() - startDate.getTime()) / 1000;//这样得到的差值是微秒级别

        long hous = diff / (60 * 60);
        long minutes = (diff - hous * (60 * 60)) / 60;
        long s = diff - hous * (60 * 60) - minutes * 60;
        if (minutes < 10) {
            str = hous + ":0" + minutes + ":" + s;
        } else {
            if (s < 10) {
                str = hous + ":" + minutes + ":0" + s;
            } else {
                str = hous + ":" + minutes + ":" + s;
            }
        }
        return str;
    }

    /**
     * 时间比较
     *
     * @return
     */
    public static int getStatus(String startTime, String middleTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date begin = dfs.parse(startTime);
            java.util.Date middle = dfs.parse(middleTime);
            java.util.Date end = dfs.parse(endTime);

            if (end.getTime() - middle.getTime() < 0) {
                return -1;
            } else if (middle.getTime() - begin.getTime() >= 0 && end.getTime() - middle.getTime() >= 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间比较
     *
     * @return
     */
    public static boolean getJudge(String startTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            java.util.Date begin = dfs.parse(startTime);
            java.util.Date end = dfs.parse(endTime);

            if (end.getTime() - begin.getTime() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static SimpleDateFormat formatterdate = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 求时间天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDateCha(String startTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date begin = dfs.parse(startTime);
            java.util.Date end = dfs.parse(endTime);
            long diff = end.getTime() - begin.getTime();//这样得到的差值是微秒级别
            return (int) (diff / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前周几
     *
     * @return
     */
    public static int getWeek() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    //当前年月日
    public static String getDateForYMD() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    //转换月日
    public static String getDateForYMD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //当前月日
    public static String getNewDateForMD() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    //某天周几
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "星期天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "星期一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "星期二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "星期三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "星期四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "星期五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "星期六";
        }
        return Week;
    }

    //之前或之后几天的日期
    public static Date getNextDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        return date;
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

}
