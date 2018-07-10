package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Orderbill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IOrderbillService {
    int deleteByPrimaryKey(Long id);

    int insert(Orderbill orderbill);

    Orderbill selectByPrimaryKey(Long id);

    List<Orderbill> selectAll();

    int updateByPrimaryKey(Orderbill orderbill);
    
	PageResult query(QueryObject qo);

    void audit(Long id);

}
