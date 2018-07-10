package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductService;
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
public class ProductServiceImpl implements IProductService {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	BrandMapper brandMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return productMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(Product product) {
		Brand brand = brandMapper.selectByPrimaryKey(product.getBrandId());
		product.setBrandName(brand.getName());
		return productMapper.insert(product);
		
	}

	@Override
	public Product selectByPrimaryKey(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(Product product) {
		Brand brand = brandMapper.selectByPrimaryKey(product.getBrandId());
		product.setBrandName(brand.getName());
		return productMapper.updateByPrimaryKey(product);

	}


	@Override
	public List<Product> selectAll() {
		return productMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = productMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Product> departments = productMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

}
