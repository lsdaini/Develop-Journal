package lms.code.service.impl;

import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import lms.code.beans.LMS_Logs;
import lms.code.beans.LMS_Staffs;
import lms.code.beans.LMS_WorkReports;
import lms.code.beans.enums.LMS_WorkReportRemindStatus;
import lms.code.dao.WorkReportDao;
import lms.code.service.WorkReportService;
import lms.struts.tags.Page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.frame.convert.DateTimeConvert;
import dev.frame.util.StringUtil;


@Service("WorkReportService")
@Transactional
public class WorkReportServiceImpl implements WorkReportService {
	
	@Resource
	private WorkReportDao workReportDao;
	
	public Collection<LMS_WorkReports> getReportList(Long leaderID,Long reporterID, Page page) {
		String hql = (reporterID == null || reporterID == 0)
			?StringUtil.format(workReportDao.GetReportList, leaderID)
			:StringUtil.format(workReportDao.GetReportListByReporterId, leaderID,reporterID);
		return  workReportDao.queryByConditions(hql,page);
	}
	
	
	public LMS_WorkReports getReportInfoById(long reportId) {
		return (LMS_WorkReports) workReportDao.getObjectByPK(reportId);
	}
	
	public Collection<LMS_Logs> getLogInfoById(String staffID,
			Date startDate, Date endDate, Page page) {
		long staffid = Long.parseLong(staffID);
		String startDate1 = DateTimeConvert.format(startDate, "yyyy-MM-dd");
		String endDate1 = DateTimeConvert.format(endDate, "yyyy-MM-dd");
		String hql = StringUtil.format(workReportDao.GetLogInfo, staffid,startDate1,endDate1);
		return  workReportDao.queryByConditions(hql,page);
	}
	
	
	public int updateOneReport(LMS_WorkReports reportInfo) {
		return workReportDao.updateObj(reportInfo,true) ;
	}

	public Collection<LMS_Logs> getLogDetailById(String logID) {
		String hql = StringUtil.format(workReportDao.GetLogByID,logID);
		return  workReportDao.queryByConditions(hql);
	}

	public boolean addReportInfos(Collection<LMS_WorkReports> reportInfos) {
		int addResult = 0;
		for (LMS_WorkReports reportInfo : reportInfos) {
			String startDate = DateTimeConvert.format(reportInfo.getStartDate(), "yyyy-MM-dd");
			Long leaderId = reportInfo.getLeader().getStaffID();
			Long reporterId = reportInfo.getReporter().getStaffID();
			String hql = StringUtil.format(workReportDao.CheckReportWorkLogsIsReported,startDate,leaderId,reporterId);
			Collection<LMS_WorkReports> workReports = workReportDao.queryByConditions(hql);
			if(workReports.size() == 0){
				workReportDao.addObject(reportInfo);
				addResult+=1;
			}
		}
		return addResult > 0;
	}
	
	public Collection<LMS_WorkReports> getMyWorkReports(Long staffId,Page page) {
		String hql = StringUtil.format(workReportDao.GetMyWorkReports, staffId);
		return workReportDao.queryByConditions(hql, page);
	}
	
	public boolean deleteOneReportByPk(Long reportId) {
		return workReportDao.deleteByPks(reportId) > 0;
	}
	
	public Collection<LMS_Staffs> getReporters(long leaderID) {
		String hql = StringUtil.format(workReportDao.GetReporters, leaderID);
		return  workReportDao.queryByConditions(hql);
	}

	public Collection<LMS_WorkReports> getAuditedReports(Long staffId) {
		String hql = StringUtil.format(workReportDao.GetAuditedReports,staffId);
		return workReportDao.queryByConditions(hql);
	}

	public Collection<LMS_WorkReports> getNeedAuditReports(Long leaderId) {
		String hql = StringUtil.format(workReportDao.GetNeedAuditReports,leaderId);
		return workReportDao.queryByConditions(hql);
	}

	public boolean updateRemindStatus(Long reportId,
			LMS_WorkReportRemindStatus remindStatus, boolean isLeader) {
		LMS_WorkReports reportInfo = (LMS_WorkReports) workReportDao.getObjectByPK(reportId);
		if (isLeader) {
			reportInfo.setLeaderRemind(remindStatus);
		} else {
			reportInfo.setStaffRemind(remindStatus);
		}
		return workReportDao.updateObj(reportInfo, false) > 0;
	}

	public Collection<LMS_WorkReports> getReporterReports(Long reporterID) {
		String hql = StringUtil.format(workReportDao.GetReporterReports, reporterID);
		return workReportDao.queryByConditions(hql);
	}
}
