package cn.wolfcode.wms.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午10:02:55
 *@mail   wzzst310@163.com
 *@desp
 */
@Setter@Getter
public class OrderbillQueryObject extends QueryObject {
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private Long supplierId = -1L;
	private int status = -1;
	public Date getEndDate() {
		if (endDate!=null) {
			return DateUtil.getEndDate(endDate);
		}
		return null;
	}
}
