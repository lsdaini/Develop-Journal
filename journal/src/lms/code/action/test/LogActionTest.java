package lms.code.action.test;

import lms.code.action.LogAction;
import lms.code.action.StaffAction;
import lms.code.action.model.LogActionModel;
import lms.code.action.model.StaffActionModel;
import lms.code.action.returnconst.LogActionConst;
import lms.code.action.returnconst.StaffActionConst;

import org.junit.Before;
import org.junit.Test;

public class LogActionTest extends AbstractSpringStruts2JUnit4 {
	@Before
	public void staffSignInTest() throws Exception {
		StaffAction staffAction = (StaffAction) super.createAction("/actions/staff/staffActions.action");
		StaffActionModel actionModel = staffAction.getModel();
		actionModel.setLoginName("admin");
		actionModel.setPassWord("admin");
		assertEquals(StaffActionConst.StaffSignIn_Success,staffAction.staffSignIn());
	}
	@Test
	public void addLog(){
		LogAction logAction = (LogAction) super.createAction("/actions/staff/logActions.action");
		assertEquals(LogActionConst.AddLog_Success, logAction.addLog());
	}
	@Test
	public void getLogsByDate(){
		LogAction logAction = (LogAction) super.createAction("/actions/staff/logActions.action");
		LogActionModel logActionModel = logAction.getModel();
		logActionModel.setSelectDate("2013-8-1");
		assertEquals(LogActionConst.GetLogsByDate_Success, logAction.getLogsByDate());
	}
	@Test
	public void getOneLogDetail(){
		LogAction logAction = (LogAction) super.createAction("/actions/staff/logActions.action");
		LogActionModel logActionModel = logAction.getModel();
		logActionModel.setLogId(1L);
		assertEquals(LogActionConst.GetOneLogDetail_Success, logAction.getOneLogDetail());
	}
}
