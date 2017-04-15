package lms.code.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lms.code.beans.enums.LMS_WorkReportRemindStatus;
import lms.code.beans.enums.LMS_WorkReportStatus;

@Entity
@Table(name = "LMS_WorkReports")
public class LMS_WorkReports   {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long reportID;
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ReporterID")
	private LMS_Staffs reporter;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LeaderID")
	private LMS_Staffs leader;
	
	@Enumerated(EnumType.ORDINAL)
	private LMS_WorkReportStatus status;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(columnDefinition="int(10) default 0") 
	private LMS_WorkReportRemindStatus leaderRemind;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(columnDefinition="int(10) default 0") 
	private LMS_WorkReportRemindStatus staffRemind;
	
	
	private Date reportTime;
	private Date startDate;
	private Date endDate;
	@Column(columnDefinition="VARCHAR(2000) default null") 
	private String summary;
	@Column(columnDefinition="VARCHAR(2000) default null") 
	private String selfEvalu;
	@Column(columnDefinition="VARCHAR(2000) default null") 
	private String bossEvalu;
	
	//[start] 业务方法
	public String getUsedDays(){
		long diff = endDate.getTime() - startDate.getTime();
		long day = diff / (24 * 60 * 60 * 1000)+1;
		return day+"天";
	}
	//[end]
	
	//[start] get and set methods
	public long getReportID() {
		return reportID;
	}

	public void setReportID(long reportID) {
		this.reportID = reportID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LMS_Staffs getReporter() {
		return reporter;
	}

	public void setReporter(LMS_Staffs reporter) {
		this.reporter = reporter;
	}

	public LMS_WorkReportStatus getStatus() {
		return status;
	}

	public void setStatus(LMS_WorkReportStatus status) {
		this.status = status;
	}

	public LMS_WorkReportRemindStatus getStaffRemind() {
		return staffRemind;
	}

	public void setStaffRemind(LMS_WorkReportRemindStatus staffRemind) {
		this.staffRemind = staffRemind;
	}

	public LMS_WorkReportRemindStatus getLeaderRemind() {
		return leaderRemind;
	}

	public void setLeaderRemind(LMS_WorkReportRemindStatus leaderRemind) {
		this.leaderRemind = leaderRemind;
	}

	public Date getReportTime() {
		return reportTime;
	}  
	
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSelfEvalu() {
		return selfEvalu;
	}

	public void setSelfEvalu(String selfEvalu) {
		this.selfEvalu = selfEvalu;
	}

	public String getBossEvalu() {
		return bossEvalu;
	}

	public void setBossEvalu(String bossEvalu) {
		this.bossEvalu = bossEvalu;
	}

	public LMS_Staffs getLeader() {
		return leader;
	}

	public void setLeader(LMS_Staffs leader) {
		this.leader = leader;
	}
	//[end]
	
}
