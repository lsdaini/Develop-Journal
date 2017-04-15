package lms.code.action.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lms.code.beans.enums.LMS_LogFreeType;
import lms.code.beans.enums.LMS_LogType;

public class LogActionModel {

	private String selectDate;
	private Long logId;
	
	
	// [start] addLog method parameters
	private Date startTime;
	private Date endTime;
	private Long projectTask;
	private String percentage;
	private LMS_LogFreeType freeType;
	private String content;
	private LMS_LogType type;

	// [end]

	// [start] get and set methods
	
	
	public String getSelectDate() {
		return selectDate;
	}

	public LMS_LogType getType() {
		if(projectTask == null)
			return LMS_LogType.NoProject;
		return LMS_LogType.Project;
	}

	public void setType(LMS_LogType type) {
		this.type = type;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		DateFormat  simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			this.startTime = simple.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		DateFormat  simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			this.endTime = simple.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Long getProjectTask() {
		return projectTask;
	}

	public void setProjectTask(Long projectTask) {
		this.projectTask = projectTask;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public LMS_LogFreeType getFreeType() {
		return freeType;
	}

	public void setFreeType(Integer freeType) {
		this.freeType = LMS_LogFreeType.values()[freeType];
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	// [end]
}
