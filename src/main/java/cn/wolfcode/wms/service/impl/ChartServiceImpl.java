package cn.wolfcode.wms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.mapper.ChartMapper;
import cn.wolfcode.wms.query.ChartQueryObject;
import cn.wolfcode.wms.query.ProfitChartQueryObject;
import cn.wolfcode.wms.service.IChartService;

/**
 *@author WangZhe
 *@time	  2018年6月28日下午9:19:09
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class ChartServiceImpl implements IChartService {
	@Autowired
	ChartMapper chartmapper;
	@Override
	public List<Map<String, Object>> queryForList(ChartQueryObject qo) {
		return chartmapper.queryForList(qo);
	}
	@Override
	public List<Map<String, Object>> queryForProfitList(ProfitChartQueryObject qo) {
		return chartmapper.queryForProfitList(qo);
	}

}
