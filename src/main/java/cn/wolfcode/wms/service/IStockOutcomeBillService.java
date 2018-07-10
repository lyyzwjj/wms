package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IStockOutcomeBillService {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBill stockOutcomeBill);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    int updateByPrimaryKey(StockOutcomeBill stockOutcomeBill);
    
	PageResult query(QueryObject qo);

    void audit(Long id);

}
