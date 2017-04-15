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

import lms.code.beans.enums.LMS_SectionStatus;

@Entity
@Table(name = "LMS_Sections")
public class LMS_Sections{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sectionID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ProjectID")
	private LMS_Projects project;

	private String name;

	@Enumerated(EnumType.ORDINAL)
	private LMS_SectionStatus status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ManagerID")
	private LMS_Staffs manager;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CreatorID")
	private LMS_Staffs creator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AcceptorID")
	private LMS_Staffs acceptor;
	private Date startDate;
	private Date planEndDate;
	private Date endDate;
	
	@Column(columnDefinition="VARCHAR(2000) default null") 
	private String remark;
	
	//[start] 导航属性
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "section", fetch = FetchType.EAGER)
	private Set<LMS_Tasks> tasks = new HashSet<LMS_Tasks>();
	//[end]
	
	public LMS_Sections (){
		
	}
	
	public LMS_Sections (Long sectionId,String sectionName){
		this.sectionID = sectionId;
		this.name = sectionName;
	}
	
	//[start] get and set methods
	public long getSectionID() {
		return sectionID;
	}

	public void setSectionID(long sectionID) {
		this.sectionID = sectionID;
	}

	public LMS_Projects getProject() {
		return project;
	}

	public void setProject(LMS_Projects project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LMS_SectionStatus getStatus() {
		return status;
	}

	public void setStatus(LMS_SectionStatus status) {
		this.status = status;
	}

	public LMS_Staffs getManager() {
		return manager;
	}

	public void setManager(LMS_Staffs manager) {
		this.manager = manager;
	}

	public LMS_Staffs getCreator() {
		return creator;
	}

	public void setCreator(LMS_Staffs creator) {
		this.creator = creator;
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

	public Set<LMS_Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(Set<LMS_Tasks> tasks) {
		this.tasks = tasks;
	}
	//[end]

}
