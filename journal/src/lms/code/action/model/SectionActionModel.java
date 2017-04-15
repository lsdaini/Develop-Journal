package lms.code.action.model;

import java.sql.Date;

import lms.code.beans.enums.LMS_SectionStatus;

public class SectionActionModel {
	private Long sectionID;
	private String sectionName;
	private Long managerId;
	private Long acceptorId;
	private Long creatorId;
	private Date startDate;
	private Date planEndDate;
	private Date endDate;
	private Long projectID;
	private LMS_SectionStatus status;
	private String remark;
	
	public Long getSectionID() {
		return sectionID;
	}
	public String getSectionName() {
		return sectionName;
	}
	public Long getManagerId() {
		return managerId;
	}
	public Long getAcceptorId() {
		return acceptorId;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getPlanEndDate() {
		return planEndDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Long getProjectID() {
		return projectID;
	}
	public LMS_SectionStatus getStatus() {
		return status;
	}
	public String getRemark() {
		return remark;
	}
	public void setSectionID(Long sectionID) {
		this.sectionID = sectionID;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public void setAcceptorId(Long acceptorId) {
		this.acceptorId = acceptorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public void setStatus(LMS_SectionStatus status) {
		this.status = status;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
