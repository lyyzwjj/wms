package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class BrandServiceImpl implements IBrandService {
	@Autowired
	BrandMapper brandMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return brandMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(Brand brand) {
		return brandMapper.insert(brand);
		
	}

	@Override
	public Brand selectByPrimaryKey(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(Brand brand) {
		return brandMapper.updateByPrimaryKey(brand);

	}


	@Override
	public List<Brand> selectAll() {
		return brandMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = brandMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Brand> departments = brandMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

}
