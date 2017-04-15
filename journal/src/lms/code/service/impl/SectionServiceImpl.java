package lms.code.service.impl;
import java.util.Collection;
import javax.annotation.Resource;
import lms.code.beans.LMS_Sections;
import lms.code.dao.SectionDao;
import lms.code.service.SectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.frame.util.StringUtil;


@Service("SectionService")
@Transactional
public class SectionServiceImpl implements SectionService {
	@Resource
	private SectionDao sectionDao;

	public Collection<LMS_Sections> getSectionsByProjectId(Long projectID) {
		String hql = StringUtil.format(sectionDao.GetSectionsByProjectId, projectID);
		return  sectionDao.queryByConditions(hql);
	}

	public int addNewSectionInfo(LMS_Sections sectionInfo) {
		int returnResult = 0;
		String hql = StringUtil.format(sectionDao.GetOneSectionBySectionName, sectionInfo.getName());
		Collection<LMS_Sections> sectionList = sectionDao.queryByConditions(hql);
		if (sectionList.size()> 0) {
			returnResult = 0;
		}else {
			returnResult = sectionDao.addObject(sectionInfo);
		}
		return returnResult;
	}
	
	public LMS_Sections getOneSection(Long sectionID) {
		return (LMS_Sections)sectionDao.getObjectByPK(sectionID);
	}

	public int updateOneSection(LMS_Sections sectionInfo) {
		String hql = StringUtil.format(sectionDao.CheckSectionNameIsExist,sectionInfo.getName(),sectionInfo.getSectionID());
		Collection<LMS_Sections> sectionList=sectionDao.queryByConditions(hql);
		if(sectionList.size()>0){
			return 0;
		}
		return sectionDao.updateObj(sectionInfo, true);
	}

	public boolean deleteOneSection(Long sectionID) {
		return sectionDao.deleteByPks(sectionID) > 0;
	}
}
