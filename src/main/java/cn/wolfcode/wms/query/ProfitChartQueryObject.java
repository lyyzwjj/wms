package cn.wolfcode.wms.query;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

/**
 *@author WangZhe
 *@time	  2018年6月28日下午9:11:41
 *@mail   wzzst310@163.com
 *@desp
 */
@Getter@Setter
public class ProfitChartQueryObject {
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private Long BrandId = -1L;
	private Long clientId = -1L;
	private String groupByType = "e.name";
	public Date getEndDate() {
		if (endDate!=null) {
			return DateUtil.getEndDate(endDate);
		}
		return null;
	}
	public static Map<String, String> groupByTypes = new LinkedHashMap<>();
	static{
		groupByTypes.put("e.name", "销售人员");
		groupByTypes.put("p.name", "货品名称");
		groupByTypes.put("c.name", "客户");
		groupByTypes.put("p.brand_name", "品牌");
		groupByTypes.put("DATE_FORMAT(sc.vdate,'%Y-%m-%d')", "日期(按天)");
		groupByTypes.put("DATE_FORMAT(sc.vdate,'%Y-%m')", "日期(按月)");
	}
}
