package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IBrandService {
    int deleteByPrimaryKey(Long id);

    int insert(Brand brand);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand brand);
	PageResult query(QueryObject qo);
}
