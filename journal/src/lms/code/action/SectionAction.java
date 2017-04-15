package lms.code.action;

import java.util.Collection;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ModelDriven;
import dev.frame.util.JSONUtil;
import dev.frame.util.StringUtil;
import lms.code.action.model.SectionActionModel;
import lms.code.action.returnconst.SectionActionConst;
import lms.code.beans.LMS_Projects;
import lms.code.beans.LMS_Sections;
import lms.code.beans.LMS_Staffs;
import lms.code.beans.enums.LMS_SectionStatus;
import lms.code.service.ProjectService;
import lms.code.service.SectionService;
import lms.code.service.StaffService;
import lms.common.AbstractAction;
@ParentPackage("sectionPackage")
@Action(value = "sectionActions")
@Results({ 
   @Result(name = SectionActionConst.InitialSectionList_Success,location = "/manage/SectionManage/SectionList.jsp")
  ,@Result(name = SectionActionConst.InitialAddSectionInfo_Success,location = "/manage/SectionManage/AddSection.jsp")
  ,@Result(name = SectionActionConst.AddSectionInfo_Success, location = "/manage/SectionManage/AddSection.jsp")
  ,@Result(name = SectionActionConst.InitialEditSectionInfo_Success,location = "/manage/SectionManage/EditSection.jsp")
  ,@Result(name = SectionActionConst.EditSectionInfo_Success, location = "/manage/SectionManage/EditSection.jsp")
  ,@Result(name = SectionActionConst.InitialViewSectionInfo_Success,location="/manage/SectionManage/ViewSection.jsp")
  ,@Result(name = SectionActionConst.DeleteOneSection_Success,location = "/manage/SectionManage/SectionList.jsp")
})
public class SectionAction extends AbstractAction implements ModelDriven<SectionActionModel> {
	private static final long serialVersionUID = -1631298505916322083L;
	
	@Resource
	private SectionService sectionService;
	@Resource
	private StaffService staffService;
	@Resource
	private ProjectService projectService;
	
	private SectionActionModel actionModel = new SectionActionModel();
	
	private Collection<LMS_Staffs> staffInfoList;
	private LMS_Projects projectInfo;
	private LMS_Sections sectionInfo;

	public String initialSectionList() {
		String projectId = getRequest("projectID");
		this.projectInfo = projectService.getOneProject(Long.parseLong(projectId));
		return SectionActionConst.InitialSectionList_Success;
	}

	public String initialAddSection() {
		String projectId = getRequest("projectID");
		this.projectInfo = projectService.getOneProject(Long.parseLong(projectId));
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		return SectionActionConst.InitialAddSectionInfo_Success;
	}
	
	public String addSection(){
		String projectId=getRequest("projectID");
		this.projectInfo = projectService.getOneProject(Long.parseLong(projectId));
		this.staffInfoList=staffService.getStaffListExceptAdmin();
		if (actionModel != null) {
			LMS_Sections sectionInfoT = new LMS_Sections();
			sectionInfoT.setName(actionModel.getSectionName());
			LMS_Staffs staffInfoManager, staffInfoAcceptor, staffInfoCreator;
			staffInfoManager = staffService.getStaffByStaffID(actionModel.getManagerId());
			staffInfoAcceptor = staffService.getStaffByStaffID(actionModel.getAcceptorId());
			staffInfoCreator = staffService.getStaffByStaffID(getSessionUser().getStaffID());
			sectionInfoT.setManager(staffInfoManager);
			sectionInfoT.setCreator(staffInfoCreator);
			sectionInfoT.setAcceptor(staffInfoAcceptor);
			sectionInfoT.setStartDate(actionModel.getStartDate());
			sectionInfoT.setPlanEndDate(actionModel.getPlanEndDate());
			sectionInfoT.setEndDate(actionModel.getEndDate());
			sectionInfoT.setProject(projectInfo);
			sectionInfoT.setStatus(LMS_SectionStatus.UnStart);
			sectionInfoT.setRemark(actionModel.getRemark());
			if(sectionInfoT!=null){
				int addSectionResult = sectionService.addNewSectionInfo(sectionInfoT);
				if (addSectionResult == 0) {
					registerScript("fnSectionNameIsExist();",true);
				}else{
					if(addSectionResult == 1){
						sectionInfoT.setManager(null);
						sectionInfoT.setAcceptor(null);
						sectionInfoT.setCreator(null);
						sectionInfoT.setProject(null);
						registerScript(StringUtil.format("fnAddNewSectionSuccess({0});", JSONUtil.toJSON(sectionInfoT)), true);
					}
				}
			}
		}
		return SectionActionConst.AddSectionInfo_Success;
	}
	
