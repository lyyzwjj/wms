package cn.wolfcode.wms.service;

import java.util.List;
import java.util.Map;

import cn.wolfcode.wms.query.ChartQueryObject;
import cn.wolfcode.wms.query.ProfitChartQueryObject;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IChartService {
	List<Map<String, Object>> queryForList(ChartQueryObject qo);

	List<Map<String, Object>> queryForProfitList(ProfitChartQueryObject qo);
}
