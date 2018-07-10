package cn.wolfcode.wms.mapper;

import java.util.List;
import java.util.Map;

import cn.wolfcode.wms.query.ChartQueryObject;
import cn.wolfcode.wms.query.ProfitChartQueryObject;

/**
 *@author WangZhe
 *@time	  2018年6月28日下午8:26:17
 *@mail   wzzst310@163.com
 *@desp
 */
public interface ChartMapper {
	 List<Map<String, Object>> queryForList(ChartQueryObject qo);
	 List<Map<String, Object>> queryForProfitList(ProfitChartQueryObject qo);
}
