package lms.code.beans;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "LMS_Staffs")
public class LMS_Staffs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long staffID;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RoleID")
	private LMS_Roles role;

	private String idCard;
	private String loginName;
	private String passWord;
	private String tel;
	private String crashTel;
	private String email;
	
	@Column(columnDefinition="VARCHAR(2000) default null") 
	private String contactAddress;

	//[start] 导航属性
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
	private Set<LMS_Logs> logs = new HashSet<LMS_Logs>();
	//[end]
	
	//[start] get and set methods
	public long getStaffID() {
		return staffID;
	}

	public void setStaffID(long staffID) {
		this.staffID = staffID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LMS_Roles getRole() {
		return role;
	}

	public void setRole(LMS_Roles role) {
		this.role = role;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCrashTel() {
		return crashTel;
	}

	public void setCrashTel(String crashTel) {
		this.crashTel = crashTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Set<LMS_Logs> getLogs() {
		return logs;
	}

	public void setLogs(Set<LMS_Logs> logs) {
		this.logs = logs;
	}
	//[end]
	
}
