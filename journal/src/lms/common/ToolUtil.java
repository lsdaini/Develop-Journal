package lms.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToolUtil {
	public ToolUtil(){
		
	}
	public static final String [] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	
	public static String doGetLogListTitle(String selectDate){
		DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateStr = simple.parse(selectDate);
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(dateStr);
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)  w = 0;
	        return selectDate+ "  "+weekDays[w];

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String doGetReportListDate(Date logDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(logDate);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)  w = 0;
        return weekDays[w];
	}
	
	public static String doGetReportListTitleDate(Date logSection){
		DateFormat simple = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar cal = Calendar.getInstance();
        cal.setTime(logSection);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)  w = 0;
        return simple.format(logSection)+" "+weekDays[w];
	}
}
