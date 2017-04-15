package lms.code.action.model;

import java.sql.Date;

import lms.code.beans.enums.LMS_TaskStatus;

public class TaskActionModel {
	private Long taskID;
	private String taskName;
	private Long managerId;
	private Long acceptorId;
	private Long creatorId;
	private Date startDate;
	private Date planEndDate;
	private Date endDate;
	private Long sectionId;
	private Long parentId;
	private LMS_TaskStatus status;
	private String remark;
	
	
	public Long getTaskID() {
		return taskID;
	}
	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}
	public String getTaskName() {
		return taskName;
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
	public Long getSectionId() {
		return sectionId;
	}
	public Long getParentId() {
		return parentId;
	}
	public LMS_TaskStatus getStatus() {
		return status;
	}
	public String getRemark() {
		return remark;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setStatus(LMS_TaskStatus status) {
		this.status = status;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
