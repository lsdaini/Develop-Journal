package lms.code.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LMS_Projects")
public class LMS_Projects  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectID;
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ManagerID")
	private LMS_Staffs manager;
	private Date startDate;
	private Date planEndDate;
	private Date endDate;
	
	@Column(columnDefinition="VARCHAR(2000) default null") 
	private String remark;

	//[start] 导航属性
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
	private Set<LMS_Sections> sections = new HashSet<LMS_Sections>();
	//[end]
	
	//[start] get and set methods
	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LMS_Staffs getManager() {
		return manager;
	}

	public void setManager(LMS_Staffs manager) {
		this.manager = manager;
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

	public Set<LMS_Sections> getSections() {
		return sections;
	}

	public void setSections(Set<LMS_Sections> sections) {
		this.sections = sections;
	}
	//[end]
	
}
