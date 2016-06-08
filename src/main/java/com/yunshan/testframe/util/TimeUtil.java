package com.yunshan.testframe.util;
 
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * update ,getTimeYh
 *2013-04-26
 *update ，getEndDateForNextDay() 
 *2013-08-16
 * @author c_abc
 *
 */
public class TimeUtil{
    private static final  int MSEC = 1000;

    public static final String FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String TOMINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String DATE_PATTERN1 = "yyyy年MM月dd日";
    
    public static final String TOMINUTE_PATTERN1 = "yyyy年MM月dd日  HH:mm";
    
    public static final String FULL_PATTERN_SLASH = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_PATTERN_SLASH = "yyyy/MM/dd";

    public static final String FULL_SHORT_PATTERN = "yyyyMMddHHmmss";

    public static final String DATE_SHORT_PATTERN = "yyyyMMdd";

    // 一天的毫秒数 60*60*1000*24
    public final static long DAY_MILLIS = 86400000;

    // 一小时的毫秒数 60*60*1000
    public final static long HOUR_MILLIS = 3600000;

    // 一分钟的毫秒数 60*1000
    public final static long MINUTE_MILLIS = 60000;
    // 一秒的毫秒数 1000
    public final static long SECOND_MILLIS = 1000;

    /*int 类型秒数转成 string 日期*/
    public static String timet2String(int timet, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String s = sdf.format(new Date(((long) timet) * MSEC));
        return (s);
    }
    /*int 类型秒数转成 string yyyy-MM-dd HH:mm:ss 日期*/
    public static String timet2FullString(int timet) {
        return (timet2String(timet, FULL_PATTERN));
    }
    /*int 类型秒数转成 string yyyy-MM-dd 日期*/
    public static String timet2DateString(int timet) {
        return (timet2String(timet, DATE_PATTERN));
    }
    /*String 类型日期 获得对应 int 秒数*/
    public static int string2Timet(String s, String pattern)
            throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return ((int) (sdf.parse(s).getTime() / MSEC));

    }
    /*String yyyy-MM-dd 类型日期 获得对应 int 秒数----获得某天起始秒数*/
    public static int dayBegin2Timet(String s) throws ParseException {
        return (string2Timet(s, DATE_PATTERN));
    }
    /*String yyyy-MM-dd 类型日期 获得对应 int 秒数----获得某天  结束秒数*/
    public static int dayEnd2Timet(String s) throws ParseException {
        return (string2Timet(s + " 23:59:59", FULL_PATTERN));
    }
    /*int 秒数 获得当天起始秒数*/
    public static int dayBegin2Timet(int t) throws ParseException {

        String s = timet2DateString(t);
        return (string2Timet(s, DATE_PATTERN));
    }
    /*int 秒数 获得当天结束秒数*/
    public static int dayEnd2Timet(int t) throws ParseException {
        String s = timet2DateString(t);
        return (string2Timet(s + " 23:59:59", FULL_PATTERN));
    }

    public static int yearOfTimet(int timet){
        String year = fetchTimeSubString(timet, 0, 4);
        return Integer.parseInt(year);
    }

    public static int monthOfTimet(int timet){
        String month = fetchTimeSubString(timet, 5, 7);
        return Integer.parseInt(month);
    }

    public static int dayOfTimet(int timet){
        String day = fetchTimeSubString(timet, 8, 10);
        return Integer.parseInt(day);
    }

    public static int hourOfTimet(int timet){
        String hour = fetchTimeSubString(timet, 11, 13);
        return Integer.parseInt(hour);
    }

    public static int minuteOfTimet(int timet){
        String min = fetchTimeSubString(timet, 14, 16);
        return Integer.parseInt(min);
    }

    public static int secondOfTimet(int timet){
        String sec = fetchTimeSubString(timet, 17, 19);
        return Integer.parseInt(sec);
    }

    private static String fetchTimeSubString(int destTimet,int begin,int end){
        String s = timet2FullString(destTimet);
        return s.substring(begin, end);
    }

    public static String toFmtTime(Timestamp time,String pattern){
        long milltime = time.getTime();
        if(pattern == null)
            return timet2FullString((int)(milltime/1000));
        else{
            try {
                return timet2String((int)(milltime/1000), pattern);
            } catch (Exception e) {
                return null;
            }
        }
    }
    public static String formatDateTimeToStr(Date date)
    {
        String str=formatDateTimeToStr(null,date,TimeUtil.FULL_PATTERN);
        return str;
    }
    public static String formatDateTimeToStr(Date date,String format)
    {
        String str=formatDateTimeToStr(null,date,format);
        return str;
    }
    public static String formatDateTimeToStr(TimeZone zone,Date date)
    {
        SimpleDateFormat fmt=new SimpleDateFormat(FULL_PATTERN);
        if(zone!=null)fmt.setTimeZone(zone);
        return fmt.format(date);
    }
    public static String formatDateTimeToStr(TimeZone zone,Date date,String format)
    {
        if(format==null || format.equals(""))format=FULL_PATTERN;
        SimpleDateFormat fmt=new SimpleDateFormat(format);
        if(zone!=null)fmt.setTimeZone(zone);
        return fmt.format(date);
    }

    public static Date dateFormFormatStr(String dateStr,String format)
    {
        SimpleDateFormat fmt=new SimpleDateFormat(format);
        Date result=null;
        try {
            result = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    //获取日期清除时间    用于比较日期区间时的开始时间
    public static Date getBeginDateClearTime(Date date) {

        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }
    //获取日期并设置时间为最后时间(23:59:59.999)    用于比较日期区间时的结束时间，用小于等于号比较
    public static Date getEndDateEndTime(Date date) {

        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        c.set(Calendar.MILLISECOND,999);
        return c.getTime();
    }
    //获取日期下一天并清除时间    用于比较日期区间时的结束时间，用小于号比较
    public static Date getEndDateForNextDay(Date date) {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }
    /**
     *  //获取日期下一天并清除时间    用于比较日期区间时的结束时间，用小于号比较
     *  修改 天数可配
     *  可以为负值，提前几天
     */
    public static Date getEndDateForNextDay(Date date,int daycount) {

        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,daycount);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }
    public static int getYear(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }
    public static int getMonth(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH)+1;
    }
    public static int getDay(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }
    public static int getHour(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinute(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }
    public static int getSecond(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }
    public static int  getWeek(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        int week= c.get(Calendar.DAY_OF_WEEK);
        if(week==Calendar.MONDAY)return 1;
        if(week==Calendar.TUESDAY)return 2;
        if(week==Calendar.WEDNESDAY)return 3;
        if(week==Calendar.THURSDAY)return 4 ;
        if(week==Calendar.FRIDAY)return 5 ;
        if(week==Calendar.SATURDAY)return 6 ;
        return 7;
    }
    public static Date getDateFormHourMinuteStr(String hhmm) throws ParseException
    {
        return getDateFormHourMinuteStr("",hhmm);
    }

    public static Date getDateFormHourMinuteStr(String ymd,String hhmm) throws ParseException
    {
        SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fmt="";

        if(ymd==null || ymd.length()<10)
        {
            fmt="1900-01-01 "+hhmm+":00";
        }else
        {
            ymd=ymd.trim();
            fmt=ymd+" "+hhmm+":00";
        }

        Date reulst=dft.parse(fmt);

        return reulst;
    }

    public static int betweenDays(long beginMilliseconds,long endMilliseconds)
    {
        long temp = endMilliseconds- beginMilliseconds;
        return  (int)(temp / DAY_MILLIS) ;
    }
    public static int betweenHours(long beginMilliseconds,long endMilliseconds)
    {
        long temp = endMilliseconds- beginMilliseconds;
        return   (int)(temp / HOUR_MILLIS);
    }
    public static int betweenMinute(long beginMilliseconds,long endMilliseconds)
    {
        long temp = endMilliseconds- beginMilliseconds;
        return   (int)(temp / MINUTE_MILLIS);
    }
    public static int betweenSecond(long beginMilliseconds,long endMilliseconds)
    {
        long temp = endMilliseconds- beginMilliseconds;
        return   (int)(temp / SECOND_MILLIS);
    }
    public static Date getMonthFirstDay(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }
    public static Date getMonthLastDay(Date date)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }
    public static Date getYearFirstDay(int year)
    {
        Calendar c=Calendar.getInstance();
        c.set(year, 0, 1, 0, 0, 0);
        return c.getTime();
    }
    public static Date getYearLastDay(int year)
    {
        Calendar c=Calendar.getInstance();
        c.set(year, 11, 1, 0, 0, 0);
        c.add(Calendar.MONTH,1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }
    public static Date addDate(Date date,int days)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,days);
        return c.getTime();
    }
    public static Date addHour(Date date,int hours)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY,hours);
        return c.getTime();
    }
    public static Date addMinute(Date date,int minutes)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE,minutes);
        return c.getTime();
    }
    public static Date addSecond(Date date,int seconds)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND,seconds);
        return c.getTime();
    }
    public static Date addMonth(Date date,int months)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,months);
        return c.getTime();
    }
    /**
     * 根据参数创建一个日期
     * 注意，月份从1开始
     */
    public static Date createDate(int year,int month,int date,int hour,int minute,int second)
    {
        Calendar c=Calendar.getInstance();
        c.set(year, month-1,date,hour,minute,second);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }
    /**
     * 比较两个日期是否相等。年，月，日，时，分，秒相同的日期即相等
     */
    public static boolean dateEquals(Date date1,Date date2)
    {
        Calendar c=Calendar.getInstance();
        c.setTime(date1);
        c.set(Calendar.MILLISECOND,0);
        date1=c.getTime();

        c.setTime(date2);
        c.set(Calendar.MILLISECOND,0);
        date2=c.getTime();
        return date1.equals(date2);
    }
    public static boolean isDateTime(String dateStr,String format)
    {
        if(dateStr==null || format==null)return false;
        if(dateStr.length()!=format.length())return false;

        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            Date date=dateFormat.parse(dateStr);
            String result=dateFormat.format(date);
            return dateStr.equals(result);      // 这里用equals主要是为了防止像20121032这样数据会转为20121101
        }
        catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }

    }
    /**
     *计算开始年月与结束年月之间包含多少个月份。开区间。
     *
     */
    public static int getMonthsBetweenYearMonth(int startYear,int startMonth,int endYear,int endMonth)
    {
        int startYearMonth=startYear*100+startMonth;
        int endYearMonth=endYear*100+endMonth;
        if(startYearMonth>endYearMonth)
        {
            int tmpYear=startYear;
            int tmpMonth=startMonth;
            startYear=endYear;
            startMonth=endMonth;
            endYear=tmpYear;
            endMonth=tmpMonth;
        }
        int year=endYear-startYear;
        if(endMonth>startMonth)
        {
            int month=endMonth-startMonth+1;
            return year*12+month;
        }else
        {
            int month=(12-startMonth+1)+endMonth;
            year=year-1;
            return year*12+month;
        }
    }
    public static int getMonthsBetweenYearMonth(int startYearMonth, int endYearMonth)
    {
        int startYear=startYearMonth/100;
        int startMonth=startYearMonth % 100;
        int endYear=endYearMonth/100;
        int endMonth=endYearMonth % 100;

        return getMonthsBetweenYearMonth(startYear,startMonth,endYear,endMonth);
    }




    public static String getNowDayTimeShortStr() {

        SimpleDateFormat sdf = new SimpleDateFormat(FULL_SHORT_PATTERN);
        String s = sdf.format(new Date());
        return (s);
    }

    public static String getNowDayShortStr() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_SHORT_PATTERN);
        String s = sdf.format(new Date());
        return (s);
    }

    public static String getNowDayFullStr() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String s = sdf.format(new Date());
        return (s);
    }

    public static String getNowDayTimeFullStr() {

        SimpleDateFormat sdf = new SimpleDateFormat(FULL_PATTERN);
        String s = sdf.format(new Date());
        return (s);
    }
    /**
	 * 时间处理_2, String 时间，返回友好时间 几分钟前，几小时前，等
	 * String 时间格式yyyy-MM-dd HH:mm:ss"
	 * @author c_abc
	 *
	 */
	public static String getTimeYh(String timeStr ,Date daa){
			String str=timeStr.trim();
			Date da=daa;
			Date ta=TimeUtil.dateFormFormatStr(str,TimeUtil.FULL_PATTERN);
			Long old=ta.getTime();
			Long now=da.getTime();
			int t=TimeUtil.betweenDays(old, now);
			//大于零，昨天以前
			if(t>0)	return ""+str.substring(5,str.length()-3);		if(t<0)   return "";
			int s=TimeUtil.betweenHours(old, now);
			if(s>0)		return ""+s+"小时前";  		if(s<0) return "";
			int f=TimeUtil.betweenMinute(old, now);
			if(f>0) return ""+f+"分钟前";
			if(f==0) return ""+1+"分钟内";
			return "";
	}
   
    /**
     * 将字符型XXXX-XX-XX转化成Date类型
     * @param day
     * @return
     */
    public static Date getStringToDate(String day){
    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
    	try {    
            date = format1.parse(day);   
		 } catch (ParseException e) {    
		            e.printStackTrace();    
		 }  
		return date;
    }
    
    /** *//**计算两个时间之间相隔天数 
    * @param startday 开始时间 
    * @param endday 结束时间 
    * @return 
    */ 
    public static int getDaysBetween (Date day1, Date day2) { 
    	Calendar d1=Calendar.getInstance();
    	Calendar d2=Calendar.getInstance();
    	d1.setTime(day1);
    	d2.setTime(day2);
    	if (d1.after(d2)){ // swap dates so that d1 is start and d2 is end 
	    	Calendar swap = d1; 
	    	d1 = d2; 
	    	d2 = swap; 
    	} 
    	int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR); 
    	int y2 = d2.get(Calendar.YEAR); 
    	if (d1.get(Calendar.YEAR) != y2) { 
    		d1 = (Calendar) d1.clone(); 
    		do { 
    			days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数 
    			d1.add(Calendar.YEAR, 1); 
    		} while (d1.get(Calendar.YEAR) != y2); 
    	} 
    	return days; 
    }
    

    /**
     * 获取时间总和
     * @param Date1	原有时间
     * @param days	需要添加的天数
     * @return
     */
    public static String getDateAddDay(String Date1,int days){
    	Date date = TimeUtil.getStringToDate(Date1);
    	Calendar day1=Calendar.getInstance();
    	day1.setTime(date);
    	day1.add(Calendar.DATE, days);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 获取时间格式化工具
    	String day = sdf.format(day1.getTime());
    	return day;
    }
    /**
     * 时间差   计算差多少天
     * @param startTime 开始时间
     * @param endTime	结束时间
     * @param format	日期类型
     */
    public static int dateDiff(Date startTime1, Date endTime1, String format) throws Exception{
    	String startTime = TimeUtil.formatDateTimeToStr(startTime1,TimeUtil.FULL_PATTERN);
    	String endTime = TimeUtil.formatDateTimeToStr(endTime1,TimeUtil.FULL_PATTERN);
    	SimpleDateFormat sd = new SimpleDateFormat(format);
    	long nd = 1000*24*60*60;//一天的毫秒数
    	long diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
    	long day = diff/nd;//计算差多少天
    	int val = Integer.parseInt(String.valueOf(day));  
    	return val;
    }
    /**
     * 时间差 
     * @param startTime1	开始日期
     * @param endTime1		结束日期
     * @param format		日期类型
     * @param type			查询类型  0 秒差 1 分差 2 时差 3 天差
     * @return
     * @throws Exception
     */
    public static int dateDiff(Date startTime1, Date endTime1, String format,int type) throws Exception{
    	String startTime = TimeUtil.formatDateTimeToStr(startTime1,TimeUtil.FULL_PATTERN);
    	String endTime = TimeUtil.formatDateTimeToStr(endTime1,TimeUtil.FULL_PATTERN);
    	SimpleDateFormat sd = new SimpleDateFormat(format);
    	long diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
    	switch(type){
    		case 0://秒差
    			long sec = diff%DAY_MILLIS%HOUR_MILLIS%MINUTE_MILLIS/SECOND_MILLIS;
    			return Integer.parseInt(String.valueOf(sec)); 
    		case 1://分差
    			long min = diff%DAY_MILLIS%HOUR_MILLIS/MINUTE_MILLIS;
    			return Integer.parseInt(String.valueOf(min)); 
    		case 2://时差
    			long hour = diff%DAY_MILLIS/HOUR_MILLIS;
    			return Integer.parseInt(String.valueOf(hour)); 
    		default://天差
    			long day = diff/DAY_MILLIS;//计算差多少天
    			return Integer.parseInt(String.valueOf(day)); 
    	}
    }
    
    public  static void main(String[] args)throws Exception{
    	SimpleDateFormat format= new SimpleDateFormat(TimeUtil.FULL_PATTERN);
    	String strDate = "2016-02-23 09:56:14";
    	Date date=format.parse(strDate);//str表示yyyy年MM月dd HH:mm:ss格式字符串
    	int date1 = TimeUtil.dateDiff(date, new Date(), TimeUtil.FULL_PATTERN,1);
//    	int date2 = TimeUtil.dateDiff(date, new Date(), TimeUtil.FULL_PATTERN,2);
//    	int date3 = TimeUtil.dateDiff(date, new Date(), TimeUtil.FULL_PATTERN,3);
//    	System.out.println(date1+","+date2+","+date3);
    	System.out.println(date1);
       	
    }
}

