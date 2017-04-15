package lms.code.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.frame.util.StringUtil;

import lms.code.beans.LMS_Tasks;
import lms.code.dao.TaskDao;
import lms.code.service.TaskService;


@Service("TaskService")
@Transactional
public class TaskServiceImpl implements TaskService {
	@Resource
	private TaskDao taskDao;

	public Collection<LMS_Tasks> getTasksBySectionId(Long sectionID,Long parentTaskID) {
		String parentTask = parentTaskID == null?"null" : parentTaskID.toString();
		String hql = StringUtil.format(taskDao.GetTasksBySectionId, sectionID,parentTask);
		return taskDao.queryByConditions(hql);
	}

	public LMS_Tasks getOneTaskByTaskId(Long taskID) {
		return (LMS_Tasks)taskDao.getObjectByPK(taskID);
	}

	@Override
	public int addOneTask(LMS_Tasks taskInfo) {
		int returnResult = 0;
		String hql = StringUtil.format(taskDao.GetTasksByTaskName,taskInfo.getName());
		Collection<LMS_Tasks> taskList=taskDao.queryByConditions(hql);
		if(taskList.size()>0){
			returnResult=0;
		}else{
			returnResult=taskDao.addObject(taskInfo);
		}
		return returnResult;
	}

	@Override
	public int updateOneTask(LMS_Tasks taskInfo) {
		int returnResult = 0;
		String hql = StringUtil.format(taskDao.CheckTaskNameIsExist,taskInfo.getName(),taskInfo.getTaskID());
		Collection<LMS_Tasks> taskList=taskDao.queryByConditions(hql);
		if(taskList.size() > 0){
			returnResult=0;
		}else{
			returnResult=taskDao.updateObj(taskInfo, true);
		}
		return returnResult;
	}

	@Override
	public boolean deleteOneTask(Long taskID) {
		return taskDao.deleteByPks(taskID)>0;
	}
	
}
