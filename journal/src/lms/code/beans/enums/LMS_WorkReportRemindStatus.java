package lms.code.beans.enums;

public enum LMS_WorkReportRemindStatus {
		/**
		 * 未看
		 */
		UnView(0),
		/**
		 * 已看
		 */
		Viewed(1);
		private int _value;

		private LMS_WorkReportRemindStatus(int value) {
			_value = value;
		}

		public int value() {
			return _value;
		}
		
		public String getDescript() {
			switch (this) {
				case UnView:
					return "未看";
				case Viewed:
					return "已看";
				default:
					break;
			}
			return "";
		}

}
