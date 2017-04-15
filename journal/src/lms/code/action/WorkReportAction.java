package lms.code.action;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.annotation.Resource;
import lms.code.action.model.WorkReportActionModel;
import lms.code.action.returnconst.WorkReportActionConst;
import lms.code.beans.LMS_Logs;
import lms.code.beans.LMS_Staffs;
import lms.code.beans.LMS_WorkReports;
import lms.code.beans.enums.LMS_WorkReportRemindStatus;
import lms.code.beans.enums.LMS_WorkReportStatus;
import lms.code.service.StaffService;
import lms.code.service.WorkReportService;
import lms.common.AbstractAction;
import lms.common.BeanUtility;
import lms.common.SessionUser;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("reportPackage")
@Action(value = "reportActions")
@Results({
	@Result(name = WorkReportActionConst.GetReportList_Success,location = "/manage/ReportManage/ReportList.jsp")
   ,@Result(name = WorkReportActionConst.GetOneReportInfo_Success,location = "/manage/ReportManage/GetReportInfo.jsp")
   ,@Result(name = WorkReportActionConst.EditBossEvalu_Success,location = "/manage/ReportManage/GetReportInfo.jsp")
   ,@Result(name = WorkReportActionConst.GetLogDetail_Success,location = "/manage/ReportManage/LogDetail.jsp")
   ,@Result(name = WorkReportActionConst.ReportWorkLogs_Success,location = "/manage/LogManage/ReportWorkLogs.jsp")
   ,@Result(name = WorkReportActionConst.ReportWorkLogs_DateError,location = "/manage/LogManage/ReportWorkLogs.jsp")
   ,@Result(name = WorkReportActionConst.ChangeReportList_Success,location = "/manage/ReportManage/ChangeReportList.jsp")
   ,@Result(name = WorkReportActionConst.GetMyWorkReports,location = "/manage/LogManage/MyWorkReports.jsp")
   ,@Result(name = WorkReportActionConst.DeleteOneReport,location = "/manage/LogManage/MyWorkReports.jsp")
   ,@Result(name = WorkReportActionConst.GetReportDetail,location = "/manage/LogManage/EditOneReport.jsp")
   ,@Result(name = WorkReportActionConst.EditOneReport,location = "/manage/LogManage/EditOneReport.jsp")
   ,@Result(name = WorkReportActionConst.ViewLeaderEvalu,location = "/manage/LogManage/ViewLeaderEvalu.jsp")
   ,@Result(name = WorkReportActionConst.DoGetMyRemind,location = "/manage/ReportManage/StaffRemind.jsp")
})
public class WorkReportAction extends AbstractAction implements
		ModelDriven<WorkReportActionModel> {
	private static final long serialVersionUID = 3978809458144257387L;
	@Resource
	private WorkReportService workReportService;
	@Resource
	private StaffService staffService;
	private WorkReportActionModel actionModel = new WorkReportActionModel();
	private Collection<LMS_WorkReports> reportInfoList;
	private Collection<LMS_Staffs> reporters;
	private Collection<LMS_Staffs> leaderList;
	private Collection<LMS_Logs> staffLogs;
	
	private Collection<LMS_WorkReports> auditedReports;
	private Collection<LMS_WorkReports> needAuditReports;
	
	private LMS_WorkReports reportInfo;
	private Long reporterID;
	private Date startDate;
	private Date endDate;
	private String staffID;
	private String selectedStaffID;
	
	public String getReportList(){
		SessionUser user = super.getSessionUser();
		long leaderId = user.getStaffID();
		this.setPageSize(10);
		this.reporterID = actionModel.getReporterID();
		this.reportInfoList = workReportService.getReportList(leaderId,reporterID,page);
		this.reporters = workReportService.getReporters(leaderId);
		return WorkReportActionConst.GetReportList_Success;
	}

	public String getOneReport(){
		this.setPageSize(5);
		long reportId = actionModel.getReportID();
		this.reporterID = reportId;
		String staffID = request.getParameter("staffID");
		this.setSelectedStaffID(request.getParameter("selectedStaffID"));
		Date startDate = actionModel.getStartDate();
		Date endDate = actionModel.getEndDate();
		this.staffID = staffID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reportInfo = workReportService.getReportInfoById(reportId);
		this.staffLogs = workReportService.getLogInfoById(staffID,startDate,endDate,page);
		return WorkReportActionConst.GetOneReportInfo_Success;
	}
	
	public String reportWorkLogs(){
		Collection<Long> leaders = actionModel.getLeaders();
		Collection<LMS_WorkReports> reportInfos = new LinkedList<LMS_WorkReports>();
		for (Long leaderItem : leaders) {
			LMS_WorkReports reportInfo = new LMS_WorkReports();
			BeanUtility.copyProperties(actionModel, reportInfo);
			LMS_Staffs reporter = staffService.getStaffByStaffID(getSessionUser().getStaffID());
			LMS_Staffs leader =  staffService.getStaffByStaffID(leaderItem);
			reportInfo.setStatus(LMS_WorkReportStatus.UnAudit);
			reportInfo.setReporter(reporter);
			reportInfo.setLeader(leader);
			reportInfo.setReportTime(new Date());
			reportInfo.setStaffRemind(LMS_WorkReportRemindStatus.UnView);
			reportInfo.setLeaderRemind(LMS_WorkReportRemindStatus.UnView);
			reportInfos.add(reportInfo);
		}
		boolean addResult =  workReportService.addReportInfos(reportInfos);
		this.leaderList = staffService.getWorkReportLeaders(getSessionUser().getLoginName());
		if(addResult){
			registerScript("fnReportWorkLogsSuccess();", true);
		}else{
			registerScript("fnReportWorkLogsDateError();", true);
		}
		return addResult 
				? WorkReportActionConst.ReportWorkLogs_Success 
				: WorkReportActionConst.ReportWorkLogs_DateError;
	}
	
	public String getMyWorkReports(){
		this.setPageSize(5);
		this.reportInfoList = workReportService.getMyWorkReports(getSessionUser().getStaffID(), page);
		return WorkReportActionConst.GetMyWorkReports;
	}
	
	public String deleteOneReport(){
		boolean deleteResult = workReportService.deleteOneReportByPk(actionModel.getReportID());
		this.setPageSize(5);
		this.reportInfoList = workReportService.getMyWorkReports(getSessionUser().getStaffID(), page);
		if(deleteResult) registerScript("fnDeleteSuccess();", true);
		else registerScript("fnDeleteFialed();", true);
		return WorkReportActionConst.DeleteOneReport;
	}
	
	public String getReportDetail(){
		this.reportInfo = workReportService.getReportInfoById(actionModel.getReportID());
		if(!getRequest("operate").equals("view"))
			return WorkReportActionConst.GetReportDetail;
		else
			return WorkReportActionConst.ViewLeaderEvalu;
	}
	
	public String editOneReport(){
		LMS_WorkReports reportInfo = workReportService.getReportInfoById(actionModel.getReportID());
		reportInfo.setSummary(actionModel.getSummary());
		reportInfo.setSelfEvalu(actionModel.getSelfEvalu());
		reportInfo.setReportTime(new Date());
		reportInfo.setStatus(LMS_WorkReportStatus.UnAudit);
		workReportService.updateOneReport(reportInfo);
		registerScript("fnEditedOneReport();", true);
		return WorkReportActionConst.EditOneReport;
	}
	
	public String doGetMyRemind(){
		Long staffId = getSessionUser().getStaffID();
		this.auditedReports = workReportService.getAuditedReports(staffId);
		this.needAuditReports = workReportService.getNeedAuditReports(staffId);
		return WorkReportActionConst.DoGetMyRemind;
	}
	// [start] Get and set methods
	@Override
	public WorkReportActionModel getModel() {
		return actionModel;
	}
	
	public Collection<LMS_Staffs> getReporters() {
		return reporters;
	}
	public void setReporters(Collection<LMS_Staffs> reporters) {
		this.reporters = reporters;
	}
	
	public Collection<LMS_WorkReports> getAuditedReports() {
		return auditedReports;
	}
	public void setAuditedReports(Collection<LMS_WorkReports> auditedReports) {
		this.auditedReports = auditedReports;
	}
	public Collection<LMS_WorkReports> getNeedAuditReports() {
		return needAuditReports;
	}
	public void setNeedAuditReports(Collection<LMS_WorkReports> needAuditReports) {
		this.needAuditReports = needAuditReports;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public WorkReportService getWorkReportService() {
		return workReportService;
	}

	public void setWorkReportService(WorkReportService workReportService) {
		this.workReportService = workReportService;
	}
	
	public Collection<LMS_Staffs> getLeaderList() {
		return leaderList;
	}
	public void setLeaderList(Collection<LMS_Staffs> leaderList) {
		this.leaderList = leaderList;
	}
	public Long getReporterID() {
		return reporterID;
	}
	public void setReporterID(Long reporterID) {
		this.reporterID = reporterID;
	}
	public Collection<LMS_WorkReports> getReportInfoList() {
		return reportInfoList;
	}
	public void setReportInfoList(Collection<LMS_WorkReports> reportInfoList) {
		this.reportInfoList = reportInfoList;
	}
	public LMS_WorkReports getReportInfo() {
		return reportInfo;
	}
	public void setReportInfo(LMS_WorkReports reportInfo) {
		this.reportInfo = reportInfo;
	}
	
	public Collection<LMS_Logs> getStaffLogs() {
		return staffLogs;
	}
	public void setStaffLogs(Collection<LMS_Logs> staffLogs) {
		this.staffLogs = staffLogs;
	}
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	public String getSelectedStaffID() {
		return selectedStaffID;
	}
	public void setSelectedStaffID(String selectedStaffID) {
		this.selectedStaffID = selectedStaffID;
	}
	// [end]
}
