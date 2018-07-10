package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.service.IProductStockService;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class ProductStockServiceImpl implements IProductStockService {
	@Autowired
	ProductStockMapper productStockMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return productStockMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(ProductStock productStock) {
		return productStockMapper.insert(productStock);
		
	}

	@Override
	public ProductStock selectByPrimaryKey(Long id) {
		return productStockMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(ProductStock productStock) {
		return productStockMapper.updateByPrimaryKey(productStock);

	}


	@Override
	public List<ProductStock> selectAll() {
		return productStockMapper.selectAll();
	}

	@Override
	public List<ProductStock> query(ProductStockQueryObject qo) {
		return productStockMapper.queryForList(qo);
	}

}
