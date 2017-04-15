package lms.code.service;

import java.util.Collection;

import lms.code.beans.LMS_Staffs;
import lms.struts.tags.Page;

public interface StaffService {
	/**
	 * 员工登录
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return 员工信息
	 */
	 LMS_Staffs staffLogin(String userName,String passWord);
	
	/**
	 * 密码修改
	 * @param userName 用户名
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return 0：旧密码输入错误  <br/>  1：密码修改成功 
	 */
	 int changePassword(String userName,String oldPwd,String newPwd);
	
	/**
	 * 根据登录名获取员工对象
	 * @param userName 登录名
	 * @return 员工对象
	 */
	 LMS_Staffs getStaffByUserName(String userName);
	
	 /**
	  * 根据员工id获取员工对象
	  * @param staffID 员工对象
	  * @return 员工对象
	  */
	 LMS_Staffs getStaffByStaffID(Long staffID);
	 
	/**
	 * 员工完善个人资料
	 * @param staffInfo 员工对象
	 * @return 
	 */
	 boolean updateStaffInfo(LMS_Staffs staffInfo);
	 
	 /**
	  * 添加一位新员工
	  * @param staffName 新员工姓名
	  * @param roleID 新员工角色ID
	  * @return 1：成功<br/> 0：员工已存在 
	  */
	 int addNewStaffInfo(String staffName,Long roleID);
	 
	 /**
	  * 获取员工列表，排除超级管理员
	  * @return 员工列表
	  */
	 Collection<LMS_Staffs> getStaffList(Page page);
	 
	 /**
	  * 删除一位员工
	  * @param staffID 员工id
	  * @return 
	  */
	 boolean deleteOneStaff(Long staffID);
	 
	 /**
	  * 更新员工信息
	  * @param staffInfo 员工对象
	  * @return 1：更新成功 0：员工名称已存在
	  */
	 int updateStaff(LMS_Staffs staffInfo);
	 
	 /**
	  * 重置员工密码
	  * @param staffID 员工ID
	  * @return 
	  */
	 boolean updateStaffPassword(Long staffID);
	 
	 /**
		 * 获取员工列表
		 * @return
	 */
	 Collection<LMS_Staffs> getStaffListExceptAdmin();
	 
	 /**
	  * 获取提交工作汇报的所有领导
	  * @return
	  */
	 Collection<LMS_Staffs> getWorkReportLeaders(String myLoginName);
}
