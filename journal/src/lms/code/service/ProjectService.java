package lms.code.service;

import java.util.Collection;
import lms.code.beans.LMS_Projects;
import lms.struts.tags.Page;

public interface ProjectService {
	
	/**
	 * 获取所有项目
	 * @return
	 */
	Collection<LMS_Projects> getAllProjects();
	
	/**
	 * 获取项目列表
	 * @param page 
	 * @return
	 */
	Collection<LMS_Projects> getProjectList(Page page);
	
	/**
	 * 新增项目
	 * @param LmsProject 项目bean类
	 * @return
	 */
	int addNewProjectInfo(LMS_Projects projectInfo);
	
	/**
	 * 根据项目ID获取项目数据
	 * @param projectID 项目ID
	 * @return
	 */
	LMS_Projects getOneProject(Long projectID);
	
	/**
	 * 更新项目
	 * @param projectInfo 项目bean类
	 * @return
	 */
	int updateOneProject(LMS_Projects projectInfo);
	
	/**
	 * 删除项目
	 * @param projectID 项目ID
	 * @return
	 */
	boolean deleteOneProject(Long projectID);
}
