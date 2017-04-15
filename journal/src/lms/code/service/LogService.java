package lms.code.service;

import java.util.Collection;

import lms.code.beans.LMS_Logs;
import lms.struts.tags.Page;

public interface LogService {
	
	/**
	 * 获取所有的日志对象
	 * @return
	 */
	Collection<LMS_Logs> getAllLogs(Long staffID);
	
	/**
	 * 根据选择日期获取日志列表
	 * @param date 选择日期
	 * @return 日志列表
	 */
	Collection<LMS_Logs> getLogsByDate(Long staffID,String date,Page page);
	
	/**
	 * 添加一个工作日志
	 * @param log 日志对象
	 * @return 1：成功 <br/> 0：失败
	 */
	boolean addOneLog(LMS_Logs log);
	
	/**
	 * 删除一个日志对象
	 * @param logid 日志id
	 * @return 
	 */
	boolean deleteOneLog(Long logid);
	
	/**
	 * 根据logid获取一条log对象
	 * @param logid 
	 * @return
	 */
	LMS_Logs getOneLog(Long logid);
	
	/**
	 * 编辑一个日志信息 
	 * @param log 日志对象
	 * @return
	 */
	boolean updateOneLog(LMS_Logs log);
}
