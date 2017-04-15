package lms.code.beans.enums;
/**
 * 日志类型
 * @author 梁哲灏
 *
 */
public enum LMS_LogType {
	/**
	 * 项目型 = 0
	 */
	Project(0),
	/**
	 * 非项目型 = 1
	 */
	NoProject(1);
	
	private int _value;

	private LMS_LogType(int value) {
		_value = value;
	}

	public int value() {
		return _value;
	}
	
	public String getDescript(){
		switch (this) {
		case Project:
			return "项目型";
		case NoProject:
			return "非项目型";
		default:
			break;
		}
		return "";
	}	
	
}
