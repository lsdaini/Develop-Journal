package lms.code.beans.enums;

/**
 * 项目任务等级
 * 
 * @author 梁哲灏
 * 
 */
public enum LMS_TaskLevel {
	/**
	 * 正常
	 */
	Normal(-1),
	/**
	 * 急
	 */
	Urgent(0),
	/**
	 * 紧急
	 */
	Crash(1);

	private int _value;

	private LMS_TaskLevel(int value) {
		_value = value;
	}

	public int value() {
		return _value;
	}

	public String getDescript() {
		switch (this) {
			case Normal:
				return "正常";
			case Urgent:
				return "急";
			case Crash:
				return "紧急";
			default:
				break;
		}
		return "";
	}
}
