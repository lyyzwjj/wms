package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.ProductStockQueryObject;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IProductStockService {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStock productStock);

    ProductStock selectByPrimaryKey(Long id);

    List<ProductStock> selectAll();

    int updateByPrimaryKey(ProductStock productStock);
    
    List<ProductStock> query(ProductStockQueryObject qo);
}
