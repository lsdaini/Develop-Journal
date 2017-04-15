package lms.code.beans.enums;
/**
 * 项工作汇报状态
 * @author 梁哲灏
 *
**/
public enum LMS_WorkReportStatus {
	/**
	 * 未审阅
	 */
	UnAudit(0),
	/**
	 * 已审阅
	 */
	Audited(1);
	
	private int _value;

	private LMS_WorkReportStatus(int value) {
		_value = value;
	}

	public int value() {
		return _value;
	}
	
	public String getDescript() {
		switch (this) {
			case UnAudit:
				return "未审阅";
			case Audited:
				return "已审阅";
			default:
				break;
		}
		return "";
	}
}
