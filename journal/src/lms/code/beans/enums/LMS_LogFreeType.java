package lms.code.beans.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志费用类型
 * @author 梁哲灏
 *
 */
public enum LMS_LogFreeType {
	/**
	 * 出货  = 0
	 */
	OutFree(0),
	/**
	 * 进货  = 1
	 */
	InFree(1),
	/**
	 * 无 = 2
	 */
	NoFree(2);
	private int _value;
	private LMS_LogFreeType(int value) {
		_value = value;
	}
	public int value() {
		return _value;
	}
	
	public String getDescript(){
		switch (this) {
		case OutFree:
			return "出货";
		case InFree:
			return "进货";
		case NoFree:
			return "无";
		default:
			break;
		}
		return "";
	}	
	
	public  Map<String, Integer> getMap() {  
        Map<String, Integer> maps = new HashMap<String, Integer>();  
        LMS_LogFreeType[] LMS_LogFreeTypes = LMS_LogFreeType.class.getEnumConstants();  
        for (LMS_LogFreeType f : LMS_LogFreeTypes) {  
            maps.put(getDescript(f), f.value());  
        }  
        return maps;  
    }  
	
	private  String getDescript(LMS_LogFreeType enumParam){
		switch (enumParam) {
		case OutFree:
			return "出货";
		case InFree:
			return "进货";
		case NoFree:
			return "无";
		default:
			break;
		}
		return "";
	}	
}