	public String initialEditSection(){
		String sectionId=getRequest("sectionID");
		this.staffInfoList=staffService.getStaffListExceptAdmin();
		this.sectionInfo = sectionService.getOneSection(Long.parseLong(sectionId));
		return SectionActionConst.InitialEditSectionInfo_Success;
	}
	
	public String editSection(){
		this.staffInfoList=staffService.getStaffListExceptAdmin();
		if(actionModel!=null&&actionModel.getSectionID()!=null&&!"".equals(actionModel.getSectionID())){
			LMS_Sections sectionInfoT=sectionService.getOneSection(actionModel.getSectionID());
			sectionInfoT.setName(actionModel.getSectionName());
			LMS_Staffs staffInfoManager = null,staffInfoAcceptor=null,staffInfoCreator=null;
			if(actionModel.getManagerId()!=null&&!"".equals(actionModel.getManagerId())){
				staffInfoManager=staffService.getStaffByStaffID(actionModel.getManagerId());
			}
			if(actionModel.getAcceptorId()!=null&&!"".equals(actionModel.getAcceptorId())){
				staffInfoAcceptor=staffService.getStaffByStaffID(actionModel.getAcceptorId());
			}
			if(actionModel.getCreatorId()!=null&&!"".equals(actionModel.getCreatorId())){
				staffInfoCreator=staffService.getStaffByStaffID(actionModel.getCreatorId());
			}
			sectionInfoT.setManager(staffInfoManager);
			sectionInfoT.setAcceptor(staffInfoAcceptor);
			sectionInfoT.setCreator(staffInfoCreator);
			sectionInfoT.setStartDate(actionModel.getStartDate());
			sectionInfoT.setPlanEndDate(actionModel.getPlanEndDate());
			sectionInfoT.setEndDate(actionModel.getEndDate());
			sectionInfoT.setRemark(actionModel.getRemark());
			if (sectionInfoT != null) {
				int updateResult = sectionService.updateOneSection(sectionInfoT);
				if (updateResult == 0) {
					registerScript("fnSectionNameIsExist();", true);
				} else {
					if (updateResult == 1) {
						sectionInfoT.setManager(null);
						sectionInfoT.setAcceptor(null);
						sectionInfoT.setCreator(null);
						sectionInfoT.setProject(null);
						registerScript(StringUtil.format("fnEditSectionSuccess({0});",JSONUtil.toJSON(sectionInfoT)), true);
					}
				}
			}
		}
		return SectionActionConst.EditSectionInfo_Success;
	}
	
	public String initialViewSection(){
		String sectionId=getRequest("sectionID");
		this.staffInfoList=staffService.getStaffListExceptAdmin();
		this.sectionInfo = sectionService.getOneSection(Long.parseLong(sectionId));
		return SectionActionConst.InitialViewSectionInfo_Success;
	}
	
	// [start] Get and set methods
	@Override
	public SectionActionModel getModel() {
		return actionModel;
	}

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public Collection<LMS_Staffs> getStaffInfoList() {
		return staffInfoList;
	}

	public void setStaffInfoList(Collection<LMS_Staffs> staffInfoList) {
		this.staffInfoList = staffInfoList;
	}
	
	public LMS_Projects getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(LMS_Projects projectInfo) {
		this.projectInfo = projectInfo;
	}
	
	public LMS_Sections getSectionInfo() {
		return sectionInfo;
	}

	public void setSectionInfo(LMS_Sections sectionInfo) {
		this.sectionInfo = sectionInfo;
	}
	// [end]
}
