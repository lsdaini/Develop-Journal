package lms.code.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lms.code.beans.enums.LMS_LogFreeType;
import lms.code.beans.enums.LMS_LogType;

@Entity
@Table(name = "LMS_Logs")
public class LMS_Logs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long logID;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TaskID")
	private LMS_Tasks task;

	@Enumerated(EnumType.ORDINAL)
	private LMS_LogType type;

	@Enumerated(EnumType.ORDINAL)
	private LMS_LogFreeType freeType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ManagerID")
	private LMS_Staffs manager;

	private String content;
	private String percentage;
	private Date startTime;
	private Date endTime;

	
	//[start] 业务方法
	public String getUsedTime() {
		long diff = endTime.getTime() - startTime.getTime();
		long day = diff / (24 * 60 * 60 * 1000);
		long hour = (diff / (60 * 60 * 1000) - day * 24);
		long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		return hour+"小时"+min+"分钟";
	}
	//[end]
	
	// [start] get and set methods
	public long getLogID() {
		return logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LMS_Tasks getTask() {
		return task;
	}

	public void setTask(LMS_Tasks task) {
		this.task = task;
	}

	public LMS_LogType getType() {
		return type;
	}

	public void setType(LMS_LogType type) {
		this.type = type;
	}

	public LMS_LogFreeType getFreeType() {
		return freeType;
	}

	public void setFreeType(LMS_LogFreeType freeType) {
		this.freeType = freeType;
	}

	public LMS_Staffs getManager() {
		return manager;
	}

	public void setManager(LMS_Staffs manager) {
		this.manager = manager;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	// [end]
	
}
