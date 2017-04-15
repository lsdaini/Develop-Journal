package lms.code.service;

import java.util.Collection;
import java.util.Date;

import lms.code.beans.LMS_Logs;
import lms.code.beans.LMS_Staffs;
import lms.code.beans.LMS_WorkReports;
import lms.code.beans.enums.LMS_WorkReportRemindStatus;
import lms.struts.tags.Page;

public interface WorkReportService {

	/**
	 * 获取报告列表
	 */
	Collection<LMS_WorkReports> getReportList(Long leaderID,Long reporterID, Page page);
	
	/**
	 * 获取汇报人的所有汇报
	 * @param reporterID
	 * @return
	 */
	Collection<LMS_WorkReports> getReporterReports(Long reporterID);
	
	/**
	 * 获取单个报表详细信息，返回类型：对象
	 */
	LMS_WorkReports getReportInfoById(long reportId);

	/**
	 * 编辑领导评论并更新
	 */
	int updateOneReport(LMS_WorkReports reportInfo);

	/**
	 * 根据日志ID获取该日志的内容
	 */
	Collection<LMS_Logs> getLogDetailById(String logID);

	boolean addReportInfos(Collection<LMS_WorkReports> reportInfos);

	/**
	 * 根据报表ID获取对应日志信息
	 */
	Collection<LMS_Logs> getLogInfoById(String staffID, Date startDate,Date endDate, Page page);

	Collection<LMS_Staffs> getReporters(long leaderID);
	
	/**
	 * 获取员工自己的工作汇报
	 * @param staffId
	 * @return
	 */
	Collection<LMS_WorkReports> getMyWorkReports(Long staffId,Page page);
	
	boolean deleteOneReportByPk(Long reportId);
	
	/**
	 * 获取员工提交的所有审核过后的工作汇报
	 * @param staffId
	 * @return
	 */
	Collection<LMS_WorkReports> getAuditedReports(Long staffId);
	
	/**
	 * 获取领导需要审核的工作汇报
	 * @param leaderId
	 * @return
	 */
	Collection<LMS_WorkReports> getNeedAuditReports(Long leaderId);
	
	/**
	 * 更新提醒信息状态
	 * @param reportId
	 * @param remindStatus
	 * @param isLeader
	 * @return
	 */
	boolean updateRemindStatus(Long reportId,LMS_WorkReportRemindStatus remindStatus,boolean isLeader);
}
