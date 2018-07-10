package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.mapper.SupplierMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class SupplierServiceImpl implements ISupplierService {
	@Autowired
	SupplierMapper supplierMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return supplierMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(Supplier supplier) {
		return supplierMapper.insert(supplier);
		
	}

	@Override
	public Supplier selectByPrimaryKey(Long id) {
		return supplierMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(Supplier supplier) {
		return supplierMapper.updateByPrimaryKey(supplier);

	}


	@Override
	public List<Supplier> selectAll() {
		return supplierMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = supplierMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Supplier> departments = supplierMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

}
