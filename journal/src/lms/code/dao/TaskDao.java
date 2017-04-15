package lms.code.dao;

import org.springframework.stereotype.Repository;

import lms.code.beans.LMS_Tasks;
import lms.common.AbstractDao;

@Repository
public class TaskDao extends AbstractDao{
	public final String GetTasksBySectionId = "select new lms.code.beans.LMS_Tasks(a.taskID,a.name) from LMS_Tasks a where a.section.sectionID = {0} and parentID = {1}";
	
	public final String GetTasksByTaskName = " from LMS_Tasks where name = '{0}'";
	
	public final String CheckTaskNameIsExist=" from LMS_Tasks where name = '{0}' and taskID <> {1}";
	
	@Override
	public Class<?> getReferenceClass() {
		return LMS_Tasks.class;
	}
}
