package lms.code.action.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
public class WorkReportActionModel {
	private long reportID;
	private Long reporterID;
	private String name;
	private Date startDate;
	private Date endDate;
	private String bossEvalu;
	private String summary;
	private String selfEvalu;
	private Collection<Long> leaders;
	
	public long getReportID() {
		return reportID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		SimpleDateFormat  simple = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.startDate = (Date) simple.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		SimpleDateFormat  simple = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.endDate = (Date) simple.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setReportID(long reportID) {
		this.reportID = reportID;
	}

	public String getBossEvalu() {
		return bossEvalu;
	}

	public void setBossEvalu(String bossEvalu) {
		this.bossEvalu = bossEvalu;
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

	public Collection<Long> getLeaders() {
		return leaders;
	}

	public void setLeaders(Collection<Long> leaders) {
		this.leaders = leaders;
	}

	public Long getReporterID() {
		return reporterID;
	}

	public void setReporterID(Long reporterID) {
		this.reporterID = reporterID;
	}
	
}
