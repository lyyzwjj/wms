package cn.wolfcode.wms.util;

import lombok.Getter;
import lombok.Setter;

/**
 *@author WangZhe
 *@time	  2018年6月20日下午3:51:50
 *@mail   wzzst310@163.com
 *@desp
 */
@Getter@Setter
public class JsonResult {
	private Boolean success = true;
	private String errorMsg;
	public void markMsg(String errorMsg) {
		this.success = false;
		this.errorMsg = errorMsg;
	}
}
