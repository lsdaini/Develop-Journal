package lms.code.service;

import java.util.Collection;

import lms.code.beans.LMS_Tasks;

public interface TaskService {
	/**
	 * 获取任务列表
	 * @param sectionID  阶段ID
	 * @return
	 */
    Collection<LMS_Tasks> getTasksBySectionId(Long sectionID,Long parentTaskID);
    
    /**
     * 根据任务ID获取一个任务
     * @param taskID
     * @return
     */
    LMS_Tasks getOneTaskByTaskId(Long taskID);
    
    /**
     * 新增任务
     * @param taskInfo 任务bean类
     * @return
     */
    int addOneTask(LMS_Tasks taskInfo);
    
    /**
     * 修改任务
     * @param taskInfo  任务bean类
     * @return
     */
    int updateOneTask(LMS_Tasks taskInfo);
    
    /**
     * 删除任务 
     * @param taskID  任务ID
     * @return
     */
    boolean deleteOneTask(Long taskID);
}
