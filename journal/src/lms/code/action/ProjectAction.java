package lms.code.action;

import java.util.Collection;

import javax.annotation.Resource;

import lms.code.action.model.ProjectActionModel;
import lms.code.action.returnconst.ProjectActionConst;
import lms.code.beans.LMS_Projects;
import lms.code.beans.LMS_Sections;
import lms.code.beans.LMS_Staffs;
import lms.code.service.ProjectService;
import lms.code.service.StaffService;
import lms.common.AbstractAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("projectPackage")
@Action(value = "projectActions")
@Results({ 
   @Result(name = ProjectActionConst.GetProjectList_Success,location = "/manage/ProjectManage/ProjectList.jsp")
  ,@Result(name = ProjectActionConst.InitialAddProjectInfo_Success,location = "/manage/ProjectManage/AddProject.jsp")
  ,@Result(name = ProjectActionConst.AddProjectInfo_Success,location = "/manage/ProjectManage/AddProject.jsp")
  ,@Result(name = ProjectActionConst.InitialEditProjectInfo_Success,location = "/manage/ProjectManage/EditProject.jsp")
  ,@Result(name = ProjectActionConst.EditProjectInfo_Success,location = "/manage/ProjectManage/EditProject.jsp")
  ,@Result(name = ProjectActionConst.DeleteOneProject_Success,location = "/actions/project/projectActions.action?method=getProjectInfoList",type="redirect")
})

public class ProjectAction extends AbstractAction implements ModelDriven<ProjectActionModel>{
	private static final long serialVersionUID = 4197053519780341672L;
	
	@Resource
	private ProjectService projectService;
	@Resource
	private StaffService staffService;
	
	private ProjectActionModel actionModel = new ProjectActionModel();
	
	private LMS_Projects projectInfo;
	private Collection<LMS_Staffs> staffInfoList;
	private Collection<LMS_Projects> projectList;
	private Collection<LMS_Sections> sectionList;
	
	public String getProjectInfoList(){
		this.setPageSize(5);
		this.projectList = projectService.getProjectList(page);
		return ProjectActionConst.GetProjectList_Success;
	}
	
	public String initialAddProject(){
		this.staffInfoList=staffService.getStaffListExceptAdmin();
		return ProjectActionConst.InitialAddProjectInfo_Success;
	}
	
	public String addProject(){
		this.staffInfoList=staffService.getStaffListExceptAdmin();
		if(actionModel!=null){
			LMS_Projects projectInfoT = new LMS_Projects();
			projectInfoT.setName(actionModel.getProjectName());
			LMS_Staffs staffInfoT = new LMS_Staffs();
			staffInfoT = staffService.getStaffByStaffID(actionModel.getManagerId());
			if(staffInfoT != null){
				projectInfoT.setManager(staffInfoT);
			}
			projectInfoT.setStartDate(actionModel.getStartDate());
			projectInfoT.setPlanEndDate(actionModel.getPlanEndDate());
			projectInfoT.setRemark(actionModel.getRemark());
			if (projectInfoT != null) {
				int addProjectResult = projectService.addNewProjectInfo(projectInfoT);
				if (addProjectResult == 0) {
					registerScript("fnProjectNameIsExist();", true);
				} else {
					registerScript("fnAddNewProjectSuccess();", true);
				}
			}
		}
		return ProjectActionConst.AddProjectInfo_Success;
	}
	
	public String initialEditProject(){
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		this.projectInfo = projectService.getOneProject(actionModel.getProjectID());
		return ProjectActionConst.InitialEditProjectInfo_Success;
	}
	
	public String editProject(){
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		this.projectInfo = projectService.getOneProject(actionModel.getProjectID());
		if(actionModel != null && actionModel.getProjectID() != null && ! "".equals(actionModel.getProjectID())){
			LMS_Projects projectInfoT = projectService.getOneProject(actionModel.getProjectID());
			projectInfoT.setName(actionModel.getProjectName());
			projectInfoT.setEndDate(actionModel.getEndDate());
			projectInfoT.setRemark(actionModel.getRemark());
			if(projectInfoT != null){
				int updateResult = projectService.updateOneProject(projectInfoT);
				if (updateResult == 0) {
					registerScript("fnProjectNameIsExist();", true);
				} else {
					registerScript("fnEditProjectSuccess();", true);
				}
			}
		}
		return ProjectActionConst.EditProjectInfo_Success;
	}
	
	public String deleteProject(){
		projectService.deleteOneProject(actionModel.getProjectID());
		return ProjectActionConst.DeleteOneProject_Success;
	}
	
	//[start] Get and set methods
	@Override
	public ProjectActionModel getModel() {
		return actionModel;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public LMS_Projects getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(LMS_Projects projectInfo) {
		this.projectInfo = projectInfo;
	}

	public Collection<LMS_Staffs> getStaffInfoList() {
		return staffInfoList;
	}

	public void setStaffInfoList(Collection<LMS_Staffs> staffInfoList) {
		this.staffInfoList = staffInfoList;
	}

	public Collection<LMS_Projects> getProjectList() {
		return projectList;
	}

	public void setProjectList(Collection<LMS_Projects> projectList) {
		this.projectList = projectList;
	}

	public Collection<LMS_Sections> getSectionList() {
		return sectionList;
	}

	public void setSectionList(Collection<LMS_Sections> sectionList) {
		this.sectionList = sectionList;
	}
	
	//[end]

}
