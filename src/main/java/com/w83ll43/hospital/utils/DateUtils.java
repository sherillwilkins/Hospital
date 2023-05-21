package com.w83ll43.hospital.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils {

    // 默认的年月日的格式. yyyy-MM-dd
    public static final String PATTEN_DEFAULT_YMD = "yyyy-MM-dd";

    public static Date getCurrentDate() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    /***
     * 判断两个时间段是否有交集
     * @param startDateOne 第一个时间段的开始时间
     * @param endDateOne 第一个时间段的结束时间
     * @param startDateTwo 第二个时间段的开始时间
     * @param endDateTwo 第二个时间段的结束时间
     * @return
     */
    public static Boolean IsInterSection(Date startDateOne,Date endDateOne,Date startDateTwo,Date endDateTwo)
    {
        Date maxStartDate = startDateOne;
        if(maxStartDate.before(startDateTwo))
        {
            maxStartDate = startDateTwo;
        }

        Date minEndDate = endDateOne;
        if(endDateTwo.before(minEndDate))
        {
            minEndDate = endDateTwo;
        }
        if(maxStartDate.before(minEndDate) || (maxStartDate.getTime() == minEndDate.getTime()))
        {
            return true;
        }
        else {
            return  false;
        }
    }

    /**
     * 判断日期是不是今天
     * @param date
     * @return    是返回true，不是返回false
     */
    public static boolean isToday(Date date) {
        // 当前时间
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTEN_DEFAULT_YMD);
        // 获取今天的日期
        String nowDay = simpleDateFormat.format(now);
        // 对比的时间
        String day = simpleDateFormat.format(date);

        return day.equals(nowDay);
    }
}
