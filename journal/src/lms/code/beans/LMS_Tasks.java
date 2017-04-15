package lms.code.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lms.code.beans.enums.LMS_TaskLevel;
import lms.code.beans.enums.LMS_TaskStatus;

@Entity
@Table(name = "LMS_Tasks")
public class LMS_Tasks{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long taskID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SectionID")
	private LMS_Sections section;
	
	private String name;

	@Enumerated(EnumType.ORDINAL)
	private LMS_TaskStatus status;

	@Enumerated(EnumType.ORDINAL)
	private LMS_TaskLevel taskLevel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CreatorID")
	private LMS_Staffs creator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ManagerID")
	private LMS_Staffs manager;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AcceptorID")
	private LMS_Staffs acceptor;
	private Date startDate;
	private Date planEndDate;
	private Date endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ParentID")
	private LMS_Tasks parent;
	
	@Column(columnDefinition="VARCHAR(2000) default null") 
	private String remark;
	
	//[start] 导航属性
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "task")
	private Set<LMS_Logs> taskLogs = new HashSet<LMS_Logs>();
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "parent")
	private Set<LMS_Tasks> children = new HashSet<LMS_Tasks>();
	//[end]
	
    public LMS_Tasks (){
		
	}
	
	public LMS_Tasks (Long taskID,String taskName){
		this.taskID = taskID;
		this.name = taskName;
	}
	
	//[start] get and set methods
	public long getTaskID() {
		return taskID;
	}

	public void setTaskID(long taskID) {
		this.taskID = taskID;
	}

	public LMS_Sections getSection() {
		return section;
	}

	public void setSection(LMS_Sections section) {
		this.section = section;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LMS_TaskStatus getStatus() {
		return status;
	}

	public void setStatus(LMS_TaskStatus status) {
		this.status = status;
	}

	public LMS_TaskLevel getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(LMS_TaskLevel taskLevel) {
		this.taskLevel = taskLevel;
	}

	public LMS_Staffs getCreator() {
		return creator;
	}

	public void setCreator(LMS_Staffs creator) {
		this.creator = creator;
	}

	public LMS_Staffs getManager() {
		return manager;
	}

	public void setManager(LMS_Staffs manager) {
		this.manager = manager;
	}

	public LMS_Staffs getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(LMS_Staffs acceptor) {
		this.acceptor = acceptor;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public Set<LMS_Logs> getTaskLogs() {
		return taskLogs;
	}

	public void setTaskLogs(Set<LMS_Logs> taskLogs) {
		this.taskLogs = taskLogs;
	}

	public LMS_Tasks getParent() {
		return parent;
	}

	public void setParent(LMS_Tasks parent) {
		this.parent = parent;
	}

	public Set<LMS_Tasks> getChildren() {
		return children;
	}

	public void setChildren(Set<LMS_Tasks> children) {
		this.children = children;
	}
	//[end]
}
