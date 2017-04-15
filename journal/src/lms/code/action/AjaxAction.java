package lms.code.action;

import java.util.Collection;
import javax.annotation.Resource;

import lms.code.beans.LMS_Projects;
import lms.code.beans.LMS_Sections;
import lms.code.beans.LMS_Tasks;
import lms.code.beans.LMS_WorkReports;
import lms.code.beans.enums.LMS_WorkReportRemindStatus;
import lms.code.beans.enums.LMS_WorkReportStatus;
import lms.code.service.ProjectService;
import lms.code.service.SectionService;
import lms.code.service.TaskService;
import lms.code.service.WorkReportService;
import lms.common.AbstractAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import dev.frame.util.JSONUtil;
import dev.frame.util.StringUtil;

@ParentPackage("ajaxPackage")
@Action("ajaxActions")
@Results({ @Result(type = "json") })
public class AjaxAction extends AbstractAction {
	private static final long serialVersionUID = 206406447636752221L;
	@Resource
	private SectionService sectionService;
	@Resource
	private TaskService taskService; 
	@Resource
	private ProjectService projectService;
	@Resource
	private WorkReportService  workReportService;
	
	private String result;
	
	public String doSearchProjects(){
		dev.frame.common.Result<Collection<LMS_Projects>> result = new dev.frame.common.Result<Collection<LMS_Projects>>("",1);
		try {
			Collection<LMS_Projects> projects = projectService.getAllProjects();
			for (LMS_Projects lms_Projects : projects) {
				lms_Projects.setManager(null);
				lms_Projects.setSections(null);
			}
			result.setData(projects);
			
		} catch (Exception e) {
			result.setNumber(-1);
			result.setMessage(e.getMessage());
		}
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	public String doSearchSections(){
		Long projectId = Long.parseLong(getRequest("projectID"));
		dev.frame.common.Result<Collection<LMS_Sections>> result = new dev.frame.common.Result<Collection<LMS_Sections>>("",1);
		try {
			Collection<LMS_Sections> sections = sectionService.getSectionsByProjectId(projectId);
			result.setData(sections);
		} catch (Exception e) {
			result.setNumber(-1);
			result.setMessage(e.getMessage());
		}
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	public String doSearchSectionTasks(){
		Long sectionId = Long.parseLong(getRequest("sectionID"));
		Long parentTaskID =StringUtil.isNullOrEmpty(getRequest("taskID"))?null: Long.parseLong(getRequest("taskID"));
		dev.frame.common.Result<Collection<LMS_Tasks>> result = new dev.frame.common.Result<Collection<LMS_Tasks>>("",1);
		try{
			Collection<LMS_Tasks> tasks = taskService.getTasksBySectionId(sectionId,parentTaskID);
			result.setData(tasks);
		}catch(Exception e){
			result.setNumber(-1);
			result.setMessage(e.getMessage());
		}
		
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	public String doDeleteOneSection(){
		Long sectionId = Long.parseLong(getRequest("sectionID"));
		dev.frame.common.Result<Integer> result = new dev.frame.common.Result<Integer>("",1,1);
		try {
			sectionService.deleteOneSection(sectionId);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setNumber(-1);
			result.setData(0);
		}
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	public String doDeleteOneTask(){
		Long taskId = Long.parseLong(getRequest("taskID"));
		dev.frame.common.Result<Integer> result = new dev.frame.common.Result<Integer>("",1,1);
		try {
			taskService.deleteOneTask(taskId);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setNumber(-1);
			result.setData(0);
		}
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	public String doUpdateRemindStatus(){
		Long reportID = Long.parseLong(getRequest("reportid"));
		boolean isLeader = getRequest("isleader").equals("true");
		dev.frame.common.Result<Integer> result = new dev.frame.common.Result<Integer>("",1,1);
		try {
			workReportService.updateRemindStatus(reportID, LMS_WorkReportRemindStatus.Viewed, isLeader);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setNumber(-1);
			result.setData(0);
		}
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	public String doGetStaffRemindCount(){
		Long staffID = getSessionUser().getStaffID();
		dev.frame.common.Result<Integer> result = new dev.frame.common.Result<Integer>("",1,1);
		try {
			int dataCount = 
			workReportService.getAuditedReports(staffID).size()+
			workReportService.getNeedAuditReports(staffID).size();
			result.setData(dataCount);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setNumber(-1);
			result.setData(0);
		}
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	public String doBossEvalu(){
		Long reportID = Long.parseLong(getRequest("reportid"));
		String bossEvalu = getRequest("bossevalu");
		dev.frame.common.Result<Integer> result = new dev.frame.common.Result<Integer>("",1,1);
		try {
			LMS_WorkReports reportInfo = workReportService.getReportInfoById(reportID);
			reportInfo.setBossEvalu(bossEvalu);
			reportInfo.setStatus(LMS_WorkReportStatus.Audited);
			reportInfo.setLeaderRemind(LMS_WorkReportRemindStatus.Viewed);
			workReportService.updateOneReport(reportInfo);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setNumber(-1);
			result.setData(0);
		}
		this.result = JSONUtil.toJSON(result);
		return SUCCESS;
	}
	
	//[start] get and set methods
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	//[end]
}
