package lms.code.dao;

import org.springframework.stereotype.Repository;

import lms.code.beans.LMS_Projects;
import lms.common.AbstractDao;

@Repository
public class ProjectDao extends AbstractDao{
	
	public final String GetProjectList  = " from LMS_Projects";
	public final String GetOneProjectByProjectName = " from LMS_Projects where name = '{0}'";
	public final String CheckProjectNameIsExist = " from LMS_Projects where name = '{0}' and projectID <> {1}";
	
	@Override
	public Class<?> getReferenceClass() {
		return LMS_Projects.class;
	}
}

