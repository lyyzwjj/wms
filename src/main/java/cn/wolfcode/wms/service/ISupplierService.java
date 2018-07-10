package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface ISupplierService {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier supplier);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier supplier);

	PageResult query(QueryObject qo);
}
