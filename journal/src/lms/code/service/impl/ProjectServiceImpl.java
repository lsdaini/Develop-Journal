package lms.code.service.impl;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.frame.util.StringUtil;
import lms.code.beans.LMS_Projects;
import lms.code.dao.ProjectDao;
import lms.code.service.ProjectService;
import lms.struts.tags.Page;

@Service("ProjectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Resource
	private ProjectDao projectDao;

	public Collection<LMS_Projects> getProjectList(Page page) {
		return  projectDao.queryByConditions(projectDao.GetProjectList,page);
	}
	
	public int addNewProjectInfo(LMS_Projects projectInfo) {
		int returnResult = 0;
		String hql = StringUtil.format(projectDao.GetOneProjectByProjectName, projectInfo.getName());
		Collection<LMS_Projects> projectList = projectDao.queryByConditions(hql);
		if (projectList.size()> 0) {
			returnResult = 0;
		}else {
			returnResult = projectDao.addObject(projectInfo);	
		}
		return returnResult;
	}

	public LMS_Projects getOneProject(Long projectID) {
		return (LMS_Projects)projectDao.getObjectByPK(projectID);
	}

	public int updateOneProject(LMS_Projects projectInfo) {
		String hql = StringUtil.format(projectDao.CheckProjectNameIsExist, projectInfo.getName(),projectInfo.getProjectID());
		Collection<LMS_Projects> projectList = projectDao.queryByConditions(hql);
		if (projectList.size()>0 ) {
			return 0;
		}
		return projectDao.updateObj(projectInfo,true) ;
	}

	public boolean deleteOneProject(Long projectID) {
		return projectDao.deleteByPks(projectID) > 0;
	}

	public Collection<LMS_Projects> getAllProjects() {
		return projectDao.queryAll();
	}
}
