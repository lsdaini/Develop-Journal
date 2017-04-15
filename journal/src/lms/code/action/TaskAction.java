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
import lms.code.action.model.TaskActionModel;
import lms.code.action.returnconst.TaskActionConst;
import lms.code.beans.LMS_Projects;
import lms.code.beans.LMS_Sections;
import lms.code.beans.LMS_Staffs;
import lms.code.beans.LMS_Tasks;
import lms.code.beans.enums.LMS_TaskStatus;
import lms.code.service.ProjectService;
import lms.code.service.SectionService;
import lms.code.service.StaffService;
import lms.code.service.TaskService;
import lms.common.AbstractAction;


@ParentPackage("taskPackage")
@Action(value = "taskActions")
@Results({
	 @Result(name = TaskActionConst.InitialAddTaskInfo_Success,location = "/manage/TaskManage/AddTask.jsp")
	,@Result(name = TaskActionConst.AddTaskInfo_Success, location = "/manage/TaskManage/AddTask.jsp")
	,@Result(name = TaskActionConst.InitialEditTaskInfo_Success,location = "/manage/TaskManage/EditTask.jsp")
	,@Result(name = TaskActionConst.EditTaskInfo_Success, location = "/manage/TaskManage/EditTask.jsp")
	,@Result(name = TaskActionConst.InitialViewTaskInfo_Success, location = "/manage/TaskManage/ViewTask.jsp")
	,@Result(name = TaskActionConst.DeleteOneTask_Success,location = "/manage/SectionManage/SectionList.jsp")
})
public class TaskAction extends AbstractAction implements
		ModelDriven<TaskActionModel> {
	private static final long serialVersionUID = -9033990251401611749L;
	@Resource
	private TaskService taskService;
	@Resource
	private StaffService staffService;
	@Resource
	private SectionService sectionService;
	@Resource
	private ProjectService projectService;
	
	private TaskActionModel actionModel = new TaskActionModel();

	private Collection<LMS_Staffs> staffInfoList;
	private LMS_Sections sectionInfo;
	private LMS_Projects projectInfo;
	private LMS_Tasks taskInfo;
	private String parentTaskId;
	
	public String initialAddTask() {
		String sectionId = getRequest("sectionID");
		if (sectionId != null && !"".equals(sectionId)) {
			this.sectionInfo = sectionService.getOneSection(Long.parseLong(sectionId));
		}
		parentTaskId = request.getParameter("parentID");
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		return TaskActionConst.InitialAddTaskInfo_Success;
	}
	
	public String addTask(){
		String sectionId = getRequest("sectionID");
		if(sectionId != null && !"".equals(sectionId)){
			this.sectionInfo = sectionService.getOneSection(Long.parseLong(sectionId));
		}
		parentTaskId = getRequest("parentID");
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		if(actionModel != null){
			LMS_Tasks taskInfoT = new LMS_Tasks();
			taskInfoT.setName(actionModel.getTaskName());
			LMS_Staffs staffInfoManager = null,staffInfoAcceptor=null,staffInfoCreator=null;
			if(actionModel.getManagerId() != null && !"".equals(actionModel.getManagerId())){
				staffInfoManager = staffService.getStaffByStaffID(actionModel.getManagerId());
			}
			if(actionModel.getAcceptorId() != null && !"".equals(actionModel.getAcceptorId())){
				staffInfoAcceptor=staffService.getStaffByStaffID(actionModel.getAcceptorId());
			}
			staffInfoCreator = staffService.getStaffByStaffID(getSessionUser().getStaffID());
			taskInfoT.setManager(staffInfoManager);
			taskInfoT.setAcceptor(staffInfoAcceptor);
			taskInfoT.setCreator(staffInfoCreator);
			taskInfoT.setStartDate(actionModel.getStartDate());
			taskInfoT.setPlanEndDate(actionModel.getPlanEndDate());
			taskInfoT.setEndDate(actionModel.getEndDate());
			taskInfoT.setSection(sectionInfo);
			LMS_Tasks parentTaskInfo=new LMS_Tasks();
			if(parentTaskId!=null&&!"".equals(parentTaskId)){
				parentTaskInfo=taskService.getOneTaskByTaskId(Long.parseLong(parentTaskId));
				taskInfoT.setParent(parentTaskInfo);
			}
			taskInfoT.setStatus(LMS_TaskStatus.UnStart);
			taskInfoT.setRemark(actionModel.getRemark());
			if(taskInfoT!=null){
				int addTaskResult = taskService.addOneTask(taskInfoT);
				if (addTaskResult == 0) {
					registerScript("fnTaskNameIsExist();",true);
				}else{
					if(addTaskResult == 1){
						taskInfoT.setSection(null);
						taskInfoT.setAcceptor(null);
						taskInfoT.setCreator(null);
						taskInfoT.setManager(null);
						taskInfoT.setParent(null);
						String taskNew = JSONUtil.toJSON(taskInfoT);
						registerScript(StringUtil.format("fnAddNewTaskSuccess({0});",taskNew),true);
					}
				}
			}
		}
		return TaskActionConst.AddTaskInfo_Success;
	}
	
	public String initialEditTask(){
		String taskId = getRequest("taskID");
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		this.taskInfo = taskService.getOneTaskByTaskId(Long.parseLong(taskId));
		return TaskActionConst.InitialEditTaskInfo_Success;
	}
	
	public String editTask() {
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		if (actionModel != null && actionModel.getTaskID() != null && !"".equals(actionModel.getTaskID())) {
			LMS_Tasks taskInfoT = taskService.getOneTaskByTaskId(actionModel.getTaskID());
			taskInfoT.setName(actionModel.getTaskName());
			LMS_Staffs staffInfoManager = null, staffInfoAcceptor = null, staffInfoCreator = null;
			if (actionModel.getManagerId() != null&& !"".equals(actionModel.getManagerId())) {
				staffInfoManager = staffService.getStaffByStaffID(actionModel.getManagerId());
			}
			if (actionModel.getAcceptorId() != null&& !"".equals(actionModel.getAcceptorId())) {
				staffInfoAcceptor = staffService.getStaffByStaffID(actionModel.getAcceptorId());
			}
			if (actionModel.getCreatorId() != null && !"".equals(actionModel.getCreatorId())) {
				staffInfoCreator = staffService.getStaffByStaffID(actionModel.getCreatorId());
			}
			taskInfoT.setManager(staffInfoManager);
			taskInfoT.setAcceptor(staffInfoAcceptor);
			taskInfoT.setCreator(staffInfoCreator);
			taskInfoT.setStartDate(actionModel.getStartDate());
			taskInfoT.setPlanEndDate(actionModel.getPlanEndDate());
			taskInfoT.setEndDate(actionModel.getEndDate());
			taskInfoT.setRemark(actionModel.getRemark());
			if (taskInfoT != null) {
				int updateResult = taskService.updateOneTask(taskInfoT);
				if (updateResult == 0) {
					registerScript("fnTaskNameIsExist();", true);
				} else {
					taskInfoT.setSection(null);
					taskInfoT.setAcceptor(null);
					taskInfoT.setCreator(null);
					taskInfoT.setManager(null);
					taskInfoT.setParent(null);
					registerScript(StringUtil.format("fnEditTaskSuccess({0});",JSONUtil.toJSON(taskInfoT)), true);
				}
			}
		}
		return TaskActionConst.EditTaskInfo_Success;
	}
	
	public String initialViewTask() {
		String taskId = getRequest("taskID");
		this.staffInfoList = staffService.getStaffListExceptAdmin();
		this.taskInfo = taskService.getOneTaskByTaskId(Long.parseLong(taskId));
		return TaskActionConst.InitialViewTaskInfo_Success;
	}
	
	// [start] Get and set methods
	@Override
	public TaskActionModel getModel() {
		return actionModel;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public Collection<LMS_Staffs> getStaffInfoList() {
		return staffInfoList;
	}

	public void setStaffInfoList(Collection<LMS_Staffs> staffInfoList) {
		this.staffInfoList = staffInfoList;
	}

	public LMS_Sections getSectionInfo() {
		return sectionInfo;
	}

	public void setSectionInfo(LMS_Sections sectionInfo) {
		this.sectionInfo = sectionInfo;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public LMS_Projects getProjectInfo() {
		return projectInfo;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setProjectInfo(LMS_Projects projectInfo) {
		this.projectInfo = projectInfo;
	}

	public LMS_Tasks getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(LMS_Tasks taskInfo) {
		this.taskInfo = taskInfo;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	// [end]
}
