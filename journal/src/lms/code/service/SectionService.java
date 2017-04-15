package lms.code.service;

import java.util.Collection;
import lms.code.beans.LMS_Sections;

public interface SectionService {
	
	/**
	 * 根据项目ID获取阶段列表
	 * @param projectID
	 * @return
	 */
	Collection<LMS_Sections> getSectionsByProjectId(Long projectID);
	
	/**
	 * 新增阶段
	 * @param sectionInfo 阶段Bean类
	 * @return
	 */
	int addNewSectionInfo(LMS_Sections sectionInfo);
	
	/**
	 * 获取阶段信息
	 * @param sectionID  阶段ID
	 * @return
	 */
	LMS_Sections getOneSection(Long sectionID);
	
	/**
	 * 修改阶段信息
	 * @param sectionInfo  阶段Bean类
	 * @return
	 */
	int updateOneSection(LMS_Sections sectionInfo);
	
	/**
	 * 删除阶段信息
	 * @param sectionID 阶段ID
	 * @return
	 */
	boolean deleteOneSection(Long sectionID);
}
