package com.time;

import com.entity.Area;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yangqing
 * @since 2020/5/20  17:03
 **/
public class LocalDateTimeTest {

    /**
     * 当前时间
     */
    @Test
    public void test() {
        //获取当前日期 yyyy-MM-dd
        LocalDate localDate = LocalDate.now();
        System.out.println("当前日期：" + localDate);

        //获取当前时间 HH:mm:ss
        LocalTime localTime = LocalTime.now();
        System.out.println("当前时间：" + localTime);

        //获取当前日期时间 yyyy-MM-dd HH:mm:ss
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前日期时间：" + localDateTime);

        //指定时间
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 5, 20, 17, 9, 15);
        System.out.println("指定的时间：" + localDateTime2);

        //增加时间的年月日时分秒
        LocalDate localDate2 = localDate.plusYears(1);
        System.out.println("增加1年后的时间：" + localDate2);

        //减少时间的年月日时分秒
        LocalDate localDate3 = localDate.minusMonths(5);
        System.out.println("减少5个月后的时间：" + localDate3);

        //获取年、月、日、时、分、秒
        System.out.println("年：" + localDateTime.getYear());//年
        System.out.println("月：" + localDateTime.getMonth().getValue());//月
        System.out.println("日：" + localDateTime.getDayOfMonth());//日
        System.out.println("时：" + localDateTime.getHour());//时
        System.out.println("分：" + localDateTime.getMinute());//分
        System.out.println("秒：" + localDateTime.getSecond());//秒
    }

    /**
     * 时间戳
     */
    @Test
    public void test1(){
        Instant instant = Instant.now();
        System.out.println(instant);
        Long startTs = System.currentTimeMillis();
        System.out.println(startTs);
    }
    @Resource
    private Area area;
    @Test
    public void test5(){
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        List<Map<String, String>> map = person.getMap();
        System.out.println(area);
    }

    @Test
    public void test2(){
        String str="{\"taskcard\":{\"description\":\"测试不可描述\",\"task_id\":\"1265911632929361920\",\"title\":\"测试标题\",\"btn\":[{\"color\":\"blue\",\"name\":\"批准\",\"key\":\"yes\",\"is_bold\":true,\"replace_name\":\"已批准\"},{\"color\":\"red\",\"name\":\"驳回\",\"key\":\"no\",\"is_bold\":false,\"replace_name\":\"已驳回\"}],\"url\":\"https://www.baidu.com\"},\"agentid\":1000090,\"touser\":\"Bo.Zhang\",\"enable_duplicate_check\":0,\"duplicate_check_interval\":10000,\"accessToken\":\"7jWfNpZDllxQhejXzcFzZO9ilaUUDZI45Lfx3ftAhlCfTAX-nziPEEWAeqnblPEbEyF2Ox2tCaW62N-fWFmLlLCK__XdP2U_P9G1como3HDSyKxgzRTjPx1GSniEgIe0r-ebdOkAFh-b45X3jZ5OJPFMRhhH2WJR5Fb6hbCjI7ahOfpawt1bIytkLBp-qj7zB3X-zDDsMqu7idPfNlgvQg\",\"msgtype\":\"taskcard\",\"enable_id_trans\":0}";
        JSONObject object = JSON.parseObject(str);
        System.out.println("删除之前"+object);
        Object c = object.remove("accessToken");
        System.out.println("删除之后"+object);
    }

    @Test
    public void test3() throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse("2020-12-04 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        System.out.println(timestamp/1000);
    }


    @Test
    public  void test4() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        int hour = now.getHour();

        String hourStr = year +"-"+ monthValue +"-"+ dayOfMonth + " "+(hour-1)+":00:00";
        System.out.println(hourStr);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse(hourStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        System.out.println(timestamp/1000);
    }


    @Test
    public void test6(){
        LocalDate date = LocalDate.parse("2017-08-15");
        //获取这个月的第一个周末的时间
        System.out.println(date.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.SUNDAY)));//2017-08-06
        //获取上个月的最后一周
        System.out.println(date.with(TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.SUNDAY)));//2017-07-30
        //获取这个月的倒数第一个周末的时间
        System.out.println(date.with(TemporalAdjusters.dayOfWeekInMonth(-1, DayOfWeek.SUNDAY)));//2017-08-27

        //获取这个月的第一个周末的时间,上面的dayOfWeekInMonth更灵活,可以定义第几周
        System.out.println(date.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)));//2017-08-06

        //其底层调用的是
//		  return TemporalAdjusters.dayOfWeekInMonth(1, dayOfWeek);

        //明年的第一天
        System.out.println(date.with(TemporalAdjusters.firstDayOfNextYear()));//2018-01-01
        //获取下周5的时间
        System.out.println(date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));//2017-08-18
        System.out.println(date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));//2017-08-22,始终返回下周的某个时间
        //获取周2时间
        System.out.println(date.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)));//2017-08-15,如果当前时间刚好是星期三,那么就返回当前时间

        // 获取本月最后一天
        System.out.println(date.with(TemporalAdjusters.lastDayOfMonth()));//2017-08-31
    }

    @Test
    public void test7(){
        LocalDate startTime = LocalDate.of(2020,11,25);
        LocalDate endTime = LocalDate.of(2020,11,29);
        Duration duration = Duration.between(startTime, endTime);
        long l = duration.toDays();
        System.out.println(l);
    }


}
