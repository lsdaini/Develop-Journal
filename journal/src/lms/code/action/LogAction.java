package lms.code.action;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ModelDriven;
import dev.frame.convert.DateTimeConvert;
import dev.frame.util.JSONUtil;
import dev.frame.util.StringUtil;
import lms.code.action.model.LogActionModel;
import lms.code.action.returnconst.LogActionConst;
import lms.code.beans.LMS_Logs;
import lms.code.beans.LMS_Staffs;
import lms.code.beans.LMS_Tasks;
import lms.code.beans.LMS_WorkReports;
import lms.code.beans.enums.LMS_LogFreeType;
import lms.code.service.LogService;
import lms.code.service.StaffService;
import lms.code.service.TaskService;
import lms.code.service.WorkReportService;
import lms.common.AbstractAction;
import lms.common.BeanUtility;
import lms.common.KeyValue;


@ParentPackage("logPackage")
@Action(value = "logActions")
@Results({ 
	@Result(name = LogActionConst.GetLogsByDate_Success,location = "/manage/LogManage/LogList.jsp") 
   ,@Result(name = LogActionConst.AddLog_Success,location = "/manage/LogManage/AddLog.jsp") 
   ,@Result(name = LogActionConst.GetLogWorkContent_Success,location = "/manage/LogManage/WorkContentDetail.jsp")
   ,@Result(name = LogActionConst.GetOneLogDetail_Success,location = "/manage/LogManage/EditLog.jsp")
   ,@Result(name = LogActionConst.EditOneLog_Success,location = "/manage/LogManage/EditLog.jsp")
   ,@Result(name = LogActionConst.InitializeReportWorkLogs,location = "/manage/LogManage/ReportWorkLogs.jsp")
   ,@Result(name = LogActionConst.InitializeSelectDays,location = "/manage/LogManage/SelectDays.jsp")
})
public class LogAction extends AbstractAction implements
		ModelDriven<LogActionModel> {
	private static final long serialVersionUID = 6152327655892149997L;
	
	@Resource
	private LogService logService;
	@Resource
	private StaffService staffService;
	@Resource
	private TaskService taskService;
	@Resource
	private WorkReportService workReportService;
	private LogActionModel actionModel = new LogActionModel();
	private LMS_Logs logDetail;
	private Collection<LMS_Logs> logList;
	private Collection<LMS_Staffs> leaderList;
	private Collection<LMS_WorkReports> reports;
	private String selectDate;
	private LinkedList<String> logStr;
	private String logString;
	private String reportedData;
	
	public String addLog() {
		LMS_Logs logInfo = new LMS_Logs();
		BeanUtility.copyProperties(actionModel,logInfo);
		LMS_Staffs manager = staffService.getStaffByStaffID(getSessionUser().getStaffID());
		logInfo.setFreeType(LMS_LogFreeType.NoFree);
		logInfo.setManager(manager);
		if(actionModel.getProjectTask() != null){
			LMS_Tasks task = taskService.getOneTaskByTaskId(actionModel.getProjectTask());
			logInfo.setTask(task);
		}
		boolean addResult = logService.addOneLog(logInfo);
		if(addResult) {
			logger.info(manager.getName()+"添加了新日志！");
			registerScript("fnAddLogSuccess();", true);
		}
		else {
			logger.error(manager.getName()+"添加日志失败！");
			registerScript("fnAddLogFialed();", true);
		}
		return LogActionConst.AddLog_Success;
	}
	
	public String getLogsByDate(){
		this.setPageSize(5);
		if(!StringUtil.isNullOrEmpty(actionModel.getSelectDate()))
			this.setSelectDate(actionModel.getSelectDate());
		this.logList = logService.getLogsByDate(getSessionUser().getStaffID(),selectDate,page);
		return LogActionConst.GetLogsByDate_Success;
	}
	
	public String deleteOneLog(){
		logService.deleteOneLog(actionModel.getLogId());
		return getLogsByDate();
	}
	
	public String getLogWorkContent(){
		this.logDetail = logService.getOneLog(actionModel.getLogId());
		return LogActionConst.GetLogWorkContent_Success;
	}
	
	public String getOneLogDetail(){
		this.logDetail = logService.getOneLog(actionModel.getLogId());
		if(this.logDetail.getTask()!= null){
			this.logStr = new LinkedList<String>();
			RecursionLogStr(this.logDetail.getTask());
			this.logString = StringUtil.join( this.logStr, ';');
		}
		return LogActionConst.GetOneLogDetail_Success;
	}
	private void RecursionLogStr(LMS_Tasks task){
		Long taskId = task.getTaskID();
		this.logStr.addFirst(taskId.toString());
		if(task.getParent()!= null)
			RecursionLogStr(task.getParent());
	}
	public String editOneLog(){
		LMS_Logs logInfo = logService.getOneLog(actionModel.getLogId());
		BeanUtility.copyProperties(actionModel,logInfo);
		if(actionModel.getProjectTask() != null){
			LMS_Tasks task = taskService.getOneTaskByTaskId(actionModel.getProjectTask());
			logInfo.setTask(task);
		}
		logInfo.setFreeType(LMS_LogFreeType.NoFree);
		boolean editResult = logService.updateOneLog(logInfo);
		this.logDetail = logService.getOneLog(actionModel.getLogId());
		if(editResult)
			registerScript("fnEditLogSuccess();", true);
		else
			registerScript("fnEditLogFialed();", true);
		return LogActionConst.EditOneLog_Success;
	}
	
	public String initializeReportWorkLogs(){
		this.leaderList = staffService.getWorkReportLeaders(getSessionUser().getLoginName());
		return LogActionConst.InitializeReportWorkLogs;
	}
	
	public String initializeSelectDays(){
		Long reporterID = getSessionUser().getStaffID();
		Collection<LMS_WorkReports> reporterReports = workReportService.getReporterReports(reporterID);
		List<KeyValue> reportDatas = new LinkedList<KeyValue>();
		for (LMS_WorkReports item : reporterReports) {
			reportDatas.add(new KeyValue(DateTimeConvert.format(item.getStartDate(), "yyyy-MM-dd"),DateTimeConvert.format(item.getEndDate(), "yyyy-MM-dd")));
		}
		this.reportedData = JSONUtil.toJSON(reportDatas);
		return LogActionConst.InitializeSelectDays;
	}
	
	// [start] Get and set methods
	@Override
	public LogActionModel getModel() {
		return actionModel;
	}
	
	public WorkReportService getWorkReportService() {
		return workReportService;
	}

	public void setWorkReportService(WorkReportService workReportService) {
		this.workReportService = workReportService;
	}

	public String getReportedData() {
		return reportedData;
	}

	public void setReportedData(String reportedData) {
		this.reportedData = reportedData;
	}

	public String getLogString() {
		return logString;
	}

	public void setLogString(String logString) {
		this.logString = logString;
	}

	public LinkedList<String> getLogStr() {
		return logStr;
	}

	public void setLogStr(LinkedList<String> logStr) {
		this.logStr = logStr;
	}

	public LMS_Logs getLogDetail() {
		return logDetail;
	}

	public void setLogDetail(LMS_Logs logDetail) {
		this.logDetail = logDetail;
	}

	public String getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	public Collection<LMS_Logs> getLogList() {
		return logList;
	}

	public void setLogList(Collection<LMS_Logs> logList) {
		this.logList = logList;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Collection<LMS_Staffs> getLeaderList() {
		return leaderList;
	}

	public void setLeaderList(Collection<LMS_Staffs> leaderList) {
		this.leaderList = leaderList;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public Collection<LMS_WorkReports> getReports() {
		return reports;
	}

	public void setReports(Collection<LMS_WorkReports> reports) {
		this.reports = reports;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	// [end]
}
