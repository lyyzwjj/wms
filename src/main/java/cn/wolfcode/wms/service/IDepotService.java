package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IDepotService {
    int deleteByPrimaryKey(Long id);

    int insert(Depot depot);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot depot);
	PageResult query(QueryObject qo);
}
