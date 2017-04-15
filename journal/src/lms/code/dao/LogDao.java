package lms.code.dao;

import org.springframework.stereotype.Repository;

import lms.code.beans.LMS_Logs;
import lms.common.AbstractDao;

@Repository
public class LogDao extends AbstractDao{
	
	public final String GetLogsByDate = " from LMS_Logs where date_format(startTime,'%Y-%m-%d') = '{0}' and date_format(endTime,'%Y-%m-%d') = '{1}' and manager.staffID = {2}";
	
	public final String GetLogsByStaffID = " from LMS_Logs where manager.staffID = {0}";
	
	@Override
	public Class<?> getReferenceClass() {
		return LMS_Logs.class;
	}
}
