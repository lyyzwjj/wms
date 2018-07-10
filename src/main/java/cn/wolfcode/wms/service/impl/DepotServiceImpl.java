package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.mapper.DepotMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class DepotServiceImpl implements IDepotService {
	@Autowired
	DepotMapper depotMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return depotMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(Depot depot) {
		return depotMapper.insert(depot);
		
	}

	@Override
	public Depot selectByPrimaryKey(Long id) {
		return depotMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(Depot depot) {
		return depotMapper.updateByPrimaryKey(depot);

	}


	@Override
	public List<Depot> selectAll() {
		return depotMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = depotMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Depot> departments = depotMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

}
