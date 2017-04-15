package lms.code.service.impl;

import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.frame.util.StringUtil;
import lms.code.beans.LMS_Logs;
import lms.code.dao.LogDao;
import lms.code.service.LogService;
import lms.struts.tags.Page;

@Service("LogService")
@Transactional
public class LogServiceImpl implements LogService {
	@Resource
	private LogDao logDao;

	public Collection<LMS_Logs> getAllLogs(Long staffID) {
		String hql = StringUtil.format(logDao.GetLogsByStaffID, staffID);
		return logDao.queryByConditions(hql);
	}
	public Collection<LMS_Logs> getLogsByDate(Long staffID,String date,Page page) {
		String hql = StringUtil.format(logDao.GetLogsByDate, date,date,staffID);
		return logDao.queryByConditions(hql, page);
	}
	
	public boolean addOneLog(LMS_Logs log) {
		return logDao.addObject(log) > 0;
	}
	
	public boolean deleteOneLog(Long logid) {
		return logDao.deleteByPks(logid) > 0;
	}
	
	public LMS_Logs getOneLog(Long logid) {
		return (LMS_Logs) logDao.getObjectByPK(logid);
	}
	
	public boolean updateOneLog(LMS_Logs log) {
		return logDao.updateObj(log, true) > 0;
	}
	
}
