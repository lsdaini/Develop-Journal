package lms.code.dao;

import org.springframework.stereotype.Repository;

import lms.code.beans.LMS_Sections;
import lms.common.AbstractDao;

@Repository
public  class SectionDao extends AbstractDao {
	
	public final String GetSectionsByProjectId = "select new lms.code.beans.LMS_Sections(a.sectionID,a.name) from LMS_Sections a where a.project.projectID = {0}";

	public final String GetOneSectionBySectionName = " from LMS_Sections where name = '{0}'";
	
	public final String CheckSectionNameIsExist = " from LMS_Sections where name = '{0}' and sectionID <> {1}";
	
	@Override
	public Class<?> getReferenceClass() {
		return LMS_Sections.class;
	}
}
