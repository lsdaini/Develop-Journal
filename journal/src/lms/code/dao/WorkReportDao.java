package lms.code.dao;

import lms.code.beans.LMS_WorkReports;
import lms.common.AbstractDao;

import org.springframework.stereotype.Repository;

@Repository
public class WorkReportDao extends AbstractDao{

	public final String GetReporters = "select distinct a.reporter from LMS_WorkReports a where a.leader.staffID = {0} ";
	public final String GetReportList = " from LMS_WorkReports a where a.leader.staffID = {0} order by reportTime desc";
	public final String GetReportListByReporterId = " from LMS_WorkReports a where a.leader.staffID = {0} and a.reporter.staffID = {1} order by reportTime desc";
	public final String GetLogInfo = " from LMS_Logs l where l.manager.staffID = {0} and l.startTime >= to_date('{1}','yy-mm-dd') and to_date(date_format(l.endTime,''%Y-%m-%d''),'yyyy-mm-dd') <= to_date('{2}','yy-mm-dd')";
	public final String GetLogByID = " from LMS_Logs where logid = {0}";
	public final String CheckReportWorkLogsIsReported = " from LMS_WorkReports a where a.endDate >= to_date('{0}','yyyy-mm-dd') and a.leader.staffID = {1} and a.reporter.staffID = {2}";
	public final String GetMyWorkReports = " from LMS_WorkReports where reporter.staffID = {0}";
	public final String GetAuditedReports = " from LMS_WorkReports where reporter.staffID = {0} and status = 1 and staffRemind = 0";
	public final String GetNeedAuditReports = " from LMS_WorkReports where leader.staffID = {0} and leaderRemind = 0";
	public final String GetReporterReports = " from LMS_WorkReports where reporter.staffID = {0}";
	
	@Override
	public Class<?> getReferenceClass() {
		return LMS_WorkReports.class;
	}

}
