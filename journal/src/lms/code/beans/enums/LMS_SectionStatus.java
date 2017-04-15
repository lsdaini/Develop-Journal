package lms.code.beans.enums;
/**
 * 项目阶段状态
 * @author 梁哲灏
 *
 */
public enum LMS_SectionStatus {
	/**
	 * 未开始
	 */
	UnStart(-1),
	/**
	 * 进行中
	 */
	Doing(0),
	/**
	 * 已结束
	 */
	Over(1);

	private int _value;

	private LMS_SectionStatus(int value) {
		_value = value;
	}

	public int value() {
		return _value;
	}
	
	public String getDescript(){
		switch (this) {
		case UnStart:
			return "未开始";
		case Doing:
			return "进行中";
		case Over:
			return "已结束";
		default:
			break;
		}
		return "";
	}	
}
